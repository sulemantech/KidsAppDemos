package com.example.animationdemo.activities

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animationdemo.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

data class Dua(
    val arabic: String,
    val translation: String,
    val reference: String,
    val backgroundResId: Int,
    val statusBarColorResId: Int,
    val fullAudioResId: Int,
    val wordAudioPairs: List<Pair<String, Int>>
)

@Composable
fun DuaScreen(
    index: Int,
    navController: NavController,
    onMenuClick: () -> Unit = {},
    onRightIconClick: () -> Unit = {}
) {
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current

    val duas = listOf(
        Dua(
            arabic = "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْخُبُثِ وَالْخَبَائِث",
            translation = "Glory is to Allah and praise is to Him...",
            reference = "[Sahih Muslim]",
            backgroundResId = R.drawable.framewashroom_bg,
            statusBarColorResId = R.color.top_nav2,
            fullAudioResId = R.raw.audio1,
            wordAudioPairs = listOf(
                "اللَّهُمِ" to R.raw.audio1,
                "إِنِّي" to R.raw.audio2,
                "أَعُوذُ بِكَ" to R.raw.audio3,
                "مِنَ الْخُبُثِ" to R.raw.audio4,
                "وَالْخَبَائِث" to R.raw.audio5,
            )
        ),
        Dua(
            arabic = "اللّهُـمَّ باعِـدْ بَيْني وَبَيْنَ خَطاياي",
            translation = "O Allah, distance me from my sins...",
            reference = "[Sahih Muslim]",
            backgroundResId = R.drawable.framebeforedress_bg,
            statusBarColorResId = R.color.top_nav1,
            fullAudioResId = R.raw.audio2,
            wordAudioPairs = listOf(
                "اللّهُـمَّ" to R.raw.audio5,
                "باعِـدْ" to R.raw.audio2,
                "بَيْنيَ" to R.raw.audio3,
                "وَبَيْنَ" to R.raw.audio4,
                "خَطاياي" to R.raw.audio5,
            )

        ),
        Dua(
            arabic = "سُبْحَانَ اللّٰہِ وَبِحَمْدِہِ سُبْحَانَ اللّٰہِ الْعَظِیْمِ",
            translation = "O Allah, distance me from my sins...",
            reference = "[Sahih Muslim]",
            backgroundResId = R.drawable.framebeforedress_bg,
            statusBarColorResId = R.color.top_nav1,
            fullAudioResId = R.raw.audio2,
            wordAudioPairs = listOf(
                "سُبْحَانَ اللّٰہ" to R.raw.audioo1,
                "وَبِحَمْدِہ" to R.raw.audioo2,
                "سُبْحَانَ اللّٰہِ الْعَظِیْمِ " to R.raw.audioo3,
            )

        ),

        )

    var currentIndex by remember { mutableStateOf(index.coerceIn(0, duas.lastIndex)) }
    val currentDua = duas[currentIndex]
    val backgroundColor = colorResource(id = currentDua.statusBarColorResId)

    var playingWordIndex by remember { mutableStateOf(-1) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    SideEffect {
        systemUiController.setStatusBarColor(color = backgroundColor)
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    fun playWord(index: Int) {
        if (index >= currentDua.wordAudioPairs.size) return
        val (word, audioResId) = currentDua.wordAudioPairs[index]
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, audioResId)
        playingWordIndex = index
        mediaPlayer?.setOnCompletionListener {
            if (index + 1 < currentDua.wordAudioPairs.size) {
                playWord(index + 1)
            } else {
                playingWordIndex = -1
            }
        }
        mediaPlayer?.start()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = currentDua.backgroundResId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_back),
                        contentDescription = "Back",
                        modifier = Modifier.size(35.dp)
                    )
                }

                IconButton(onClick = onRightIconClick) {
                    Image(
                        painter = painterResource(id = R.drawable.setting),
                        contentDescription = "Settings",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(410.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                currentDua.wordAudioPairs.reversed().forEachIndexed { reverseIndex, pair ->
                    val actualIndex = currentDua.wordAudioPairs.lastIndex - reverseIndex
                    Text(
                        text = "${pair.first} ",
                        fontSize = 22.sp,
                        fontFamily = FontFamily.Serif,
                        style = TextStyle(
                            textDirection = TextDirection.ContentOrRtl,
                            color = if (playingWordIndex == actualIndex) Color.Blue else colorResource(
                                R.color.dark_blue
                            ),
                            fontWeight = if (playingWordIndex == actualIndex) FontWeight.Bold else FontWeight.Normal
                        ),
                        modifier = Modifier
                            .padding(horizontal = 1.dp)
                            .clickable(enabled = playingWordIndex == -1) {
                                playWord(actualIndex)
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = currentDua.translation,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = currentDua.reference,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 65.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                IconButton(onClick = {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(context, currentDua.fullAudioResId)
                    mediaPlayer?.setOnCompletionListener {
                        playingWordIndex = -1
                    }
                    mediaPlayer?.start()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_previous),
                        contentDescription = "Previous",
                        modifier = Modifier.size(39.dp)
                    )
                }

                IconButton(onClick = {
                    playWord(0)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sound),
                        contentDescription = "Sound",
                        modifier = Modifier.size(39.dp)
                    )
                }

                IconButton(onClick = {
                    currentIndex = (currentIndex + 1) % duas.size
                    mediaPlayer?.release()
                    playingWordIndex = -1
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "Next",
                        modifier = Modifier.size(39.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .clickable {
                    mediaPlayer?.release()
                    navController.popBackStack()
                }
        ) {
            Image(
                painter = painterResource(R.drawable.button_bg),
                contentDescription = "Quit",
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
            )
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    var selectedLanguage by remember { mutableStateOf("English") }
    var fontSize by remember { mutableStateOf(32f) }
    var isAutoPlayEnabled by remember { mutableStateOf(true) }

    val languages = listOf("English", "Urdu")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF4CAF50))
            }
            Text(
                text = if (selectedLanguage == "Urdu") "ترتیبات" else "Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* Info logic */ }) {
                Icon(Icons.Default.Info, contentDescription = "Info", tint = Color(0xFF4CAF50))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Language selection
        Text(
            text = if (selectedLanguage == "Urdu") "پہلی زبان" else "Default Language",
            color = Color(0xFF00BCD4),
            fontWeight = FontWeight.Medium
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFF88)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column {
                languages.forEach { lang ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedLanguage == lang,
                                onClick = { selectedLanguage = lang }
                            )
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedLanguage == lang,
                            onClick = { selectedLanguage = lang }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (lang == "Urdu") "اردو" else "English"
                        )
                    }
                }
            }
        }


        Text(
            text = if (selectedLanguage == "Urdu") "فونٹ سیٹنگز" else "Font Settings",
            color = Color(0xFF00BCD4),
            fontWeight = FontWeight.Medium
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFF88)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(Modifier.padding(12.dp)) {
                Text(text = if (selectedLanguage == "Urdu") "فونٹ سائز" else "Font Size")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "اللّهُـمَّ باعِـدْ بَيْني وَبَيْنَ خَطاياي",
                    fontSize = fontSize.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Slider(
                    value = fontSize,
                    onValueChange = { fontSize = it },
                    valueRange = 16f..48f
                )
                Text(
                    text = fontSize.toInt().toString(),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }

        // Playback Settings
        Text(
            text = if (selectedLanguage == "Urdu") "پلے بیک سیٹنگز" else "Playback Settings",
            color = Color(0xFF00BCD4),
            fontWeight = FontWeight.Medium
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFF88)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (selectedLanguage == "Urdu") "آٹو پلے" else "AutoPlay",
                    modifier = Modifier.weight(1f)
                )
                RadioButton(
                    selected = isAutoPlayEnabled,
                    onClick = { isAutoPlayEnabled = !isAutoPlayEnabled }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Save button
        Button(
            onClick = { /* Save logic here */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0097A7))
        ) {
            Text(
                text = if (selectedLanguage == "Urdu") "تبدیلیاں محفوظ کریں" else "Save changes",
                color = Color.White
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}
@Preview(showBackground = true)
@Composable
fun DuaScreenPreview() {
    val fakeNavController = rememberNavController()
    DuaScreen(index = 0, navController = fakeNavController)
}




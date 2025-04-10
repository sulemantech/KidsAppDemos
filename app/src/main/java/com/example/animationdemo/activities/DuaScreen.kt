package com.example.animationdemo.activities

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
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
import kotlin.math.max

@Composable
fun DuaScreen(
    index: Int,
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current

    val duas = duaList

    var currentIndex by remember { mutableStateOf(index.coerceIn(0, duas.lastIndex)) }
    val backgroundColor = colorResource(id = R.color.top_nav)
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    SideEffect {
        systemUiController.setStatusBarColor(color = backgroundColor)
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = duas[currentIndex].backgroundResId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_homeasset),
                        contentDescription = "Back",
                        modifier = Modifier.size(35.dp)
                    )
                }

                Text(
                    text = "Praise and Glory",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                )

                IconButton(onClick = {
                    navController.navigate("SettingsScreen")
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_setting),
                        contentDescription = "Settings",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(390.dp))

            val showCount = if (currentIndex < 4) 2 else 1

            Column(
                modifier = Modifier
                    .height(250.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                for (i in currentIndex until (currentIndex + showCount).coerceAtMost(duas.size)) {
                    val dua = duas[i]
                    var wordIndex by remember { mutableStateOf(-1) }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = {
                            mediaPlayer?.release()
                            mediaPlayer = MediaPlayer.create(context, dua.fullAudioResId)
                            mediaPlayer?.setOnCompletionListener { wordIndex = -1 }
                            mediaPlayer?.start()
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_replay),
                                contentDescription = "Play Full",
                                modifier = Modifier.size(39.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        IconButton(onClick = {
                            fun playWord(index: Int) {
                                if (index >= dua.wordAudioPairs.size) {
                                    wordIndex = -1
                                    return
                                }
                                val (_, audioResId) = dua.wordAudioPairs[index]
                                mediaPlayer?.release()
                                mediaPlayer = MediaPlayer.create(context, audioResId)
                                wordIndex = index
                                mediaPlayer?.setOnCompletionListener {
                                    playWord(index + 1)
                                }
                                mediaPlayer?.start()
                            }
                            playWord(0)
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_playy),
                                contentDescription = "Play Word-by-Word",
                                modifier = Modifier.size(39.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        IconButton(onClick = {
                            fun playWord(index: Int) {
                                if (index >= dua.wordAudioPairs.size) {
                                    wordIndex = -1
                                    return
                                }
                                val (_, audioResId) = dua.wordAudioPairs[index]
                                mediaPlayer?.release()
                                mediaPlayer = MediaPlayer.create(context, audioResId)
                                wordIndex = index
                                mediaPlayer?.setOnCompletionListener {
                                    playWord(index + 1)
                                }
                                mediaPlayer?.start()
                            }
                            playWord(0)
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_starr),
                                contentDescription = "Play Word-by-Word",
                                modifier = Modifier.size(39.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        dua.wordAudioPairs.reversed().forEachIndexed { revIndex, pair ->
                            val actualIndex = dua.wordAudioPairs.lastIndex - revIndex
                            Text(
                                text = "${pair.first} ",
                                fontSize = 22.sp,
                                fontFamily = FontFamily.Serif,
                                style = TextStyle(
                                    textDirection = TextDirection.ContentOrRtl,
                                    color = if (wordIndex == actualIndex) Color.Blue else colorResource(
                                        R.color.dark_blue
                                    ),
                                    fontWeight = if (wordIndex == actualIndex) FontWeight.Bold else FontWeight.Normal
                                ),
                                modifier = Modifier
                                    .padding(horizontal = 1.dp)
                                    .clickable(enabled = wordIndex == -1) {
                                        mediaPlayer?.release()
                                        mediaPlayer = MediaPlayer.create(context, pair.second)
                                        wordIndex = actualIndex
                                        mediaPlayer?.setOnCompletionListener {
                                            wordIndex = -1
                                        }
                                        mediaPlayer?.start()
                                    }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = dua.translation,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Text(
                            text = dua.reference,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(80.dp))
                }
            }

        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                val step = if (currentIndex < 4) 2 else 1
                currentIndex = if (currentIndex - step < 0) 0 else currentIndex - step
                mediaPlayer?.release()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_backarrow),
                    contentDescription = "Previous Dua",
                    modifier = Modifier.size(35.dp)
                )
            }

            IconButton(onClick = {
                val step = if (currentIndex < 4) 2 else 1
                currentIndex =
                    if (currentIndex + step >= duas.size) 0 else currentIndex + step
                mediaPlayer?.release()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_nextarrow),
                    contentDescription = "Next Dua",
                    modifier = Modifier.size(39.dp)
                )
            }
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




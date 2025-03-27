package com.example.animationdemo.activities

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.animationdemo.R
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(4000)
        isVisible = false
        delay(500)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        exit = slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(durationMillis = 1000)
        ) + fadeOut(animationSpec = tween(durationMillis = 800))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F4F5)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Kids Tracing App",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))

                LottieAnimation(
                    modifier = Modifier.size(180.dp),
                    composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation)).value,
                    iterations = LottieConstants.IterateForever,
                )
            }
        }

    }
}

@Composable
fun HomeScreen(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally { it },
        exit = slideOutHorizontally { -it }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF8FD2F3)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "App Logo",
                modifier = Modifier
                    .width(450.dp)
                    .align(Alignment.TopCenter)

            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Kids Tracing App",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        isVisible = false
                        navController.navigate("LetterTracingApp") {
                            popUpTo("Home") { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(text = "Start Tracing", fontSize = 18.sp, color = Color.White)
                }

            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = "splash",
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = 800)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(durationMillis = 800)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(durationMillis = 800)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = 800)
            )
        }
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("LetterTracingApp") { LetterTracingApp(navController) }
    }
}

@Composable
fun LetterTracingApp(navController: NavController) {
    var currentLetterIndex by remember { mutableIntStateOf(0) }
    val letters = ('A'..'Z').toList()
    val backgroundComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.background))
    val celebrationComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bollon_pop))

    Box(modifier = Modifier.fillMaxSize()) {

        LottieAnimation(
            composition = backgroundComposition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.fillMaxSize()
        )

        LottieBackButton(navController)

        if (currentLetterIndex < letters.size) {
            LetterTracingScreen(letter = letters[currentLetterIndex]) {
                currentLetterIndex++
            }
        } else {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Congratulations! 😍🎉",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    LottieAnimation(
                        modifier = Modifier.size(180.dp),
                        composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bollon_pop)).value,
                        iterations = LottieConstants.IterateForever,
                    )
                }
            }
        }
    }
}

@Composable
fun LottieBackButton(navController: NavController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.back_btn))

    Box(
        modifier = Modifier
            .padding(20.dp)
            .size(50.dp)
            .clickable {
                navController.navigate("home") {
                    popUpTo("LetterTracingApp") { inclusive = true }
                }
            }
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}



@Composable
fun LetterTracingScreen(letter: Char, onNext: () -> Unit) {
    val touchPoints = remember { mutableStateListOf<Offset>() }
    val context = LocalContext.current
    var isCompleted by remember { mutableStateOf(false) }


    val successSound = remember {
        MediaPlayer.create(context, R.raw.tracing_sound)
    }

    DisposableEffect(Unit) {
        onDispose {
            successSound.release()
        }
    }

    fun playSuccessSound() {
        successSound.start()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            LetterTracingView(letter, touchPoints) {
                isCompleted = true
                playSuccessSound()
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { touchPoints.clear(); isCompleted = false }) {
                Text("Erase")
            }
            if (isCompleted) {
                Button(onClick = {
                    touchPoints.clear()
                    isCompleted = false
                    onNext()
                }) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun LetterTracingView(letter: Char, touchPoints: MutableList<Offset>, onComplete: () -> Unit) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow)
    )
    val totalDots = 300
    val completed = remember { mutableStateOf(false) }

    // Stores the image size to detect valid touch areas
    var imageBounds by remember { mutableStateOf<Pair<Float, Float>?>(null) }

    Box(modifier = Modifier.size(400.dp), contentAlignment = Alignment.Center) {

        Image(
            painter = painterResource(id = getLetterImage(letter)),
            contentDescription = "Letter $letter",
            modifier = Modifier
                .size(300.dp)
                .onGloballyPositioned { layoutCoordinates ->
                    imageBounds = layoutCoordinates.size.width.toFloat() to layoutCoordinates.size.height.toFloat()
                }
        )


        Canvas(
            modifier = Modifier
                .size(300.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, _ ->
                        change.consume()


                        val touchX = change.position.x
                        val touchY = change.position.y


                        val (imageWidth, imageHeight) = imageBounds ?: return@detectDragGestures


                        if (touchX in 0f..imageWidth && touchY in 0f..imageHeight) {
                            touchPoints.add(change.position)

                            if (touchPoints.size >= totalDots) {
                                completed.value = true
                                onComplete()
                            }
                        }
                    }
                }
        ) {
            drawTracedPath(touchPoints, gradientBrush)
        }
    }
}

fun getLetterImage(letter: Char): Int {
    return when (letter) {
        'A' -> R.drawable.letter_a
        'B' -> R.drawable.letter_b
        'C' -> R.drawable.letter_c
        'D' -> R.drawable.letter_d
        'E' -> R.drawable.letter_e
        'F' -> R.drawable.letter_f
        'G' -> R.drawable.letter_g
        'H' -> R.drawable.letter_h
        'I' -> R.drawable.letter_i
        'J' -> R.drawable.letter_j
        'K' -> R.drawable.letter_k
        'L' -> R.drawable.letter_l
        'M' -> R.drawable.letter_m
        'N' -> R.drawable.letter_n
        'O' -> R.drawable.letter_o
        'P' -> R.drawable.letter_p
        'Q' -> R.drawable.letter_q
        'R' -> R.drawable.letter_r
        'S' -> R.drawable.letter_s
        'T' -> R.drawable.letter_t
        'U' -> R.drawable.letter_u
        'V' -> R.drawable.letter_v
        'W' -> R.drawable.letter_w
        'X' -> R.drawable.letter_x
        'Y' -> R.drawable.letter_y
        'Z' -> R.drawable.letter_z
        else -> R.drawable.letter_a
    }
}

fun DrawScope.drawTracedPath(touchPoints: List<Offset>, brush: Brush) {
    if (touchPoints.isNotEmpty()) {
        drawPath(
            path = Path().apply {
                touchPoints.forEachIndexed { index, offset ->
                    if (index == 0) moveTo(offset.x, offset.y) else lineTo(offset.x, offset.y)
                }
            },
            brush = brush,
            style = Stroke(width = 120f, cap = StrokeCap.Round)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTraceApp() {
    LetterTracingApp(navController = rememberNavController())
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    AppNavHost(navController)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(navController = rememberNavController())
}

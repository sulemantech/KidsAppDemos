package com.example.animationdemo.activities

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.animationdemo.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(navController = rememberNavController())
        }
    }
}

@Composable
fun LetterTracingApp() {
    var currentLetterIndex by remember { mutableIntStateOf(0) }
    val letters = ('A'..'Z').toList()

    if (currentLetterIndex < letters.size) {
        LetterTracingScreen(letter = letters[currentLetterIndex]) {
            currentLetterIndex++
        }
    } else {
        Text(
            text = "Congratulations! 🎉",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxSize()
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

    Box(modifier = Modifier.size(400.dp), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = getLetterImage(letter)),
            contentDescription = "Letter $letter",
            modifier = Modifier.size(300.dp)
        )

        Canvas(
            modifier = Modifier
                .size(300.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, _ ->
                        change.consume()
                        touchPoints.add(change.position)

                        if (touchPoints.size >= totalDots) {
                            completed.value = true
                            onComplete()
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
    LetterTracingApp()
}
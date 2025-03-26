package com.example.animationdemo.activities

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(4000) // Splash screen delay
        isVisible = false
        delay(1500) // Wait for the transition to complete
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true } // Remove splash from back stack
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        exit = slideOutHorizontally(
            targetOffsetX = { -it }, // Moves left
            animationSpec = tween(durationMillis = 1000) // Slow transition
        ) + fadeOut(animationSpec = tween(durationMillis = 800)) // Smooth fade
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF7CE2EF)),
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
                    iterations = LottieConstants.IterateForever
                )
            }
        }

    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen() }
        composable("LetterTracingApp") { LetterTracingApp() }
    }
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

package com.example.animationdemo.activities

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.*

@Composable
fun DetailsScreen(navController: NavController) {
    var isVisible by remember { mutableStateOf(true) }

    // Load Lottie animation
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.example.animationdemo.R.raw.bollon_pop))
    val progress by animateLottieCompositionAsState(composition)

    AnimatedContent(targetState = isVisible) { visible ->
        if (visible) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "This is an Animated Screen",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { navController.navigate("DetailsAnimation") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Go to Animated Content Screen")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskScreen() {
    DetailsScreen(navController = rememberNavController())
}

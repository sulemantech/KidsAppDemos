package com.example.animationdemo.activities

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.*
import com.example.animationdemo.R

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import kotlin.io.path.Path
import kotlin.io.path.moveTo

@Composable
fun DetailsAnimation(navHostController: NavHostController) {
    var isPlaying by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Successful", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

        val confettiComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.confetti))
        val confettiProgress by animateLottieCompositionAsState(
            composition = confettiComposition,
            isPlaying = isPlaying,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(
            composition = confettiComposition,
            progress = { confettiProgress },
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))


        val rocketComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.rocket))
        val rocketProgress by animateLottieCompositionAsState(
            composition = rocketComposition,
            isPlaying = isPlaying,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(
            composition = rocketComposition,
            progress = { rocketProgress },
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))


        Button(onClick = { isPlaying = !isPlaying }) {
            Text(if (isPlaying) "Pause Animations" else "Play Animations")
        }
    }
}


@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isButtonClicked by remember { mutableStateOf(false) }

    val buttonScale by animateFloatAsState(if (isButtonClicked) 1.2f else 1f)
    val buttonColor by animateColorAsState(if (isButtonClicked) Color.Green else Color.Blue)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Infinite Rotation Animation
        InfiniteRotatingIcon()

        Spacer(modifier = Modifier.height(16.dp))

        // Username Input
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Input with Visibility Animation
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else AsteriskPasswordVisualTransformation(),
            trailingIcon = {
                Text(
                    if (isPasswordVisible) "Hide" else "Show",
                    modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Login Button with Animation
        Button(
            onClick = {
                isButtonClicked = !isButtonClicked
            },
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(scaleX = buttonScale, scaleY = buttonScale)
                .background(buttonColor, RoundedCornerShape(10.dp))
        ) {
            Text("Login", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Navigate to Register with Shared Transition
        Text(
            "New here? Register",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.clickable {
                navController.navigate("register")
            }
        )
    }
}

@Composable
fun InfiniteRotatingIcon() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your icon
        contentDescription = "Rotating Icon",
        modifier = Modifier
            .size(50.dp)
            .rotate(rotation)
    )
}

@Composable
fun RegisterScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Email Input
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else AsteriskPasswordVisualTransformation(),
            trailingIcon = {
                Text(
                    if (isPasswordVisible) "Hide" else "Show",
                    modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))


        BounceButtonEffect(onClick = {}) {
            Text("Register", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Already have an account? Login",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.clickable {
                navController.navigate("login")
            }
        )
    }
}

@Composable
fun BounceButtonEffect(onClick: () -> Unit, content: @Composable () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Button(
        onClick = {
            isPressed = true
            onClick()
            isPressed = false
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
        modifier = Modifier
            .scale(scale)
            .padding(10.dp)
    ) {
        content()
    }
}

class AsteriskPasswordVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString("*".repeat(text.text.length)),
            OffsetMapping.Identity
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailsAnimation() {
    DetailsAnimation(navHostController = rememberNavController())
}

package com.example.animationdemo.activities

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animationdemo.data.Task

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        NavigationMenu()


        ProfileSection()


        AnimatedCard(navController)


        DragDropBox()


        Button(
            onClick = { navController.navigate("details") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go to Animated Content Screen")
        }
    }
}

@Composable
fun NavigationMenu() {
    var isVisible by remember { mutableStateOf(true) }

    Column {
        Button(onClick = { isVisible = !isVisible }) {
            Text(if (isVisible) "Hide Menu" else "Show Menu")
        }

        AnimatedVisibility(visible = isVisible) {
            Column(Modifier.background(Color.Gray).padding(8.dp)) {
                Text("Home", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                Text("Profile", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
                Text("Settings", fontSize = 18.sp, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun ProfileSection() {
    var expanded by remember { mutableStateOf(false) }

    val size by animateDpAsState(if (expanded) 150.dp else 100.dp)
    val color by animateColorAsState(if (expanded) Color.Red else Color.Blue)
    val rotation by animateFloatAsState(if (expanded) 360f else 0f)

    Box(
        modifier = Modifier
            .size(size)
            .background(color, shape = CircleShape)
            .clickable { expanded = !expanded }
            .graphicsLayer(rotationZ = rotation),
        contentAlignment = Alignment.Center
    ) {
        Text("Profile", color = Color.White, fontSize = 16.sp)
    }
}

@Composable
fun AnimatedCard(navController: NavController) {
    var isExpanded by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(if (isExpanded) 1.2f else 1f)
    val offset by animateOffsetAsState(if (isExpanded) Offset(10f, 10f) else Offset(0f, 0f))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .graphicsLayer {
                translationX = offset.x
                translationY = offset.y
                scaleX = scale
                scaleY = scale
            }
            .clickable { isExpanded = !isExpanded },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Click to Expand", fontSize = 18.sp)
        }
    }
}

@Composable
fun DragDropBox() {
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }

    Box(
        modifier = Modifier
            .size(100.dp)
            .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) }
            .background(Color.Green, shape = RoundedCornerShape(8.dp))
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta -> offset = offset.copy(x = offset.x + delta) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text("Drag Me", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskItem() {
    HomeScreen(navController = rememberNavController())
}

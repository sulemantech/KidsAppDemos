package com.example.animationdemo.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.animationdemo.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.lazy.grid.*
import kotlinx.coroutines.delay
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator(navController = rememberNavController())
        }
    }
}

@Composable
fun AppNavigator(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(
                onFinished = {
                    navController.navigate("learn") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }
        composable("learn") {
            MainScreen(navController)
        }
        composable("dua/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
            DuaScreen(index = index, navController = navController)
        }

        composable("home") {
            PlaceholderScreen(title = "Home Screen")
        }
        composable("favorites") {
            PlaceholderScreen(title = "Favorites Screen")
        }
        composable("profile") {
            PlaceholderScreen(title = "Profile Screen")
        }
        composable("SettingsScreen") {
            SettingsScreen(navController)
        }

    }
}

@Composable
fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

    @Composable
fun SplashScreen(onFinished: () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val backgroundColor = colorResource(id = R.color.splash_bg)

    SideEffect {
        systemUiController.setStatusBarColor(color = backgroundColor)
    }
    val poppins = FontFamily(Font(R.font.inter_ui_regular))

    val poppinsFamily = FontFamily(
        Font(R.font.inter_ui_regular, FontWeight.Normal),
        Font(R.font.inter_ui_medium, FontWeight.Medium),
        Font(R.font.inter_ui_bold, FontWeight.Bold)
    )


    LaunchedEffect(true) {
        delay(2000)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.splash_bg)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .border(2.dp, Color.Gray, RoundedCornerShape(4.8.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(150.dp)
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            Text(
                text = "Dua App",
                fontSize = 24.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Duas Made Easy for Kids!",
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
            )


            Spacer(modifier = Modifier.height(24.dp))

            CircularProgressIndicator(
                color = Color.LightGray,
                strokeWidth = 4.dp,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(
                onHomeClick = { navController.navigate("home") },
                onStarClick = { navController.navigate("favorites") },
                onUserClick = { navController.navigate("profile") },
                onSettingsClick = { navController.navigate("SettingsScreen") }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LearnWithEaseScreen(navController)
        }
    }
}

data class DuaItem(
    val imageRes: Int,
    val onClick: () -> Unit
)

@Composable
fun LearnWithEaseScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    val backgroundColor = colorResource(id = R.color.blue_bg)

    SideEffect {
        systemUiController.setStatusBarColor(color = backgroundColor)
    }


    val duaList = (0 until 21).map { index ->
        val drawableName = if (index == 0) "card" else "card${index + 1}"
        val resId = remember(drawableName) {
            val res = R.drawable::class.java.getDeclaredField(drawableName).getInt(null)
            res
        }
        DuaItem(
            imageRes = resId,
            onClick = { navController.navigate("dua/$index") }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopBar(
            onMenuClick = { },
            onRightIconClick = { }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.blue_bg))
                .padding(16.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Header()
            }

            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(44.dp))
            }

            items(duaList) { dua ->
                DuaCard(imageRes = dua.imageRes, onClick = dua.onClick)
            }
        }
    }
}


@Composable
fun Header() {
    val itimRegular = FontFamily(Font(R.font.itim_regular, FontWeight.Medium))
    val interRegular = FontFamily(Font(R.font.inter_ui_regular, FontWeight.Normal))

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset(x = 16.dp, y = 16.dp)
                .size(width = 278.dp, height = 187.dp)
                .background(Color(0xFFD7D7D7), RoundedCornerShape(28.dp))
        )
        Box(
            modifier = Modifier
                .offset(x = 8.dp, y = 8.dp)
                .size(width = 278.dp, height = 187.dp)
                .background(Color(0xFFECECEC), RoundedCornerShape(28.dp))
        )
        Box(
            modifier = Modifier
                .size(width = 278.dp, height = 187.dp)
                .background(Color(0xFFFFF176), RoundedCornerShape(28.dp))
                .padding(26.dp)
        ) {
            Column {
                Text(
                    text = " Learn \n With \n Ease",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontFamily = itimRegular,
                    fontWeight = FontWeight.Medium,
                )
            }
            Box(
                modifier = Modifier
                    .size(142.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 56.dp, y = (-1).dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Icon\nAccording to\nthe quote",
                    fontSize = 16.sp,
                    color = colorResource(R.color.grey),
                    fontFamily = interRegular,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DuaCard(
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(
                onClick = onClick,
                indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() }
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Dua card image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Composable
fun CustomBottomNavigationBar(
    onHomeClick: () -> Unit,
    onStarClick: () -> Unit,
    onUserClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(colorResource(R.color.bottom_nav))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
                //.padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavIcon(R.drawable.icon_home, onClick = onHomeClick)
            NavIcon(R.drawable.ic_star, onClick = onStarClick)
            NavIcon(R.drawable.ic_user, onClick = onUserClick)
            NavIcon(R.drawable.ic_settings, onClick = onSettingsClick)
        }
    }
}
 //
@Composable
fun NavIcon(@DrawableRes iconRes: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(68.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(41.dp),
        )
    }
}

@Composable
fun CustomTopBar(
    onMenuClick: () -> Unit,
    onRightIconClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(colorResource(R.color.blue_bg))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuClick) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                modifier = Modifier.size(30.dp)
            )
        }

        IconButton(onClick = onRightIconClick) {
            Image(
                painter = painterResource(id = R.drawable.ic_bookmark),
                contentDescription = "Notification",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LearnWithEaseScreenPreview() {
    LearnWithEaseScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(onFinished = {})
}
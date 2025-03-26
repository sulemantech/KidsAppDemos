package com.example.animationdemo.viewmodel

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "TaskScreen") {
       // composable("TaskScreen") { MainActivity(navController) }
    }
}

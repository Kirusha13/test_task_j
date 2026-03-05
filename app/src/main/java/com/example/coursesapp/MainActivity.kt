package com.example.coursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coursesapp.core.R as CoreR
import com.example.coursesapp.core.ui.theme.BackgroundDark
import com.example.coursesapp.core.ui.theme.BottomNavBackground
import com.example.coursesapp.core.ui.theme.CoursesAppTheme
import com.example.coursesapp.core.ui.theme.PrimaryGreen
import com.example.coursesapp.core.ui.theme.TextHint
import com.example.coursesapp.feature.account.AccountScreen
import com.example.coursesapp.feature.favorites.FavoritesScreen
import com.example.coursesapp.feature.login.LoginScreen
import com.example.coursesapp.feature.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesAppTheme {
                CoursesApp()
            }
        }
    }
}

data class BottomNavItem(val route: String, val label: String, val iconRes: Int)

@Composable
fun CoursesApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute != "login"

    val bottomNavItems = listOf(
        BottomNavItem("home", "Главная", CoreR.drawable.ic_home),
        BottomNavItem("favorites", "Избранное", CoreR.drawable.ic_favorites),
        BottomNavItem("account", "Аккаунт", CoreR.drawable.ic_account)
    )

    Scaffold(
        containerColor = BackgroundDark,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(containerColor = BottomNavBackground) {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(item.iconRes),
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label) },
                            selected = currentRoute == item.route,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = PrimaryGreen,
                                selectedTextColor = PrimaryGreen,
                                unselectedIconColor = TextHint,
                                unselectedTextColor = TextHint,
                                indicatorColor = BottomNavBackground
                            ),
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            composable("home") { MainScreen() }
            composable("favorites") { FavoritesScreen() }
            composable("account") { AccountScreen() }
        }
    }
}

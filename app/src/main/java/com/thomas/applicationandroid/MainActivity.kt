package com.thomas.applicationandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thomas.applicationandroid.ui.theme.ApplicationAndroidTheme
import kotlinx.serialization.Serializable

@Serializable
class HomePage

@Serializable
class FilmPage

class MainActivity : ComponentActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            ApplicationAndroidTheme {
                Scaffold(
                    bottomBar = {
                        if (currentDestination?.hasRoute<HomePage>() == false) {
                            NavigationBar {
                                NavigationBarItem(
                                    icon = {Icon(imageVector = Icons.Filled.Home, contentDescription = "Profile Icon") }, label = { Text("Edition du profil") },
                                    selected = currentDestination?.hasRoute<FilmPage>() == true,
                                    onClick = { navController.navigate(FilmPage()) })
                                NavigationBarItem(
                                    icon = {
                                        var imageVector = Icons.Filled.Menu
                                        var contentDescription = "Localized description"
                                    }, label = { Text("Edition du profil") },
                                    selected = currentDestination?.hasRoute<FilmPage>() == true,
                                    onClick = { navController.navigate(FilmPage()) })
                                NavigationBarItem(
                                    icon = { }, label = { Text("Edition du profil") },
                                    selected = currentDestination?.hasRoute<FilmPage>() == true,
                                    onClick = { navController.navigate(FilmPage()) })
                            }
                        } else {
                        }

                    }
                )
                { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = HomePage(),
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<HomePage> { Profil(windowSizeClass, navController) }
                        composable<FilmPage> { Film() }
                    }


                }
            }
        }
    }
}


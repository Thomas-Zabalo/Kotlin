package com.thomas.applicationandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
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

@Serializable
class SeriePage

@Serializable
class ActeurPage

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel()

        setContent {
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            var search by remember { mutableStateOf(TextFieldValue("")) }

            ApplicationAndroidTheme {
                Scaffold(
                    topBar = {

                        if (currentDestination?.hasRoute<HomePage>() == false) {
                            CenterAlignedTopAppBar(
                                title = {
                                    TextField(
                                        value = search,
                                        onValueChange = {
                                            search = it
                                            // Utiliser le ViewModel pour effectuer la recherche
                                            viewModel.getFilmsBySearch(
                                                apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                                query = search.text
                                            )
                                        },
                                        placeholder = { Text("Rechercher un film...") },
                                        singleLine = true,
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    )
                                }
                            )
                        }
                    },
                    bottomBar = {
                        if (currentDestination?.hasRoute<HomePage>() == false) {
                            NavigationBar {
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.Movie,
                                            contentDescription = "Profile Icon"
                                        )
                                    }, label = { Text("Films") },
                                    selected = currentDestination.hasRoute<FilmPage>(),
                                    onClick = { navController.navigate(FilmPage()) })
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.Tv,
                                            contentDescription = "Profile Icon"
                                        )
                                    }, label = { Text("Séries") },
                                    selected = currentDestination.hasRoute<SeriePage>(),
                                    onClick = { navController.navigate(SeriePage()) })
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Filled.AccountCircle,
                                            contentDescription = "Profile Icon"
                                        )
                                    }, label = { Text("Acteurs") },
                                    selected = currentDestination.hasRoute<ActeurPage>(),
                                    onClick = { navController.navigate(ActeurPage()) })
                            }
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
                        composable<FilmPage> {
                            Film(
                                viewModel = viewModel,
                                api_key = "e4009b8963dbfe389c28cb3b4d0c309e"
                            )
                        }
                        composable<SeriePage> { Serie() }
                        composable<ActeurPage> { Acteur() }
                    }


                }
            }
        }
    }
}


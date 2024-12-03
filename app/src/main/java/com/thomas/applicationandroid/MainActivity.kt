package com.thomas.applicationandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.thomas.applicationandroid.ui.theme.ApplicationAndroidTheme
import kotlinx.serialization.Serializable

@Serializable
class HomePage

@Serializable
class FilmPage

@Serializable
class FilmDetail(val id: String)

@Serializable
class SeriePage

@Serializable
class SerieDetail(val id: Int)

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
            var isSearchMode by remember { mutableStateOf(false) }

            ApplicationAndroidTheme {
                Scaffold(
                    // TopBar : affiché selon la destination actuelle
                    topBar = {
                        if (currentDestination?.hasRoute<HomePage>() == false && !currentDestination.hasRoute<SerieDetail>() && !currentDestination.hasRoute<FilmDetail>()) {
                            TopAppBar(title = {
                                if (!isSearchMode) {
                                    Text(
                                        "Android TMDB app",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                } else {
                                    TextField(
                                        value = search,
                                        onValueChange = {
                                            search = it
                                            when {
                                                currentDestination.hasRoute<FilmPage>() -> {
                                                    viewModel.getFilmsBySearch(
                                                        apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                                        query = search.text
                                                    )
                                                }

                                                currentDestination.hasRoute<SeriePage>() -> {
                                                    viewModel.getTvBySearch(
                                                        apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                                        query = search.text
                                                    )
                                                }

                                                currentDestination.hasRoute<ActeurPage>() -> {
                                                    viewModel.getActeurBySearch(
                                                        apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                                        query = search.text
                                                    )
                                                }
                                            }
                                        },
                                        placeholder = {
                                            when {
                                                currentDestination.hasRoute<FilmPage>() -> Text(
                                                    "Rechercher un film..."
                                                )

                                                currentDestination.hasRoute<SeriePage>() -> Text(
                                                    "Rechercher une série..."
                                                )

                                                currentDestination.hasRoute<ActeurPage>() -> Text(
                                                    "Rechercher un(e) acteur/rice..."
                                                )

                                                else -> Text("Rechercher...")
                                            }
                                        },
                                        singleLine = true,
                                        modifier = Modifier
                                            .fillMaxWidth() // Prend toute la largeur disponible
                                            .padding(end = 60.dp) // Padding à gauche seulement
                                    )
                                }
                            }, actions = {
                                if (!isSearchMode) {
                                    // Icône de recherche visible en mode "titre"
                                    Icon(imageVector = Icons.Default.Search,
                                        contentDescription = "Rechercher",
                                        modifier = Modifier.clickable { isSearchMode = true })
                                } else {
                                    // Icône pour quitter le mode recherche
                                    Icon(imageVector = Icons.Default.Close,
                                        contentDescription = "Fermer la recherche",
                                        modifier = Modifier.clickable {
                                            isSearchMode = false
                                            search = TextFieldValue("") // Réinitialise la recherche
                                            when {
                                                currentDestination.hasRoute<FilmPage>() -> {
                                                    // Chargez les films par défaut
                                                    viewModel.getFilmsBySearch(
                                                        apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                                        query = "" // Recherche vide pour obtenir tous les films
                                                    )
                                                }

                                                currentDestination.hasRoute<SeriePage>() -> {
                                                    // Chargez les séries par défaut
                                                    viewModel.getTvBySearch(
                                                        apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                                        query = "" // Recherche vide pour obtenir toutes les séries
                                                    )
                                                }

                                                currentDestination.hasRoute<ActeurPage>() -> {
                                                    // Chargez les acteurs par défaut
                                                    viewModel.getActeurBySearch(
                                                        apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                                        query = "" // Recherche vide pour obtenir tous les acteurs
                                                    )
                                                }
                                            }
                                        }

                                    )
                                }
                                // Ajout de l'icône de cœur
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favoris",
                                    modifier = Modifier.padding(end = 16.dp)
                                )
                            })
                        }
                    }, bottomBar = {
                        if (currentDestination?.hasRoute<HomePage>() == false) {
                            NavigationBar {
                                NavigationBarItem(icon = {
                                    Icon(
                                        imageVector = Icons.Filled.Movie,
                                        contentDescription = "Profile Icon"
                                    )
                                },
                                    label = { Text("Films") },
                                    selected = currentDestination.hasRoute<FilmPage>(),
                                    onClick = { navController.navigate(FilmPage()) })
                                NavigationBarItem(icon = {
                                    Icon(
                                        imageVector = Icons.Filled.Tv,
                                        contentDescription = "Profile Icon"
                                    )
                                },
                                    label = { Text("Séries") },
                                    selected = currentDestination.hasRoute<SeriePage>(),
                                    onClick = { navController.navigate(SeriePage()) })
                                NavigationBarItem(icon = {
                                    Icon(
                                        imageVector = Icons.Filled.AccountCircle,
                                        contentDescription = "Profile Icon"
                                    )
                                },
                                    label = { Text("Acteurs") },
                                    selected = currentDestination.hasRoute<ActeurPage>(),
                                    onClick = { navController.navigate(ActeurPage()) })
                            }
                        }
                    }) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = HomePage(),
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<HomePage> { Profil(windowSizeClass, navController) }
                        composable<FilmPage> {
                            Films(
                                viewModel = viewModel,
                                apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                navController,
                                windowSizeClass
                            )
                        }
                        composable<FilmDetail> { backStackEntry ->
                            val movieDetail: FilmDetail = backStackEntry.toRoute()
                            MovieDetail(
                                viewModel = viewModel,
                                id = movieDetail.id,
                                apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                navController
                            )
                        }
                        composable<SeriePage> {
                            Series(
                                viewModel = viewModel,
                                apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                navController,
                                windowSizeClass
                            )
                        }
                        composable<SerieDetail> { backStackEntry ->
                            val tvDetail: SerieDetail = backStackEntry.toRoute()
                            TvDetail(
                                viewModel = viewModel,
                                id = tvDetail.id,
                                apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                navController
                            )
                        }
                        composable<ActeurPage> {
                            Acteur(
                                viewModel = viewModel,
                                apikey = "e4009b8963dbfe389c28cb3b4d0c309e",
                                windowSizeClass
                            )
                        }


                    }


                }
            }
        }
    }
}


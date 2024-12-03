package com.thomas.applicationandroid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun Films(
    viewModel: MainViewModel,
    apikey: String,
    navController: NavController,
    classes: WindowSizeClass
) {
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) {
        viewModel.getFilmsInitiaux(apikey)
    }

    val widthClass = classes.windowWidthSizeClass

    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        modifier = Modifier.padding(12.dp) // Padding général pour la grille
    ) {
        items(movies.take(9).size) { index ->
            val movie = movies[index]
            Column(
                modifier = Modifier.padding(8.dp), // Padding autour de chaque film

            ) {
                if (widthClass == WindowWidthSizeClass.COMPACT) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w300${movie.poster_path}",
                        contentDescription = "Image du film ${movie.title}",
                        modifier = Modifier
                            .fillMaxWidth() // Utilise toute la largeur disponible
                            .padding(bottom = 8.dp) // Padding en bas pour espacement
                            .clip(RoundedCornerShape(8.dp)) // Applique des coins arrondis à l'image
                            .clickable(onClick = {
                                navController.navigate(FilmDetail(movie.id))
                            }),

                        )
                } else {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w300${movie.poster_path}",
                        contentDescription = "Image du film ${movie.title}",
                        modifier = Modifier
                            .fillMaxWidth() // Utilise toute la largeur disponible
                            .padding(bottom = 8.dp) // Padding en bas pour espacement
                            .clip(RoundedCornerShape(8.dp)) // Applique des coins arrondis à l'image
                            .height(150.dp)
                            .clickable(onClick = {
                                navController.navigate(FilmDetail(movie.id))
                            }),
                        contentScale = ContentScale.Crop
                    )
                }

                // Titre centré et avec style
                Text(
                    text = movie.title,
                    modifier = Modifier.padding(top = 4.dp),
                    style = TextStyle(
                        color = Color.Black, // Applique une couleur noire au texte
                        fontSize = 16.sp, // Applique une taille de texte de 16 sp
                        fontWeight = FontWeight.Bold,
                    ),
                )

                // Date de sortie avec un style différent et texte centré
                Text(
                    text = movie.release_date,
                    modifier = Modifier.padding(top = 4.dp),
                    style = TextStyle(
                        color = Color.Gray, // Applique une couleur grise au texte
                        fontSize = 12.sp, // Applique une taille de texte de 12 sp
                        textAlign = TextAlign.Center // Centre le texte horizontalement
                    )
                )
            }
        }
    }
}

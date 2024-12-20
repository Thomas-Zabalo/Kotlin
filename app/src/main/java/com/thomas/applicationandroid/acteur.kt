package com.thomas.applicationandroid

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun Acteur(viewModel: MainViewModel, apikey: String, classes: WindowSizeClass) {
    val actors by viewModel.actors.collectAsState()

    if (actors.isEmpty()) {
        viewModel.getActeurInitiaux(apikey)
    }


    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        modifier = Modifier.padding(12.dp) // Padding général pour la grille
    ) {
        items(actors.take(12).size) { index ->
            val actor = actors[index]
            Column(
                modifier = Modifier.padding(8.dp), // Padding autour de chaque film

            ) {
                val widthClass = classes.windowWidthSizeClass

                if (widthClass == WindowWidthSizeClass.COMPACT) {
                    if (actor.profile_path == null) {
                        Image(
                            painter = painterResource(id = R.drawable.person),
                            contentDescription = "Image d'une personne inconnue",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    } else {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w300${actor.profile_path}",
                            contentDescription = "Image de l'acteur ${actor.name}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                } else {
                    if (actor.profile_path == null) {
                        Image(
                            painter = painterResource(id = R.drawable.person),
                            contentDescription = "Image d'une personne inconnue",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .height(150.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w300${actor.profile_path}",
                            contentDescription = "Image de l'acteur ${actor.name}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .height(150.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                // Titre centré et avec style
                Text(
                    text = actor.name,
                    modifier = Modifier.padding(top = 4.dp),
                    style = TextStyle(
                        color = Color.Black, // Applique une couleur noire au texte
                        fontSize = 16.sp, // Applique une taille de texte de 16 sp
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
        }
    }
}

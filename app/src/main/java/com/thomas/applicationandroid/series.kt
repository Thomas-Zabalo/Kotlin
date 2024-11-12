package com.thomas.applicationandroid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Series(viewModel: MainViewModel, apikey: String, navController: NavController) {
    val series by viewModel.series.collectAsState()

    if (series.isEmpty()) {
        viewModel.getTvInitiaux(apikey)
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(12.dp) // Padding général pour la grille
    ) {
        items(series.size) { index ->
            val serie = series[index]
            Column(
                modifier = Modifier.padding(8.dp), // Padding autour de chaque film

            ) {
                // Image avec hauteur ajustée et largeur maximisée
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w300${serie.poster_path}",
                    contentDescription = "Image du film ${serie.name}",
                    modifier = Modifier
                        .fillMaxWidth() // Utilise toute la largeur disponible
                        .padding(bottom = 8.dp) // Padding en bas pour espacement
                        .clip(RoundedCornerShape(8.dp)) // Applique des coins arrondis à l'image
                        .clickable(onClick = {
                            navController.navigate(SerieDetail(serie.id))
                        })
                )

                // Titre centré et avec style
                Text(
                    text = serie.name,
                    modifier = Modifier.padding(top = 4.dp),
                    style = TextStyle(
                        color = Color.Black, // Applique une couleur noire au texte
                        fontSize = 16.sp, // Applique une taille de texte de 16 sp
                        fontWeight = FontWeight.Bold,
                    ),
                )
                Text(
                    text = serie.first_air_date,
                    modifier = Modifier.padding(end = 4.dp),
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}


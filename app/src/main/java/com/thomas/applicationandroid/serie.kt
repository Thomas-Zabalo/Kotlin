package com.thomas.applicationandroid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import coil.compose.AsyncImage

@Composable
fun TvDetail(viewModel: MainViewModel, id: Int, apikey: String) {
    viewModel.getTvById(id, apikey)
    val serieDetail by viewModel.serieDetails.collectAsState()

    Column(
        modifier = Modifier.padding(12.dp) // Padding général autour du contenu
    ) {
        // Affichage de l'image du film
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w300${serieDetail?.poster_path}",
            contentDescription = "Image du film ${serieDetail?.name}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(8.dp)) // Coins arrondis pour l'image
        )

        // Titre du film
        serieDetail?.name?.let {
            Text(
                text = it,
                modifier = Modifier.padding(top = 4.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }

        // Année de sortie du film
        serieDetail?.first_air_date?.let {
            Text(
                text = "Release date: ${it.take(4)}", // Prendre les 4 premiers caractères pour l'année
                modifier = Modifier.padding(top = 4.dp),
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center // Centrer le texte
                )
            )
        }

        // Note du film
        serieDetail?.vote_average?.let {
            Text(
                text = "Rating: ${it}/10",
                modifier = Modifier.padding(top = 4.dp),
                style = TextStyle(
                    color = Color.Green,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            )
        }

        // Liste des acteurs du film (affiche les 5 premiers acteurs)
        serieDetail?.credits?.cast?.take(5)?.let { castList ->
            Text(
                text = "Cast:",
                modifier = Modifier.padding(top = 8.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            )

            castList.forEach { castMember ->
                castMember?.name?.let { castName ->
                    Text(
                        text = castName,
                        modifier = Modifier.padding(top = 2.dp),
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 14.sp,
                        )
                    )
                }
            }
        }

        // Description du film
        serieDetail?.overview?.let {
            Text(
                text = "Overview: $it",
                modifier = Modifier.padding(top = 8.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                )
            )
        }
    }
}
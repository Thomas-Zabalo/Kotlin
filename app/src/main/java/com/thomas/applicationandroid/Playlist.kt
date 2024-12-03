package com.thomas.applicationandroid


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage

@Composable
fun Playlist(viewModel: MainViewModel) {
    val playlists by viewModel.playlist.collectAsState()


    if (playlists.isEmpty()) {
        viewModel.getfetchPlaylist()
    }
    viewModel.getfetchPlaylist()

    Row {
        AsyncImage(
            model = "file:///android_asset/images/${playlists.cover}",
            contentDescription = "Image de l'acteur",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(3.dp, Color.Gray, CircleShape)
        )
        // Titre centré et avec style
        playlists.let {
            Text(
                text = it.title,
                modifier = Modifier.padding(top = 4.dp),
                style = TextStyle(
                    color = Color.Black, // Applique une couleur noire au texte
                    fontSize = 16.sp, // Applique une taille de texte de 16 sp
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        modifier = Modifier.padding(12.dp) // Padding général pour la grille
    ) {
        items(playlists.take(9).size) { index ->
            val playlist = playlists[index]
            Column(
                modifier = Modifier.padding(8.dp), // Padding autour de chaque film

            ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w300${playlists.tracks.data}",
                        contentDescription = "Image du film ${playlists.tracks.data}",
                        modifier = Modifier
                            .fillMaxWidth() // Utilise toute la largeur disponible
                            .padding(bottom = 8.dp) // Padding en bas pour espacement
                            .clip(RoundedCornerShape(8.dp)) // Applique des coins arrondis à l'image
                        )

                // Titre centré et avec style
                Text(
                    text = playlist.tracks.data.name,
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
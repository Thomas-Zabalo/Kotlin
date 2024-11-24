package com.thomas.applicationandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetail(viewModel: MainViewModel, id: String, apikey: String, navController: NavController) {

    // Fetch movie details by calling the viewModel function
    viewModel.getFilmsById(id, apikey)
    val movieDetail by viewModel.movieDetails.collectAsState()

    // Define the Column layout with vertical scroll for flexibility
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()) // Make the content scrollable
    ) {
        TopAppBar(
            title = {
                Text(text = movieDetail?.title ?: "Movie Details")
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        // Display the movie poster with full width and fixed height
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movieDetail?.poster_path}",
            contentDescription = "Poster of ${movieDetail?.title}",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp)) // Space between elements

        // Display the movie title if available
        movieDetail?.title?.let {
            Text(
                text = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp)) // Space between title and next item

        // Display the movie rating with a star icon
        movieDetail?.vote_average?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Star icon to represent rating
                Image(
                    painter = painterResource(R.drawable.baseline_star_24),
                    contentDescription = "Rating stars",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "Rating: ${it}/10",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = TextStyle(
                        color = Color(0xFFFFEB3B), // Custom color for rating
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        // Display movie overview if available
        movieDetail?.overview?.let {
            Text(
                text = "Overview:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = it,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                )
            )
        }

        // Title for Casting
        Text(
            text = "Casting:",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        // Displaying actors (up to 9 actors)
        movieDetail?.credits?.cast?.take(9)?.let { castList ->

            // Iterate over the castList in chunks of 3 actors
            castList.chunked(3).forEach { rowActors ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp), // Spacing between each actor
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    rowActors.forEach { actor ->
                        // Display actor's profile image and name
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .weight(1f) // Ensure actors take equal space
                                .padding(8.dp) // Padding around each actor's profile
                        ) {
                            // Actor's image
                            if (actor != null) {
                                actor.profile_path?.let {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w185$it",
                                        contentDescription = actor.name,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(120.dp)
                                            .clip(RoundedCornerShape(8.dp)) // Rounded corners
                                            .padding(bottom = 8.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                            // Actor's name
                            if (actor != null) {
                                actor.name?.let {
                                    Text(
                                        text = it,
                                        style = TextStyle(
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Normal
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

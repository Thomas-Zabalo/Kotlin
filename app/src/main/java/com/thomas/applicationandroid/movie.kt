package com.thomas.applicationandroid

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun MovieDetail(viewModel: MainViewModel, id: String, api_key: String) {

    viewModel.getFilmsById(id, api_key)
    val movieDetail by viewModel.movieDetails.collectAsState()



    Text(text = "Title: ${movieDetail?.title}")


}
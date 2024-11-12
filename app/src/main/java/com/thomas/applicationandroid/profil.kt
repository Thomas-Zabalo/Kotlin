package com.thomas.applicationandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun Presentation(name: String, surname: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name $surname", fontSize = 30.sp,
        modifier = Modifier.padding(12.dp)
    )
    Text(
        text = "Etudiant en 3eme année de BUT MMI",
    )
}

@Composable
fun Reseaux(painterResource: Int, contentDescription: String) {
    Column(
        modifier = Modifier.padding(
            16.dp,
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = painterResource),
                contentDescription = contentDescription,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "thomas.zabalo@gmail.com",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.linkedin_110),
                contentDescription = contentDescription,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "https://www.linkedin.com/in/thomas-zabalo-62627a256/",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun MonImage(painterResource: Int, contentDescription: String) {
    Image(
        painter = painterResource(id = painterResource),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .border(3.dp, Color.Gray, CircleShape)
    )
}
@Composable
fun MonBouton(navController: NavController) {
    Button(onClick = { navController.navigate(FilmPage())}) {
        Text(
            text = "Démarrer",
        )
    }
}

@Composable
fun Main() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        MonImage(painterResource = R.drawable.moi, contentDescription = "Image de Thomas ZABALO")
        Presentation(name = "Thomas", surname = "ZABALO")
    }
}

@Composable
fun Profil(classes: WindowSizeClass, navController: NavHostController) {
    val heightClass = classes.windowHeightSizeClass
    val widthClass = classes.windowWidthSizeClass

    if (widthClass == WindowWidthSizeClass.COMPACT) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(56.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Main()
            Reseaux(
                painterResource = R.drawable.baseline_email_24,
                contentDescription = "Image mail"
            )
            MonBouton(navController)
        }

    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Main()
            Spacer(modifier = Modifier.width(80.dp))
            Column {
                Reseaux(
                    painterResource = R.drawable.baseline_email_24,
                    contentDescription = "Image mail"
                )
                MonBouton(navController)
            }

        }
    }
}

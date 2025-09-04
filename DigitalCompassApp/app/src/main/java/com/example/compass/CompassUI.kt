package com.example.compass

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CompassScreen(viewModel: CompassViewModel) {
    val azimuth by viewModel.azimuth

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        CompassNeedle(azimuth)
        Text(
            text = "${azimuth.toInt()}°",
            color = Color.White,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.align(Alignment.BottomCenter).padding(32.dp)
        )
    }
}

@Composable
fun CompassNeedle(azimuth: Float) {
    val rotation = -azimuth
    Image(
        painter = painterResource(id = R.drawable.compass),
        contentDescription = "Compass",
        modifier = Modifier
            .size(300.dp)
            .rotate(rotation)
    )
}
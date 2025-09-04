package com.abmn.compass

import ads_mobile_sdk.h4
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

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
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.BottomCenter).padding(32.dp)
        )
    }
}
@Preview
@Composable
fun CompassNeedle(azimuth: Float) {
    val rotation = -azimuth // Needle rotates in opposite direction
    Image(
        painter = painterResource(id = R.drawable.compass), // Use your compass image
        contentDescription = "Compass",
        modifier = Modifier
            .size(300.dp)
            .rotate(rotation)
    )
}

package com.example.compass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: CompassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = CompassViewModel(this)

        setContent {
            MaterialTheme {
                CompassScreen(viewModel)
            }
        }
    }
}
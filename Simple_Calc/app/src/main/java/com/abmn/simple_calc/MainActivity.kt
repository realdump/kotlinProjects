package com.abmn.simple_calc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
//import com.abmn.simple_calc.ui.theme.Simple_CalcTheme
import kotlin.math.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp()
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorApp() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple Calculator") }
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                CalculatorUI(input, result, onButtonClick = { button ->
                    if (button == "=") {
                        try {
                            result = evaluateExpression(input)
                        } catch (e: Exception) {
                            result = "Error"
                        }
                        input = result
                    } else if (button == "C") {
                        input = ""
                        result = ""
                    } else {
                        input += button
                    }
                })
            }
        }
    )
}

@Composable
fun CalculatorUI(input: String, result: String, onButtonClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = input,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = result,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(32.dp))

        val buttons = listOf(
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        )

        GridLayout(buttons, onButtonClick)
    }
}

@Composable
fun GridLayout(buttons: List<String>, onButtonClick: (String) -> Unit) {
    Column {
        for (i in buttons.chunked(4)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (button in i) {
                    Button(
                        onClick = { onButtonClick(button) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                    ) {
                        Text(text = button, color = Color.White)
                    }
                }
            }
        }
    }
}

fun evaluateExpression(expression: String): String {
    // This will handle basic arithmetic expressions, using eval or a parser could be done here
    return try {
        val result = expression.replace(Regex("[^0-9+\\-*/.]"), "")
        val evalResult = eval(result)
        if (evalResult == evalResult.toInt().toDouble()) evalResult.toInt().toString() else evalResult.toString()
    } catch (e: Exception) {
        "Error"
    }
}

fun eval(expression: String): Double {
    // Simple evaluation using built-in Kotlin methods
    return when {
        expression.contains("+") -> {
            val parts = expression.split("+")
            parts[0].toDouble() + parts[1].toDouble()
        }
        expression.contains("-") -> {
            val parts = expression.split("-")
            parts[0].toDouble() - parts[1].toDouble()
        }
        expression.contains("*") -> {
            val parts = expression.split("*")
            parts[0].toDouble() * parts[1].toDouble()
        }
        expression.contains("/") -> {
            val parts = expression.split("/")
            parts[0].toDouble() / parts[1].toDouble()
        }
        else -> expression.toDouble()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorApp()
}

package com.example.trainingcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trainingcompose.ui.theme.TrainingComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrainingComposeTheme {
                val list = DataSource.list
                val appViewModel: AppViewModel = viewModel()
                val appUiState by appViewModel.appUiState.collectAsStateWithLifecycle()

                Screen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    list = list,
                    updatePrice = appViewModel::updatePrice,
                    price = appUiState.price
                )
            }
        }
    }
}

@Composable
fun Screen(modifier: Modifier, list: List<Int>, updatePrice: (Int) -> Unit, price: Int) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        TextField(
            value = "$price",
            onValueChange = {
                updatePrice(it.toInt())
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )
        TextButtonsList(modifier = Modifier.fillMaxWidth(), list = list) {
            updatePrice(it)
        }
    }
}

@Composable
fun TextButtonsList(modifier: Modifier, list: List<Int>, updatePrice: (Int) -> Unit) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 10.dp),
    ) {
        items(list) { item ->
            TextButtons(price = item) {
                updatePrice(item)
            }
        }
    }
}

@Composable
fun TextButtons(price: Int, updatePrice: (Int) -> Unit) {
    Button(
        onClick = {
            updatePrice(price)
        }, modifier = Modifier,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(Color.Black)

    ) {
        Text(text = "$price", style = MaterialTheme.typography.bodyMedium, color = Color.White)
    }
}

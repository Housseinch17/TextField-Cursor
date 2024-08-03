package com.example.trainingcompose

import androidx.compose.ui.text.input.TextFieldValue

data class AppUiState(
    val price: TextFieldValue = TextFieldValue("0"),
)
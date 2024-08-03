package com.example.textfieldvalue

import androidx.compose.ui.text.input.TextFieldValue

data class AppUiState(
    val price: TextFieldValue = TextFieldValue("0"),
)
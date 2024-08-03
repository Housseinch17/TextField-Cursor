package com.example.trainingcompose

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class AppViewModel: ViewModel() {
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState = _appUiState.asStateFlow()

    fun updatePrice(textFieldValue: TextFieldValue){
        _appUiState.update {
            it.copy(price = textFieldValue)
        }
    }
}
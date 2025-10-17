package com.example.ailanguagetranslate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ailanguagetranslate.domain.model.Language
import com.example.ailanguagetranslate.domain.usecase.GetListLanguageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LanguageUiState(
    val languages: List<Language> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedLanguage: Language? = null,
)
class LanguageViewModel(
    private val getListLanguageUseCase: GetListLanguageUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LanguageUiState())
    val uiState: StateFlow<LanguageUiState> = _uiState

    init {
        getLanguageList()
    }

    private fun getLanguageList() {
        _uiState.value = LanguageUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val languages = getListLanguageUseCase()
                _uiState.value = LanguageUiState(languages = languages)
            } catch (e: Exception) {
                _uiState.value = LanguageUiState(error = e.message)
            }
        }
    }

    fun selectLanguage(language: Language) {
        _uiState.value = _uiState.value.copy(selectedLanguage = language)
    }
}

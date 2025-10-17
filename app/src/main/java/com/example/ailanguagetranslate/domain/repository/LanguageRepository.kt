package com.example.ailanguagetranslate.domain.repository

import com.example.ailanguagetranslate.domain.model.Language

interface LanguageRepository {
    suspend fun getLanguageList(): List<Language>
}

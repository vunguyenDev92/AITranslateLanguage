package com.example.ailanguagetranslate.domain.usecase

import com.example.ailanguagetranslate.domain.model.Language
import com.example.ailanguagetranslate.domain.repository.LanguageRepository

class GetListLanguageUseCase(
    private val repository: LanguageRepository,
) {
    suspend operator fun invoke(): List<Language> {
        return repository.getLanguageList()
    }
}

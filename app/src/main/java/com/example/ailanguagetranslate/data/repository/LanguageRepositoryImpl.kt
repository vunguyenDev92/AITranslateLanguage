package com.example.ailanguagetranslate.data.repository

import com.example.ailanguagetranslate.data.remote.LanguageRemoteData
import com.example.ailanguagetranslate.domain.model.Language
import com.example.ailanguagetranslate.domain.repository.LanguageRepository

class LanguageRepositoryImpl(
    private val api: LanguageRemoteData,
) : LanguageRepository {

    override suspend fun getLanguageList(): List<Language> {
        val countries = api.getLanguages()
        val languages = mutableListOf<Language>()

        for (country in countries) {
            country.languages?.forEach { (code, langName) ->
                languages.add(
                    Language(
                        name = langName,
                        code = code,
                        flagUrl = country.flags?.png ?: "",
                    ),
                )
            }
        }

        return languages.distinctBy { it.code }
    }
}

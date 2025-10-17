package com.example.ailanguagetranslate

import com.example.ailanguagetranslate.data.remote.LanguageRemoteData
import com.example.ailanguagetranslate.data.repository.LanguageRepositoryImpl
import com.example.ailanguagetranslate.domain.usecase.GetListLanguageUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {
    private val baseUrl = "https://restcountries.com/v3.1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: LanguageRemoteData =
        retrofit.create(LanguageRemoteData::class.java)

    private val languageRepository = LanguageRepositoryImpl(apiService)

    val getListLanguageUseCase = GetListLanguageUseCase(languageRepository)
}

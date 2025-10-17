package com.example.ailanguagetranslate.data.remote

import com.example.ailanguagetranslate.data.model.LanguageDto
import retrofit2.http.GET
interface LanguageRemoteData {
    @GET("all?fields=name,flags,languages,cca2")
    suspend fun getLanguages(): List<LanguageDto>
}

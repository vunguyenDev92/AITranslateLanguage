package com.example.ailanguagetranslate.data.model

import com.google.gson.annotations.SerializedName

data class LanguageDto(
    @SerializedName("name") val name: NameResponse?,
    @SerializedName("flags") val flags: FlagResponse?,
    @SerializedName("languages") val languages: Map<String, String>?,
    @SerializedName("cca2") val code: String?,
)

data class NameResponse(
    @SerializedName("common") val common: String?,
)

data class FlagResponse(
    @SerializedName("png") val png: String?,
)


package com.susanna.weatherapp.model

import com.google.gson.annotations.SerializedName

data class APIError(
    @field:SerializedName("error")
    val error: Boolean = false,

    @field:SerializedName("reason")
    val reason: String?,
)

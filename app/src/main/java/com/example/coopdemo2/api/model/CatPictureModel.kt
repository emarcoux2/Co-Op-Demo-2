package com.example.coopdemo2.api.model

import kotlinx.serialization.Serializable

// this annotation tells Kotlin that this class can be converted to and from JSON
@Serializable
data class CatPicture(
    val id: String,
    val url: String
)
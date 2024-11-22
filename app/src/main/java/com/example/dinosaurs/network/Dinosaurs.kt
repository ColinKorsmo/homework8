package com.example.dinosaurs.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Dinosaurs(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String,
    val description: String,
    val name: String,
    val length: String
)

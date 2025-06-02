package com.example.katalogfakultas2.model

import androidx.annotation.DrawableRes

data class Fakultas(
    val id: Int,
    val nama: String,
    val deskripsi: String,
    val jumlahProdi: Int,
    @DrawableRes val gambarResId: Int
)
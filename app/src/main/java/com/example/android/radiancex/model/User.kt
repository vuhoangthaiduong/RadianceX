package com.example.android.radiancex.model

import android.graphics.Bitmap
import java.net.URL

data class User(val username: String,
                val firstName: String,
                val lastName: String,
                val email: String) {

    private val learnedCards: List<Sentence>? = null
    private val timeStudied: Int? = null
    private val achievement: String? = null
    private val avatar: Bitmap? = null
    private val avatarURL: URL? = null

    constructor() : this("", "", "", "") {}
}
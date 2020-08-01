package com.example.android.radiancex.model

import java.util.*

data class Note(var title: String) {
    private var content: String? = null
    private var dateCreated: Date? = null
    private var creator: User? = null
    private var isEdited = false

    constructor(_title: String, _content: String, _creator: User) : this(_title) {
        content = _content
        creator = _creator
        dateCreated = Calendar.getInstance().time
        isEdited = false
    }
}
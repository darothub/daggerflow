package com.example.daggertwo.model

import com.google.gson.annotations.SerializedName

data class Todo(
    val completed: Boolean? = null,
    @SerializedName("id")
    var id: Int?= null,
    val title: String?= null,
    val userId: Int?= null
)
package com.demo.code.models


import com.google.gson.annotations.SerializedName

data class PlaylistDataItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
package com.example.anchorbookskvh.model.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    val author: String?,
    val country: String?,
    @PrimaryKey val id: Int,
    val imageLink: String?,
    val language: String?,
    val title: String?
)
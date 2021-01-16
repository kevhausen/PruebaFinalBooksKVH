package com.example.anchorbookskvh.model.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_detail")
data class BookDetail(
    val author: String?,
    val country: String?,
    val delivery: Boolean?,
    @PrimaryKey val id: Int,
    val imageLink: String?,
    val language: String?,
    val lastPrice: Int?,
    val link: String?,
    val pages: Int?,
    val price: Int?,
    val title: String?,
    val year: Int?
)
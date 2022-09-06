package com.example.myapplication.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "events")
@Parcelize
data class Event(
    @PrimaryKey
    val date: String = "",
    @ColumnInfo(name = "title_text")
    val title_text: String = "",
    @ColumnInfo(name = "header_text")
    val header_text: String = "",
    @ColumnInfo(name = "body_text")
    val body_text: String = "",
    @ColumnInfo(name = "image")
    val image: String = ""
) : Parcelable

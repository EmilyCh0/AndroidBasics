package com.example.roomword

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "word") val word: String
)

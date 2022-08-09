package com.example.topic

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val nameResourceId: Int,
    val numberOfLectures: Int,
    @DrawableRes val imageResourceId: Int
)

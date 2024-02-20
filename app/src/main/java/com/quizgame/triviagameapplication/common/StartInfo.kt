package com.quizgame.triviagameapplication.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StartInfo(
    val numberOfQuestion: Int,
    val category: String,
    val difficulty: String
): Parcelable

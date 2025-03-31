package com.annalech.gamecompositionofthenumber.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Level:Parcelable {
    TESTY,
    EASY,
    NORMAL,
    HARD
}
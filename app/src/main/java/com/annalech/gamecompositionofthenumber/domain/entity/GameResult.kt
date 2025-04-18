package com.annalech.gamecompositionofthenumber.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val winner: Boolean,
    val countOfRightAnswer: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSetting
) : Parcelable{



}


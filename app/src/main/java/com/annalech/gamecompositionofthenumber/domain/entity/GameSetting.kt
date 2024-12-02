package com.annalech.gamecompositionofthenumber.domain.entity

data class GameSetting (
    val maxSumValue: Int,
    val minCountOfRightAnswer: Int,
    val minPercentOfRightAnswer: Int,
    val gameTimeInSeconds: Int
)
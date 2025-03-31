package com.annalech.gamecompositionofthenumber.domain.repository

import com.annalech.gamecompositionofthenumber.domain.entity.GameSetting
import com.annalech.gamecompositionofthenumber.domain.entity.Level
import com.annalech.gamecompositionofthenumber.domain.entity.Qestion

interface GameRepository {

    fun generatedQustions(
        maxSumValue: Int,
        countOfOptuions: Int
    ): Qestion

    fun getGameSettings(level:Level): GameSetting


}
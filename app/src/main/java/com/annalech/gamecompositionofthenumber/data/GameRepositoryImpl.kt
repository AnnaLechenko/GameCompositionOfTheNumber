package com.annalech.gamecompositionofthenumber.data

import com.annalech.gamecompositionofthenumber.domain.entity.GameSetting
import com.annalech.gamecompositionofthenumber.domain.entity.Level
import com.annalech.gamecompositionofthenumber.domain.entity.Qestion
import com.annalech.gamecompositionofthenumber.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val  MIN_SUM_VALUE = 2
    private const val  MIN_ANSWER = 1

    override fun generatedQustions(maxSumValue: Int, countOfOptuions: Int): Qestion {
        val sumNumber = Random.nextInt(MIN_SUM_VALUE,maxSumValue+1)
        val visibleNumber = if (sumNumber > MIN_SUM_VALUE) {
            Random.nextInt(MIN_SUM_VALUE, sumNumber)
        } else {
            MIN_SUM_VALUE
        }

        val optionsNumbers = HashSet<Int>()
        val rightAnswer = sumNumber - visibleNumber
        optionsNumbers.add(rightAnswer)
        val fromAnswer = max(rightAnswer - countOfOptuions, MIN_ANSWER)
        val toAnswer = min(maxSumValue, rightAnswer+ countOfOptuions)
        while (   countOfOptuions > optionsNumbers.size){
            optionsNumbers.add(Random.nextInt(fromAnswer,toAnswer))
        }
        return Qestion(sumNumber,visibleNumber,optionsNumbers.toList())
    }

    override fun getGameSettings(level: Level): GameSetting {
       return when(level){
            Level.TESTY -> GameSetting(
                maxSumValue = 10,
                minCountOfRightAnswer = 3,
                minPercentOfRightAnswer = 50,
                gameTimeInSeconds = 10
            )
           Level.EASY -> GameSetting(
               10,
               10,
               70,
               60
           )
           Level.NORMAL -> GameSetting(
               25,
               15,
               80,
               50
           )
           Level.HARD -> GameSetting(
               50,
               20,
               80,
               30
           )
        }
    }
}
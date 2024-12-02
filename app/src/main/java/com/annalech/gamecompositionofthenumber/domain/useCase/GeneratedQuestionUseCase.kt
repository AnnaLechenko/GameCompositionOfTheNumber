package com.annalech.gamecompositionofthenumber.domain.useCase

import com.annalech.gamecompositionofthenumber.domain.entity.Qestion
import com.annalech.gamecompositionofthenumber.domain.repository.GameRepository

class GeneratedQuestionUseCase(
   private val repository: GameRepository
) {

    operator fun invoke(maxSumValue:Int): Qestion{
       return repository.generatedQustions(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object{
        private const val COUNT_OF_OPTIONS = 6
    }


}
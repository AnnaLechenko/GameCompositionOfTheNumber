package com.annalech.gamecompositionofthenumber.domain.useCase

import com.annalech.gamecompositionofthenumber.domain.entity.GameSetting
import com.annalech.gamecompositionofthenumber.domain.entity.Level
import com.annalech.gamecompositionofthenumber.domain.repository.GameRepository

class GetGameSettingsUseCase (val repository: GameRepository){
    operator fun invoke(level:Level) : GameSetting{
      return repository.getGameSettings(level)
    }
}
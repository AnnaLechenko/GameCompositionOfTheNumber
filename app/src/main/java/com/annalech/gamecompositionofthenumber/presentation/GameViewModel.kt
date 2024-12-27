package com.annalech.gamecompositionofthenumber.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annalech.gamecompositionofthenumber.R
import com.annalech.gamecompositionofthenumber.data.GameRepositoryImpl
import com.annalech.gamecompositionofthenumber.domain.entity.GameResult
import com.annalech.gamecompositionofthenumber.domain.entity.GameSetting
import com.annalech.gamecompositionofthenumber.domain.entity.Level
import com.annalech.gamecompositionofthenumber.domain.entity.Qestion
import com.annalech.gamecompositionofthenumber.domain.useCase.GeneratedQuestionUseCase
import com.annalech.gamecompositionofthenumber.domain.useCase.GetGameSettingsUseCase

class GameViewModel(
    private val  application: Application,
    private val level: Level,
) : ViewModel() {

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime:LiveData<String>
        get() = _formattedTime

    private val _question = MutableLiveData<Qestion>()
    val question: LiveData<Qestion>
        get() = _question

    private val _percentRightAnswer = MutableLiveData<Int>()
    val percentRightAnswer: LiveData<Int>
        get() = _percentRightAnswer

    private val _progressAnswer = MutableLiveData<String>()
    val progressAnswer: LiveData<String>
        get() = _progressAnswer

    private val _enoughPerrcentOfRightAnswer = MutableLiveData<Boolean>()
    val enoughPerrcentOfRightAnswer: LiveData<Boolean>
        get() = _enoughPerrcentOfRightAnswer

    private val _enoughContOfRightAnswer = MutableLiveData<Boolean>()
    val enoughContOfRightAnswer: LiveData<Boolean>
        get() = _enoughContOfRightAnswer

    private val _gameResult= MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent


    private var timer: CountDownTimer? = null
    private var countOfRightAnswer = 0
    private var countOfQuestiions = 0
    private val context = application

    private lateinit var gameSetting: GameSetting


    val repository = GameRepositoryImpl

    val generatedQuestionUseCase = GeneratedQuestionUseCase(repository)
    val getGameSettingUseCase = GetGameSettingsUseCase(repository)

    init {
        startGame()
    }
    //сохранение настроек в переменные полученные из выбранного уровня
   private fun startGame( ){
       getGameSettings( )
        startTimer()
        generatedQuestion()
        updateProgress()
   }

    private fun getGameSettings( ){

        this.gameSetting = getGameSettingUseCase(level)
        _minPercent.value = gameSetting.minPercentOfRightAnswer
    }

    //установка таймера
    private fun startTimer(){
             timer= object : CountDownTimer(
                gameSetting.gameTimeInSeconds* MILLIS_IN_SECONDS,
                MILLIS_IN_SECONDS
            ) {
                override fun onTick(millisUntilFinished: Long) {
                   _formattedTime.value =formatTime(millisUntilFinished)
                }

                override fun onFinish() {
                    finishGame()
                }

            }
        timer?.start()
    }

    private fun formatTime(millisUntilFinished: Long): String{
            val seconds = millisUntilFinished/ MILLIS_IN_SECONDS
        val minutes = seconds/ SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes* SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes,leftSeconds)
    }

    fun chooseAnswer(number: Int){
        checkAnswer(number)
        updateProgress()
        generatedQuestion()
    }

    private fun checkAnswer(number: Int){
        val rightAnswer = question.value?.rightAnswer
        if (number == rightAnswer){
            countOfRightAnswer++
        }
        countOfQuestiions++
    }


    private fun finishGame(){
        _gameResult.value = GameResult(
            winner = enoughContOfRightAnswer.value == true && enoughPerrcentOfRightAnswer.value == true,
            countOfRightAnswer = countOfRightAnswer,
            countOfQuestions = countOfQuestiions,
            gameSettings = gameSetting
        )
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    private fun generatedQuestion(){
            _question.value = generatedQuestionUseCase(gameSetting.maxSumValue)
    }

    private fun updateProgress(){
        val percent = calculatePercentOfRightAnswer()
        _percentRightAnswer.value = percent

        //отображение данных прогресса
        _progressAnswer.value = String.format(
            application.resources.getString(R.string.progress_answer),
            countOfRightAnswer ,
            gameSetting.minCountOfRightAnswer
        )

        _enoughContOfRightAnswer.value = countOfRightAnswer>=gameSetting.minCountOfRightAnswer
        _enoughPerrcentOfRightAnswer.value = percent >= gameSetting.minPercentOfRightAnswer

    }

    private fun calculatePercentOfRightAnswer(): Int{
        if (countOfRightAnswer==0){
            return 0
        }
return ((countOfRightAnswer/countOfQuestiions.toDouble())*100).toInt()
    }












    companion object{
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60
    }

}
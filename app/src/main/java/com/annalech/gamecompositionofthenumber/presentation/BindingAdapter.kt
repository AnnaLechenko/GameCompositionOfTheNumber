package com.annalech.gamecompositionofthenumber.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.annalech.gamecompositionofthenumber.R
import com.annalech.gamecompositionofthenumber.domain.entity.GameResult


interface OnOptionClickListner {
    fun onOptionClick(option: Int)
}


@BindingAdapter( "requieredAnswer")
fun bindRequiredAnswer(textView: TextView, count:Int){
    textView.text = String.format(
        textView.context.getString(R.string.requiredAnswer),
        count
    )
}

@BindingAdapter("scoreAnswer")
fun bindScoreAnswer(textView: TextView,score: Int){
    textView.text = String.format(
        textView.context.getString(R.string.scoreAnswer),
       score
    )
}

@BindingAdapter("requieredPercentage")
fun requieredPercentage(textView: TextView,percent: Int){
    textView.text = String.format(
        textView.context.getString(R.string.requiered_percent),
        percent
    )

}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView,gameResult: GameResult){
    textView.text = String.format(
        textView.context.getString(R.string.score_perce),
       getPercentRightAnswers(gameResult)
    )
}

private fun getPercentRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfRightAnswer == 0) {
        0
    } else {
        ((countOfRightAnswer / countOfQuestions.toDouble()) * 100).toInt()
    }
}


@BindingAdapter("imageResult")
fun bindImageResult(view: ImageView,winner:Boolean){
    view.setImageResource(getSmileResId(winner))
}


private fun getSmileResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.suscess_final_ic
    } else {
        R.drawable.no_sucsess_final
    }
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView,enough:Boolean){
    textView.setTextColor(getColorByState(textView.context,enough))
}

private fun getColorByState(context: Context, goodState:Boolean):Int{
    val colorResId = if (goodState){
        android.R.color.holo_green_light
    } else{
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar,enough:Boolean){
    val color = getColorByState(progressBar.context, enough)
   progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number:Int){
    textView.text  = number.toString()
}

@BindingAdapter("onOptionCliickListner")
fun bindOnOptionClickListner(textView: TextView, clickListner: OnOptionClickListner){
    //вместо кликлистнер прилетает метод chooseAnswer и проверка числа на правильность ответа
    textView.setOnClickListener{
        clickListner.onOptionClick(textView.text.toString().toInt())
    }
}
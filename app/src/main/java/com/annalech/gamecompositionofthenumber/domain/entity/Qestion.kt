package com.annalech.gamecompositionofthenumber.domain.entity

data class Qestion (
    val sum : Int,
    val visibibleNumber: Int,
    val options: List<Int>
){
    val rightAnswer : Int
        get() = sum - visibibleNumber
}
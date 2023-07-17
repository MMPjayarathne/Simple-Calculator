package com.seng31323.assignment_01.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var result = MutableLiveData<Double>(0.0)

    fun add(number1: Double, number2: Double){
        result.value = number1.plus(number2)
        Log.d("Add Result",result.value.toString())
    }

    fun subtraction(number1:Double,number2:Double){
        result.value = number1.minus(number2)
        Log.d("Minus Result",result.value.toString())
    }

    fun multiplication(number1:Double,number2:Double){
        result.value = number1.times(number2)
        Log.d("Multiplication Result",result.value.toString())
    }

    fun divide(number1:Double,number2:Double){
        result.value = number1.div(number2)
        Log.d("Divide Result",result.value.toString())
    }
}
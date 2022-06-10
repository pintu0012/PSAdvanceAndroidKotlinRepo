package com.publicissapient.PSAndroidKotlinApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val fruits = listOf<String>("mango", "banana", "pineapple", "orange","watermelon")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(callAFunction(5,5).toString())
        printFruitsArray()
    }

    private fun printFruitsArray() {
        for (item in fruits){
            println("Current Fruit is: $item")
        }
        //To get index
        for(index in fruits.indices){
            println("Current Fruit is : ${fruits[index]}")
        }
    }

    private fun callAFunction(a: Int ,b: Int ):Int {
        return  a+b
    }
}
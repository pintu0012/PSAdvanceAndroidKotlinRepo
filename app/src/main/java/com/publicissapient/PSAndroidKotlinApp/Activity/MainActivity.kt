package com.publicissapient.PSAndroidKotlinApp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.publicissapient.PSAndroidKotlinApp.R
import com.publicissapient.PSAndroidKotlinApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val fruits = listOf<String>("mango", "banana", "pineapple", "orange","watermelon")
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

}
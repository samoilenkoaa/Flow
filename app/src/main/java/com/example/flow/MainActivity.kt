package com.example.flow

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var inputEditText: EditText
    private lateinit var startButton: Button
    private lateinit var resultTextView: TextView

    private var sumCoroutine: Job? = null
    private val resultsList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputEditText = findViewById(R.id.inputEditText)
        startButton = findViewById(R.id.startButton)
        resultTextView = findViewById(R.id.resultTextView)

        startButton.setOnClickListener {
            val n = inputEditText.text.toString().toInt()
            startSum(n)
        }
    }

    private fun startSum(n: Int) {
        sumCoroutine?.cancel()
        resultsList.clear()
        resultTextView.text = ""

        sumCoroutine = lifecycleScope.launch {
            for (index in 0 until  n) {
                delay((index + 1) * 100L)
                val value = index + 1
                resultsList.add(value)

                val sum = resultsList.sum()
                resultTextView.append("$sum ")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sumCoroutine?.cancel()
    }
}
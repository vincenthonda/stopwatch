package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    lateinit var clock : Chronometer
    lateinit var startstop : Button
    lateinit var reset : Button
    var isTimerRunning: Boolean = false

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        startstop.setOnClickListener() {
            if(!isTimerRunning) {
                clock.start()
                isTimerRunning = true
            }
            else {
                clock.stop()
                isTimerRunning = false
            }
        }

        reset.setOnClickListener() {
            clock.stop()
        }
    }

    fun wireWidgets() {
        clock = findViewById(R.id.chronometer_main_stopwatch)
        startstop = findViewById(R.id.button_start)
        reset = findViewById(R.id.button_main_reset)
    }



    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }


}
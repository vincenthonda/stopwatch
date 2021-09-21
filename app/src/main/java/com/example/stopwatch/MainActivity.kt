package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.*
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    lateinit var clock: Chronometer
    lateinit var startstop: Button
    lateinit var reset: Button
    var isTimerRunning: Boolean = false
    var displayedTime = 0L


    companion object {
        val TAG = "MainActivity"
        val BUNDLE_DISPLAYED_TIME = "displayed time"
        val BUNDLE_IS_RUNNING = "isRunning"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        wireWidgets()
        displayedTime = savedInstanceState?.getLong(BUNDLE_DISPLAYED_TIME) ?: 0L
        isTimerRunning = savedInstanceState?.getBoolean(BUNDLE_IS_RUNNING) ?: false
        clock.base = elapsedRealtime() - displayedTime
        if(isTimerRunning) {
           // clock.base = displayedTime   // i don't think this line is correct. is the base the display?
            clock.start()
            startstop.text = "STOP"
            // update the button to say stop because that doesn't happen here on rotate
        }

        startstop.setOnClickListener() {
            if (isTimerRunning) {
                updateLastPause()
                clock.stop()
                startstop.text = "START"
            } else {
                clock.base = elapsedRealtime() - displayedTime
                clock.start()
                startstop.text = "STOP"
            }
            isTimerRunning = !isTimerRunning
        }

        reset.setOnClickListener() {
            if(isTimerRunning) {
                clock.stop()
            }
            displayedTime = 0L
            clock.base = displayedTime
        }
    }

    fun updateLastPause() {
        if(isTimerRunning) {
            displayedTime = elapsedRealtime() - clock.base
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        updateLastPause()
        outState.putLong(BUNDLE_DISPLAYED_TIME, displayedTime)
        outState.putBoolean(BUNDLE_IS_RUNNING, isTimerRunning)
    }


    fun wireWidgets() {
        clock = findViewById(R.id.chronometer_main_stopwatch)
        startstop = findViewById(R.id.button_start)
        startstop.text = "START"
        reset = findViewById(R.id.button_main_reset)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }



}
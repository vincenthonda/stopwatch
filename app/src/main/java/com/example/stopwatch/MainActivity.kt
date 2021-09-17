package com.example.stopwatch

import android.net.wifi.rtt.CivicLocationKeys
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.os.SystemClock.*
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    lateinit var clock: Chronometer
    lateinit var startstop: Button
    lateinit var reset: Button
    var isTimerRunning: Boolean = false
    var lastPause = 0L
    val TAG = "MainActivity"
    val BUNDLE_DISPLAYED_TIME = "displayed time"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()
        lastPause = savedInstanceState?.getLong(BUNDLE_DISPLAYED_TIME) ?: 0L
        isTimerRunning = savedInstanceState?.getBoolean(BUNDLE_DISPLAYED_TIME) ?: false
        if(isTimerRunning) {
            clock.start()
        }

        startstop.setOnClickListener() {
            if (isTimerRunning) {
                lastPause = elapsedRealtime() - clock.base
                clock.stop()
                startstop.text = "START"
            } else {
                clock.base = elapsedRealtime() - lastPause
                clock.start()
                startstop.text = "STOP"
            }
            isTimerRunning = !isTimerRunning
        }

        reset.setOnClickListener() {
            if(isTimerRunning) {
                clock.stop()
            }
            lastPause = 0L
            clock.base = elapsedRealtime()
        }
    }

    fun updateLastPause() {
        if(isTimerRunning) {
            lastPause = SystemClock.elapsedRealtime() - clock.base
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        updateLastPause()
        outState.putLong(BUNDLE_DISPLAYED_TIME, lastPause)
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
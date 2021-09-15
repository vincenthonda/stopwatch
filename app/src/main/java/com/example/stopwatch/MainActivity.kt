package com.example.stopwatch

import android.net.wifi.rtt.CivicLocationKeys
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

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

    fun wireWidgets() {
        clock = findViewById(R.id.chronometer_main_stopwatch)
        startstop = findViewById(R.id.button_start)
        startstop.text = "START"
        reset = findViewById(R.id.button_main_reset)
    }
}
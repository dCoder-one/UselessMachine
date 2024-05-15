package com.example.uselessmachine

import android.annotation.SuppressLint
import android.graphics.Color.rgb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import com.google.android.material.search.SearchBar

@SuppressLint("UseSwitchCompatOrMaterialCode")
class MainActivity : AppCompatActivity() {

    lateinit var switch : Switch
    lateinit var selfDestruct : Button
    lateinit var selfDestructTimer : TextView
    lateinit var lookBusy : Button
    lateinit var layout : ConstraintLayout
    lateinit var groupBaseUI : Group
    //
    lateinit var busyTimer : TextView
    lateinit var lineProgressBar : ProgressBar
    lateinit var circleProgressBar : ProgressBar
//    lateinit var searchBar : SearchBar
//    lateinit var seekBar : SeekBar
    lateinit var groupSelfDestruct : Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()
        switch.text = "Slack"
        selfDestruct.text = "Honorable Suicide"
        lookBusy.text = ";>"

        groupSelfDestruct.visibility = View.GONE
        selfDestructTimer.visibility = View.GONE

        // using an anonymous inner class
        // anonymous because we aren't formally declaring a class but
        // we are implementing all of its member functions

        // lambda is an anonymous function. any time you have an interface
        // with a single function to implement, you can just use a lambda
        // and skip having to make an anonymous inner class
        // it automatically overrides the one function
        // with 1 parameter, you don't have to name it. it gets the default
        // name "it"
        selfDestruct.setOnClickListener { // todo add a countdown timer before closing the app
            val timer = object: CountDownTimer(10000, 1000) {
                var isRed = false
                var color = rgb(255,2,66)
                var white = rgb(255,255,255)

                override fun onTick(millisUntilFinished: Long) {
//                    switch.setOnCheckedChangeListener { view, isChecked ->
//                        if ((millisUntilFinished/1000 % 2).toInt() == 0) {
//                            switch.isChecked = true
//                        }
//                    }
                    selfDestruct.text = "Time Remaining: ${millisUntilFinished/1000}"
                    selfDestructTimer.visibility = View.VISIBLE
                    selfDestructTimer.text = "${millisUntilFinished/1000}"
                    selfDestruct.isEnabled = false
                    if ((millisUntilFinished/1000 % 2).toInt() == 0) {
                        layout.setBackgroundColor(color)
                        selfDestructTimer.setTextColor(white)
                        switch.setOnCheckedChangeListener { compoundButton, isChecked ->
                            switch.isChecked = true
                        }
                    } else {
                        layout.setBackgroundColor(white)
                        selfDestructTimer.setTextColor(color)
                    }
                }

                override fun onFinish() {
                    finish()
                }
            }
            timer.start()
        }

        lookBusy.setOnClickListener {
            groupBaseUI.visibility = View.GONE
            groupSelfDestruct.visibility = View.VISIBLE
            var n = 14
            val timer = object: CountDownTimer((1000*n).toLong(), 1000) {

                override fun onTick(millisUntilFinished2: Long) {
                    //busyTimer.text = "Percentage Until Complete: ${millisUntilFinished2/1000}"
//                    if (millisUntilFinished2/1000 < 0) {
//
//                    }
                    busyTimer.text = "Files Done: ${n - millisUntilFinished2/1000}/${n}"
                    lineProgressBar.incrementProgressBy((n-millisUntilFinished2/1000).toInt())
                    // n/n+3
                }

                override fun onFinish() {
                    //n = (n + 10)
                    groupSelfDestruct.visibility = View.GONE
                    groupBaseUI.visibility = View.VISIBLE
                    lineProgressBar.setProgress(0)
                }
            }
            timer.start()
        }


        //switch.isChecked
        // lambda for the OnCheckedChangedListener. Has 2 parameters,
        // so we have to name them
        switch.setOnCheckedChangeListener { view, isChecked ->
            if(isChecked) {
                // let them know it's on
                var random = (1..4).random()
                if (random == 1) {
                    switch.text = "Activated"
                    //random = (2..4).random()
                } else if (random == 2) {
                    switch.text = "Initiated"
                    //random = (3..4).random()
                } else if (random == 3) {
                    switch.text = "Created"
                    //random = (1..2).random()
                } else {
                    switch.text = "Instigated"
                    //random = (1..3).random()
                }
                //switch.text = "Activated"
                val timer = object: CountDownTimer(1500, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        switch.isChecked = false
                    } //timer.start()
//                    timer.start()
                }
                timer.start()
                //layout.setBackgroundColor()
            } else {
                // let them know it's off
                switch.text = "Deactivated"
            }
        }



        //if (switch.isChecked) {
        //    switch.text = "yes"
        //}

    }
    private fun wireWidgets() {
        switch = findViewById(R.id.switch_main_useless)
        selfDestruct = findViewById(R.id.button_main_selfDestruct)
        selfDestructTimer = findViewById(R.id.textView_selfDestruct_timer)
        lookBusy = findViewById(R.id.button_main_lookBusy)
        layout = findViewById(R.id.layout_main)
        groupBaseUI = findViewById(R.id.group_main_baseUI)
        // look Busy Group
        busyTimer = findViewById(R.id.textView_lookBusy_timer)
        lineProgressBar = findViewById(R.id.progressBar_lookBusy_line)
        circleProgressBar = findViewById(R.id.progressBar_lookBusy_circle)
//        searchBar = findViewById(R.id.searchView_lookBusy_search)
//        seekBar = findViewById(R.id.seekBar_lookBusy_seeks)
        groupSelfDestruct = findViewById(R.id.group_selfDestruct_selfDestruct)
    }
}
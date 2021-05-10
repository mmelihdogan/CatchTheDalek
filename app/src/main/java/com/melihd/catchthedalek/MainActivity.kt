package com.melihd.catchthedalek

import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var score = 0
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        hideImages()

        // Count Down Timer
        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                timeText.text = "Time: 0"

                handler.removeCallbacks(runnable)

                imageView1.visibility = View.INVISIBLE


                // Alert Dialog
                val alert = AlertDialog.Builder(this@MainActivity)

                alert.setTitle("Time is Up!")
                alert.setPositiveButton("Play Again", DialogInterface.OnClickListener { dialog, which ->
                    val intent= intent
                    finish()
                    startActivity(intent)
                })
                alert.setNegativeButton("Quit", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                })
                alert.show()
            }
        }.start()
    }

    fun increaseScore(view : View) {
        score++
        scoreText.text = "Score: ${score}"
    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {

                imageView1.visibility = View.INVISIBLE

                val random = Random()
                val column = random.nextInt(750).toFloat()
                val row = random.nextInt(750).toFloat()
                imageView1.x = column
                imageView1.y = row
                imageView1.visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }

        handler.post(runnable)


    }
}
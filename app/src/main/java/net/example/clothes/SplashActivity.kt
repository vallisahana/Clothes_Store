package net.example.clothes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // create function for timer to change activity
        val timer = Timer()
        timer.schedule(object : TimerTask() {

            override fun run() {
                startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
            }

        }, 2000)
    }
}
package com.dicoding.lifeu.view.sleepquality

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.lifeu.databinding.ActivitySleepBinding
import com.dicoding.lifeu.main.MainActivity
import com.dicoding.lifeu.view.foodrecomendation.FoodActivity
import com.dicoding.lifeu.view.mentalconsultation.MentalActivity
import com.dicoding.lifeu.view.profile.ProfileActivity

class SleepActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySleepBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            val intent = Intent(this@SleepActivity, SleepActivity::class.java)
            startActivity(intent)
        }
        binding.btn2.setOnClickListener {
            val intent = Intent(this@SleepActivity, FoodActivity::class.java)
            startActivity(intent)
        }
        binding.btn3.setOnClickListener {
            val intent = Intent(this@SleepActivity, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btn4.setOnClickListener {
            val intent = Intent(this@SleepActivity, MentalActivity::class.java)
            startActivity(intent)
        }
        binding.btn5.setOnClickListener {
            val intent = Intent(this@SleepActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

        setupView()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}
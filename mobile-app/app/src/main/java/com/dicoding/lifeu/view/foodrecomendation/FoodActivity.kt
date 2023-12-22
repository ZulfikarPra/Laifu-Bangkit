package com.dicoding.lifeu.view.foodrecomendation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.dicoding.lifeu.databinding.ActivityFoodBinding
import com.dicoding.lifeu.main.MainActivity
import com.dicoding.lifeu.view.mentalconsultation.MentalActivity
import com.dicoding.lifeu.view.profile.ProfileActivity
import com.dicoding.lifeu.view.sleepquality.SleepActivity

class FoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            val intent = Intent(this@FoodActivity, SleepActivity::class.java)
            startActivity(intent)
        }
        binding.btn2.setOnClickListener {
            val intent = Intent(this@FoodActivity, FoodActivity::class.java)
            startActivity(intent)
        }
        binding.btn3.setOnClickListener {
            val intent = Intent(this@FoodActivity, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btn4.setOnClickListener {
            val intent = Intent(this@FoodActivity, MentalActivity::class.java)
            startActivity(intent)
        }
        binding.btn5.setOnClickListener {
            val intent = Intent(this@FoodActivity, ProfileActivity::class.java)
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
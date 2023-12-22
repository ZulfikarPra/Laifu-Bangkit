package com.dicoding.lifeu.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.dicoding.lifeu.R
import com.dicoding.lifeu.databinding.ActivityMainBinding
import com.dicoding.lifeu.databinding.ActivityProfileBinding
import com.dicoding.lifeu.main.MainViewModel
import com.dicoding.lifeu.main.MainViewModelFactory
import com.dicoding.lifeu.view.signup.SignupActivity
import com.dicoding.lifeu.view.welcome.WelcomeActivity

class ProfileActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel> {
        MainViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.app_name)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.logout -> {
                val intent = Intent (this@ProfileActivity, WelcomeActivity::class.java)
                startActivity(intent)
            }
            R.id.acc -> {
                val intent = Intent (this@ProfileActivity, SignupActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
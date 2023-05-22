package com.solosol.solsolandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.solosol.solsolandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(binding.container.id, HomeFragment()).commit()
        initBottomNav()
    }

    private fun initBottomNav() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigationHome -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.container.id, HomeFragment()).commit()
                    true
                }


                else -> false
            }
        }
    }
}
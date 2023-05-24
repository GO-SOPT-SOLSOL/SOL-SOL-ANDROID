package com.solosol.solsolandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.solosol.solsolandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            replace(binding.container.id, HomeFragment())
        }
        initBottomNav()
    }

    private fun initBottomNav() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigationHome -> {
                    supportFragmentManager.commit {
                        replace(binding.container.id, HomeFragment())
                    }
                    true
                }


                else -> false
            }
        }
    }
}
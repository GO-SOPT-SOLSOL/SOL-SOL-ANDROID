package com.solosol.solsolandroid.transfer3

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.solosol.solsolandroid.databinding.ActivityTransfer3Binding

class Transfer3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTransfer3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransfer3Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
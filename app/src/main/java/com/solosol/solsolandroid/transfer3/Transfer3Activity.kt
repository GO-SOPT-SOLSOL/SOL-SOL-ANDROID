package com.solosol.solsolandroid.transfer3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.solosol.solsolandroid.databinding.ActivityTransfer3Binding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Transfer3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTransfer3Binding
    private val transferService = ServicePool.transferService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransfer3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        clickTransfer()
    }

    private fun clickTransfer() {
        binding.btnSendTransfer3.setOnClickListener {
            completeTransfer()
        }
    }

    private fun completeTransfer() {
        val requestTransferDto: RequestTransferDto = requestTransferDto()

        lifecycleScope.launch {
            val response = transferService.postTransfer(requestTransferDto)
            if (response.isSuccessful) {
                Log.d("Transfer 서버연결", "onResponse: ${response.message()}")
                Toast.makeText(this@Transfer3Activity, "성공", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("Transfer 서버연결실패", "onResponse: ${response.message()}")
                Snackbar.make(binding.root, "실패", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


    private fun requestTransferDto(): RequestTransferDto {
        return with(binding) {
            RequestTransferDto(
                1,
                tvPriceTransfer3.text.toString().replace(",", "").toInt(),
                tvBankTransfer3.text.toString(),
                tvNumberTransfer3.text.toString(),
                edtTransferMemoTransfer3.text.toString(),
                edtReceiverMemoTransfer3.text.toString(),
                0
            )
        }
    }
}
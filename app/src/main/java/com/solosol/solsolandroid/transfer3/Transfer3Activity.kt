package com.solosol.solsolandroid.transfer3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.solosol.solsolandroid.databinding.ActivityTransfer3Binding
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
        transferService.postTransfer(// 서버로 보낼 데이터 생성
            with(binding) {
                RequestTransferDto(
                    1,
                    tvPriceTransfer3.text.toString().replace(",","").toInt(),
                    tvBankTransfer3.text.toString(),
                    tvNumberTransfer3.text.toString(),
                    edtTransferMemoTransfer3.text.toString(),
                    edtReceiverMemoTransfer3.text.toString(),
                    0
                )
            }).enqueue(object :
            Callback<ResponseTransferDto> {
            override fun onResponse(
                call: Call<ResponseTransferDto>,
                response: Response<ResponseTransferDto>
            ) {
                if (response.isSuccessful) {
                    val responseTransferDto = response.body()
                    if (responseTransferDto != null) {
                        if (responseTransferDto.success) {
                            Log.d("Transfer 서버연결", "onResponse: ${responseTransferDto.message}")
                            Toast.makeText(this@Transfer3Activity, "성공", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d("Transfer 서버연결실패", "onResponse: ${responseTransferDto.message}")
                            Snackbar.make(binding.root, "실패", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTransferDto>, t: Throwable) {
                Log.e("Transfer 서버연결", "onFailure: $t")
            }
        })


    }
}
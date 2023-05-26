package com.solosol.solsolandroid.transfer3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.solosol.solsolandroid.ServicePool
import com.solosol.solsolandroid.databinding.ActivityTransfer3Binding
import com.solosol.solsolandroid.response.RequestTransferDto
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class Transfer3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTransfer3Binding
    private val solService = ServicePool.solService

    private val accountNumber by lazy { intent.getStringExtra("accountNumber") }
    private val userName by lazy { intent.getStringExtra("userName") }
    private val bank by lazy { intent.getStringExtra("bank") }
    private val amount by lazy { intent.getStringExtra("amount") }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransfer3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initMyAccountViews()
        clickTransfer()
    }

    private fun initViews() {
        //match binding
        with(binding) {
            val dec = DecimalFormat("#,###")
            tvUserNameTransfer3.text = userName
            tvSenderAccountTransfer3.text = accountNumber
            tvAccountNameTransfer3.text = bank
            tvSendMoneyTransfer3.text = "${dec.format(amount?.toBigDecimal())} 원"

            ivBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun initMyAccountViews() {
        lifecycleScope.launch {
            val response = ServicePool.solService.getAccountInfo("1", 1)
            if (response.isSuccessful) {
                with(binding) {
                    tvBankTransfer3.text = response.body()?.data?.bank ?: ""
                    tvNumberTransfer3.text = response.body()?.data?.accountNumber ?: ""
                    tvPriceTransfer3.text = response.body()?.data?.balance?.toBigDecimal()?.toString()
                }
            }
        }
    }

    private fun clickTransfer() {
        binding.btnSendTransfer3.setOnClickListener {
            completeTransfer()
        }
    }

    private fun completeTransfer() {
        val requestTransferDto: RequestTransferDto = requestTransferDto()

        lifecycleScope.launch {
            val response = solService.postTransfer(requestTransferDto)
            if (response.isSuccessful) {
                Log.d("Transfer 서버연결", "onResponse: ${response.message()}")
                Toast.makeText(this@Transfer3Activity, "성공", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
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
                tvPriceTransfer3.text.toString().replace(",", "").toLong(),
                "KAKAO",
                tvNumberTransfer3.text.toString(),
                edtTransferMemoTransfer3.text.toString(),
                edtReceiverMemoTransfer3.text.toString(),
                0
            )
        }
    }
}
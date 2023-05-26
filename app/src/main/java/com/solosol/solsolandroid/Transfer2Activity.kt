package com.solosol.solsolandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.solosol.solsolandroid.databinding.ActivityTransfer2Binding
import com.solosol.solsolandroid.transfer3.Transfer3Activity
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.DecimalFormat
import kotlin.math.floor

class Transfer2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTransfer2Binding
    private var enterAmount: String = ""
    private var myAccountNumber: String = ""

    private val accountNumber by lazy { intent.getStringExtra("accountNumber") }
    private val userName by lazy { intent.getStringExtra("userName") }
    private val bank by lazy { intent.getStringExtra("bank") }

    private fun getKoreaPriceString(): String {
        var remainAmount = this.enterAmount.toBigDecimal();
        val units = listOf(
            BigDecimal(100000000),
            BigDecimal(10000000),
            BigDecimal(1000000),
            BigDecimal(100000),
            BigDecimal(10000),
            BigDecimal(1000),
            BigDecimal(100),
            BigDecimal(10)
        )
        val suffixes = listOf("억", "천만", "백만", "십만", "만", "천", "백", "십")
        val prices = mutableListOf<BigDecimal>(
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
        )

        units.forEachIndexed { index, unit ->
            if (remainAmount >= unit) {
                prices[index] = BigDecimal(floor(remainAmount.toDouble() / unit.toDouble()))
                remainAmount -= prices[index] * unit
            }
        }

        var t = "";
        prices
            .forEachIndexed { index, price ->
                if (price != BigDecimal.ZERO) {
                    t += "${price}${suffixes[index]} "
                }
            }

        if (remainAmount != BigDecimal.ZERO) {
            t += "$remainAmount"
        }

        return "${t.trimEnd()}원"
    }

    private fun updateEtEnterAmount() {
        if (this.enterAmount.isNotEmpty()) {
            binding.btnNextButton.isEnabled = true
            val dec = DecimalFormat("#,###")
            binding.etEnterAmount.setText("${dec.format(this.enterAmount.toBigDecimal())} 원")
            binding.tvLocaleAmount.text = this.getKoreaPriceString()
        } else {
            binding.btnNextButton.isEnabled = false
            binding.etEnterAmount.setText("")
            binding.tvLocaleAmount.text = "0원"
        }
    }

    private fun appendEnterAmount(appendNumber: String) {
        this.enterAmount = if (appendNumber == "-") {
            if (this.enterAmount.length <= 1) {
                ""
            } else {
                this.enterAmount.substring(0, this.enterAmount.length - 1)
            }
        } else {
            if (this.enterAmount == "0") {
                appendNumber
            } else {
                this.enterAmount + appendNumber
            }
        }

        this.updateEtEnterAmount()
    }

    private fun sumEnteredAmountAndr(andr: BigDecimal) {
        this.enterAmount = if (this.enterAmount.isNotEmpty()) {
            this.enterAmount.toBigDecimal().add(andr).toString()
        } else {
            andr.toString()
        }

        this.updateEtEnterAmount()
    }

    private fun initAccount(accountId: String, memberId: Int) {
        lifecycleScope.launch {
            val response = ServicePool.solService.getAccountInfo(accountId, memberId)
            if (response.isSuccessful) {
                val accountInfo = response.body()?.data
                binding.run {
                    val dec = DecimalFormat("#,###")
                    tvBankAndAccount.text =
                        "${accountInfo?.bank?.orEmpty()} ${accountInfo?.accountNumber?.orEmpty()}"
                    tvAmount.text = "${dec.format(accountInfo?.balance?.toBigDecimal() ?: 0)}원"
                    myAccountNumber = accountInfo?.accountNumber.orEmpty()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransfer2Binding.inflate(layoutInflater)
        binding.etEnterAmount.showSoftInputOnFocus = false
        setContentView(binding.root)
        this.updateEtEnterAmount()
        this.initAccount("1", 1)
        binding.buttonKeypad1.setOnClickListener {
            this.appendEnterAmount("1")
        }
        binding.buttonKeypad2.setOnClickListener {
            this.appendEnterAmount("2")
        }
        binding.buttonKeypad3.setOnClickListener {
            this.appendEnterAmount("3")
        }
        binding.buttonKeypad4.setOnClickListener {
            this.appendEnterAmount("4")
        }
        binding.buttonKeypad5.setOnClickListener {
            this.appendEnterAmount("5")
        }
        binding.buttonKeypad6.setOnClickListener {
            this.appendEnterAmount("6")
        }
        binding.buttonKeypad7.setOnClickListener {
            this.appendEnterAmount("7")
        }
        binding.buttonKeypad8.setOnClickListener {
            this.appendEnterAmount("8")
        }
        binding.buttonKeypad9.setOnClickListener {
            this.appendEnterAmount("9")
        }
        binding.buttonKeypad0.setOnClickListener {
            this.appendEnterAmount("0")
        }
        binding.buttonKeypad00.setOnClickListener {
            this.appendEnterAmount("00")
        }
        binding.buttonKeypadBack.setOnClickListener {
            this.appendEnterAmount("-")
        }
        binding.buttonAndr.setOnClickListener {
            this.sumEnteredAmountAndr(BigDecimal(10000))
        }
        binding.buttonAndr2.setOnClickListener {
            this.sumEnteredAmountAndr(BigDecimal(50000))
        }
        binding.buttonAndr3.setOnClickListener {
            this.sumEnteredAmountAndr(BigDecimal(100000))
        }
        binding.buttonAndr4.setOnClickListener {
            this.sumEnteredAmountAndr(BigDecimal(1000000))
        }
        binding.buttonAndr5.setOnClickListener {
            this.sumEnteredAmountAndr(BigDecimal(3773140))
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.tvRecipient.text = userName
        bank?.let {
            binding.tvTransferAccount.text = "${BankName.valueOf(it)} $accountNumber"
        }

        binding.btnNextButton.setOnClickListener {
            Intent(this, Transfer3Activity::class.java).apply {
                putExtra("accountNumber", accountNumber)
                putExtra("userName", userName)
                putExtra("bank", bank)
                putExtra("amount", enterAmount)
                putExtra("myAccountNumber", myAccountNumber)
            }.run {
                startActivity(this)
            }

        }
    }
}
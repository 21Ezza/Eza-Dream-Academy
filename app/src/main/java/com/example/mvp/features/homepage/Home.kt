package com.example.mvp.features.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.R
import com.example.mvp.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapterNotif: AdapterNotif

    private val listTransactionTitle = listOf(
        ItemNotifModel("Top Up E-Wallet", "Gopay - 08123123123", "Rp 14.000.000", "TIPE TOPUP"),
        ItemNotifModel("Transfer Masuk", "BRI - 3453 3434 3435", "Rp 130.000.000", "Transfer Masuk"),
        ItemNotifModel("Pembelian", "Telkomsel - 08123123123", "Rp 14.000.000", "Pembelian"),
        ItemNotifModel("Pembelian", "Telkomsel - 08123123123", "Rp 14.000.000", "Pembelian"),
        ItemNotifModel("Pembelian", "Telkomsel - 08123123123", "Rp 14.000.000", "Pembelian"),
        ItemNotifModel("Pembelian", "Telkomsel - 08123123123", "Rp 14.000.000", "Pembelian"),
        ItemNotifModel("Pembelian", "Telkomsel - 08123123123", "Rp 14.000.000", "Pembelian"),
        ItemNotifModel("Pembelian", "Telkomsel - 08123123123", "Rp 14.000.000", "Pembelian"),


        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.profileCard.setTopTest("Hi, Viola Maulatan")
        binding.profileCard.bootomText = "150 Point"
        binding.profileCard.setTextColor(topColorId = R.color.black)
        binding.profileCard.setTextColor(bottomColorId = R.color.blue)

        binding.profileCard.setIcon(R.drawable.vector_6)

        adapterNotif = AdapterNotif(listTransactionTitle.toMutableList())

        val layoutManager = LinearLayoutManager(this)
        binding.rvTransaction.adapter = adapterNotif
        binding.rvTransaction.layoutManager = layoutManager

        binding.profileCard.apply {
            setEndIconListener {
                startActivity(Intent(this@Home, LayoutNotif::class.java))
                /*Toast.makeText(this@Home, "Click Card Profile", Toast.LENGTH_SHORT).show()*/
            }
        }
        adapterNotif.setOnCardClickListener(rvListener)
    }

    private val rvListener: (accountDetail: String, transactionAmount: String) -> Unit =
        { accountDetail, transactionAmount ->
            startActivity(Intent(this, Detail::class.java).apply {
                putExtra("accountDetail", accountDetail)
                putExtra("transactionAmount", transactionAmount)
            })
        }
}
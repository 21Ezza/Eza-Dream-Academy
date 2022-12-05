package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterNotif: AdaperNotif

    private val listTransactionTitle = listOf(
        ItemNotifModel("Top Up E-Wallet", "Gopay - 08123123123", "Rp 14.000.000", "TIPE TOPUP"),
        ItemNotifModel("Transfer Masuk", "BRI - 3453 3434 3435", "Rp 130.000.000", "Transfer Masuk"),
        ItemNotifModel("Pembelian", "Telkomsel - 08123123123", "Rp 14.000.000", "Pembelian"),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterNotif = AdaperNotif(listTransactionTitle.toMutableList())

        val layoutManager = LinearLayoutManager(this)
        binding.rvTransaction.adapter = adapterNotif
        binding.rvTransaction.layoutManager = layoutManager
    }

}
package com.example.iglocal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        // Inisialisasi View
        val tvAccountName = findViewById<TextView>(R.id.show_username)
        val btnBack = findViewById<Button>(R.id.btn_back)

        // Mengambil dan menampilkan data nama yang dikirim dari MainActivity
        val accountName = intent.getStringExtra("EXTRA_ACCOUNT_NAME")
        tvAccountName.text = accountName ?: "Nama tidak tersedia"

        // Logika Navigasi Back
        btnBack.setOnClickListener {
            finish()
        }
    }
}

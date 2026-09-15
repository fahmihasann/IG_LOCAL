package com.example.iglocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.app.Activity
import android.content.Intent

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etName = findViewById<EditText>(R.id.edit_name)
        val etBio = findViewById<EditText>(R.id.edit_bio)
        val btnSave = findViewById<Button>(R.id.btn_save)

        // Menampilkan data lama yang dikirim dari MainActivity
        etName.setText(intent.getStringExtra("CURRENT_NAME"))
        etBio.setText(intent.getStringExtra("CURRENT_BIO"))

        btnSave.setOnClickListener {
            val newName = etName.text.toString()
            val newBio = etBio.text.toString()

            // Memasukkan data baru ke dalam Intent untuk dikembalikan
            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_NAME", newName)
            resultIntent.putExtra("EXTRA_BIO", newBio)

            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }
}
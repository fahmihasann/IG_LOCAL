package com.example.iglocal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var tvUsername: TextView
    private lateinit var tvBio: TextView
    private lateinit var btnEditProfile: Button
    private lateinit var imgProfile: ImageView

    private val editProfileLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data

            // Mengambil data baru yang dikirim dari EditProfileActivity
            val updatedName = data?.getStringExtra("EXTRA_NAME")
            val updatedBio = data?.getStringExtra("EXTRA_BIO")

            // Memperbarui UI dengan data baru
            if (!updatedName.isNullOrEmpty()) tvUsername.text = updatedName
            if (!updatedBio.isNullOrEmpty()) tvBio.text = updatedBio
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi View di activity_main.xml
        tvUsername = findViewById(R.id.user_profile)
        tvBio = findViewById(R.id.bio)
        btnEditProfile = findViewById(R.id.btn_edit_profile)
        imgProfile = findViewById(R.id.img_profile)

        // Klik Button Edit Profile
        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            // Kirim data saat ini agar bisa ditampilkan di form edit
            intent.putExtra("CURRENT_NAME", tvUsername.text.toString())
            intent.putExtra("CURRENT_BIO", tvBio.text.toString())

            // Jalankan activity
            editProfileLauncher.launch(intent)
        }

        // Klik Icon Foto Profile
        imgProfile.setOnClickListener {
            val intent = Intent(this, PhotoActivity::class.java)
            // Kirim data username untuk ditampilkan di PhotoActivity
            intent.putExtra("EXTRA_ACCOUNT_NAME", tvUsername.text.toString())

            startActivity(intent)
        }
    }
}
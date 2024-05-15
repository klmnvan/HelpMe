package com.example.matuleme.screens

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.matuleme.R
import com.example.matuleme.databinding.ActivityProfileBinding
import com.example.matuleme.databinding.ActivitySignInBinding
import com.example.matuleme.fragments.FragmentCheckEmail
import com.example.matuleme.fragments.FragmentCode
import com.example.matuleme.objects.General.isEmailValid
import com.example.matuleme.objects.PrefManager
import com.example.matuleme.objects.UserStorage
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.journeyapps.barcodescanner.BarcodeView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class Profile : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
        initValues()
        tryGetCode()
    }

    private fun tryGetCode() {
        with(binding) {
            val str = "96c3c2f1-3d52-4e2d-bf90-aad8a4d93556" // Строка, которую нужно кодировать
            val imageView = ImageView(this@Profile)
            val width = resources.displayMetrics.widthPixels
            val barcodeBitmap = BarcodeEncoder().encodeBitmap(str, BarcodeFormat.CODE_128, width, 200)
            //Устанавливаем изображение типа Bitmap
            imageView.setImageBitmap(barcodeBitmap)
            LLForCode.addView(imageView)
        }
    }

    private fun initValues() {
        with(binding){
            with(UserStorage){
                inptName.setText(profile.name + " " + profile.surname)
                inptEmail.setText(profile.email)
            }
        }
    }

    private fun pressBtns(){
        with(binding){
            LLForCode.setOnClickListener {

                if (Settings.System.canWrite(this@Profile)) {
                    Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 100)
                } else {
                    val permissionIntent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                    startActivity(permissionIntent)
                }
                val dialog = FragmentCode()
                dialog.show(supportFragmentManager, "code")

            }
            btnBack.setOnClickListener {
                startActivity(Intent(this@Profile, Home::class.java))
                finish()
            }
            btnSave.setOnClickListener {
                sendRequest()
            }
            imgUser.setOnClickListener {
                startActivity(Intent(this@Profile, ProfileRedact::class.java))
                finish()
            }
        }
    }

    private fun sendRequest() {
        Toast.makeText(this@Profile, "Сохраняю данные профиля", Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                runOnUiThread {
                    startActivity(Intent(this@Profile, Home::class.java))
                    finish()
                }
            } catch (e: Exception) {
                Toast.makeText(this@Profile, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                Log.d("error save profile", e.message.toString())
            }
        }

    }
}
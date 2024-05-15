package com.example.matuleme.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.matuleme.databinding.ActivityNewPasswordBinding
import com.example.matuleme.fragments.FragmentCheckEmail
import com.example.matuleme.objects.Requests
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random


class NewPassword : AppCompatActivity() {
    private lateinit var binding: ActivityNewPasswordBinding
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
    }

    private fun pressBtns(){
        with(binding){
            btnGenerate.setOnClickListener {
                if(inptPhraze.text.length >= 8) {
                    generatePassword(inptPhraze.text.toString())
                } else {
                    Toast.makeText(this@NewPassword, "Фраза слишком короткая", Toast.LENGTH_SHORT).show()
                }
            }
            btnBack.setOnClickListener {
                startActivity(Intent(this@NewPassword, SignIn::class.java))
                finish()
            }
            btnNewPass.setOnClickListener {
                val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("", inptPassword.getText().toString())
                clipboard.setPrimaryClip(clip)
                sendRequest()
            }
        }
    }

    fun generatePassword(phrase: String){
        var newStr = ""
        phrase.forEach { c ->
            newStr += when(c.toLowerCase()) {
                'а' -> "@"
                'б' -> "6"
                'в' -> "|3"
                'г' -> getRand("r", "G")
                'д' -> getRand("d", "D")
                'е' -> getRand("e", "E")
                'ё' -> "3`"
                'ж' -> getRand("#", "Zh")
                'з' -> "3"
                'и' -> "1"
                'й' -> "1`"
                'к' -> getRand("1<", getRand("k", "K"))
                'л' -> getRand("l", "L")
                'м' -> getRand("m", "M")
                'н' -> getRand("|-|","H")
                'о' -> "0"
                'п' -> "n"
                'р' -> getRand("|O", "P")
                'с' -> getRand("c", "C")
                'т' -> getRand("t", "T")
                'у' -> getRand("y", "Y")
                'ф' -> getRand("<|>", "pH")
                'х' -> getRand("><", getRand("x", "X"))
                'ц' -> getRand("u,", "U,")
                'ч' -> getRand("4", "Ch")
                'ш' -> getRand("w", "W")
                'щ' -> getRand("w,", "W,")
                'ь' -> "b"
                'ы' -> "6|"
                'ъ' -> "^6"
                'э' -> "3"
                'ю' -> "|-0"
                'я' -> "9|"
                else -> c
            }
        }
        val regex = Regex("(?=.*[A-ZА-Я])(?=.*[a-zа-я])(?=.*\\d)(?=.*\\s)(?=.*\\p{Punct}).+")
        val rez = regex.containsMatchIn(newStr)
        if (rez) binding.inptPassword.setText(newStr)
        else Toast.makeText(this@NewPassword, "Фраза не подходит", Toast.LENGTH_SHORT).show()
    }

    private fun getRand(s1: String, s2: String): String {
        val rand = Random.nextBoolean()
        return if (rand) s1
        else s2
    }

    private fun sendRequest() {
        with(binding){
            password = inptPassword.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Requests.updatePass(password)
                    runOnUiThread {
                        startActivity(Intent(this@NewPassword, SignIn::class.java))
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@NewPassword, e.message.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("error update pass", e.message.toString())
                }
            }
        }
    }
}
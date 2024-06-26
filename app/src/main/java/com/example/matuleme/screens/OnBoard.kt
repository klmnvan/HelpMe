package com.example.matuleme.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.matuleme.R
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityOnBoardBinding
import com.example.matuleme.databinding.ActivitySplashBinding
import com.example.matuleme.models.OnBoardModel
import com.example.matuleme.objects.PrefManager
import io.paperdb.Paper
import java.util.LinkedList
import java.util.Queue

class OnBoard : CustomActivity(), AnimationListener {
    private lateinit var binding: ActivityOnBoardBinding
    private var queue: Queue<OnBoardModel> = LinkedList()
    private lateinit var animIn: Animation
    private lateinit var animOut: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animIn = AnimationUtils.loadAnimation(this, R.anim.alpha_in)
        animOut = AnimationUtils.loadAnimation(this, R.anim.alpha_out)
        animIn.setAnimationListener(this)
        createQueue()
        pressButton()
    }

    private fun createQueue() {
        queue = Paper.book().read<LinkedList<OnBoardModel>>("queue",
            LinkedList(
                listOf(
                    OnBoardModel("ДОБРО\nПОЖАЛОВАТЬ", "", R.drawable.on_board1, R.drawable.on_board1_b, "Начать", 1),
                    OnBoardModel("Начнем\nпутешествие", "Умная, великолепная и модная коллекция Изучите сейчас", R.drawable.on_board2, R.drawable.on_board2_b, "Далее", 2),
                    OnBoardModel("У Вас Есть Сила,\nЧтобы", "В вашей комнате много красивых и привлекательных растений", R.drawable.on_board3, R.drawable.on_board3_b, "Далее", 3)
                )
            )
        )!!
        saveStateQueue()
        enterQueue(queue.poll()!!)
    }

    private fun saveStateQueue(){
        Paper.book().write("queue", queue)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun enterQueue(el: OnBoardModel){
        with(binding){
            initStage(el.stage)
            main.background = getDrawable(el.background)
            img.setImageResource(el.image)
            txtStartTittle.text = el.tittle
            txtNextTittle.text = el.tittle
            txtDesc.text = el.description
            btn.text = el.buttonText
        }
    }

    private fun initStage(count: Int){
        with(binding){
            when(count){
                1 -> {
                    viewD1.visibility = View.GONE
                    viewD2.visibility = View.VISIBLE
                    viewD3.visibility = View.VISIBLE
                    viewW1.visibility = View.VISIBLE
                    viewW2.visibility = View.GONE
                    viewW3.visibility = View.GONE
                }
                2 -> {
                    viewD1.visibility = View.VISIBLE
                    viewD2.visibility = View.GONE
                    viewD3.visibility = View.VISIBLE
                    viewW1.visibility = View.GONE
                    viewW2.visibility = View.VISIBLE
                    viewW3.visibility = View.GONE
                    txtStartTittle.visibility = View.GONE
                    txtNextTittle.visibility = View.VISIBLE
                    txtDesc.visibility = View.VISIBLE
                }
                3 -> {
                    viewD1.visibility = View.VISIBLE
                    viewD2.visibility = View.VISIBLE
                    viewD3.visibility = View.GONE
                    viewW1.visibility = View.GONE
                    viewW2.visibility = View.GONE
                    viewW3.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun pressButton(){
        with(binding){
            btn.setOnClickListener {
                if(queue.size == 0) {
                    PrefManager.act = 1
                    startActivity(Intent(this@OnBoard, SignIn::class.java))
                    finish()
                }
                else {
                    if(queue.size != 1) {
                        txtStartTittle.startAnimation(animIn)
                    }
                    img.startAnimation(animIn)
                    txtNextTittle.startAnimation(animIn)
                    txtDesc.startAnimation(animIn)
                }
            }
        }
    }

    override fun onAnimationStart(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        with(binding){
            saveStateQueue()
            enterQueue(queue.poll()!!)
            img.startAnimation(animOut)
            txtNextTittle.startAnimation(animOut)
            txtDesc.startAnimation(animOut)
        }
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }

}
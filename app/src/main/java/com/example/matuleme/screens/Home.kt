package com.example.matuleme.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.matuleme.R
import com.example.matuleme.adapters.AdapterShop
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityHomeBinding
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.UserData
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class Home : CustomActivity(), AdapterShop.Listener{
    private lateinit var binding: ActivityHomeBinding
    private val adapterShop = AdapterShop(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()

        /*//Объект для обработки свайпов
        val swipeToDeleteCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                UserData.listCross.removeAt(position)
                initProduct()
            }

            //Штука для окраски заднего фона при удалении (выглядит как не обязательная штука)
            @SuppressLint("ResourceType")
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@Home, R.color.white))
                    .addSwipeLeftActionIcon(R.drawable.btn_big_delete)
                    .setSwipeLeftLabelColor(ContextCompat.getColor(this@Home, R.color.white))
                    .create()
                    .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

            }
        }

        //создается объект ItemTouchHelper с переданным swipeToDeleteCallback
        // и привязывается к RecyclerView с помощью attachToRecyclerView().
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.listViewProduct)*/
        initProduct()
    }

    private fun initProduct() {
        with(binding) {
            listViewProduct.adapter = adapterShop
            listViewProduct.layoutManager = GridLayoutManager(this@Home, 2)
            adapterShop.clear()
            UserData.listCross.forEach {
                adapterShop.addElement(it)
            }
        }
    }

    private fun pressBtns() {
        with(binding){
            btnOpenShop.setOnClickListener {
                startActivity(Intent(this@Home, Shop::class.java))
                finish()
            }
        }
    }

    override fun openCardProduct(el: ShopModelTest) {
        startActivity(Intent(this@Home, CardProduct::class.java))
        finish()
    }

    override fun updateListProduct() {
        initProduct()
    }
}
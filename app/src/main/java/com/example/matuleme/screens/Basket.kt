package com.example.matuleme.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.matuleme.R
import com.example.matuleme.adapters.AdapterBasket
import com.example.matuleme.adapters.AdapterProduct
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityBasketBinding
import com.example.matuleme.databinding.ActivityCardProductBinding
import com.example.matuleme.objects.ProductsStorage

class Basket : CustomActivity(), AdapterBasket.Listener {
    private lateinit var binding: ActivityBasketBinding
    private val adapterBasket = AdapterBasket(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        addSwipeListener()
        pressBtns()
    }

    private fun pressBtns() {
        with(binding){
            btnBack.setOnClickListener {
                startActivity(Intent(this@Basket, Home::class.java))
                finish()
            }
            btnPay.setOnClickListener {
                startActivity(Intent(this@Basket, MyCart::class.java))
                finish()
            }
        }
    }

    private fun addSwipeListener() {
        //Объект для обработки свайпов
        val swipeToDeleteCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                ProductsStorage.listProductBasket.removeAll { it == adapterBasket.listProduct[position] }
                initValues()
            }
        }

        //создается объект ItemTouchHelper с переданным swipeToDeleteCallback
        // и привязывается к RecyclerView с помощью attachToRecyclerView().
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.listViewProduct)
    }

    private fun initValues(){
        with(binding){
            with(ProductsStorage){
                txtSumValue.text = Price.toString()
                txtDeliveryValue.text = "60.20"
                txtTotalValue.text = (Price.toDouble() + 60.2).toString()
                txtCountAllProduct.text = countProductInB.toString() + " тов."

                listViewProduct.adapter = adapterBasket
                listViewProduct.layoutManager = GridLayoutManager(this@Basket, 1)
                adapterBasket.clear()
                listProductBasket.distinct().forEach {
                    adapterBasket.addElement(it)
                }
            }
        }
    }

    override fun updateListProduct() {
        initValues()
    }
}
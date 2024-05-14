package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.matuleme.adapters.AdapterOrder
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityOrdersBinding
import com.example.matuleme.models.OrderModel
import com.example.matuleme.objects.ProductsStorage

class Orders : CustomActivity(), AdapterOrder.Listener {
    private lateinit var binding: ActivityOrdersBinding
    private val adapterOrder = AdapterOrder(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pressBtns()
        initProduct()
    }

    private fun initProduct() {
        with(binding) {
            listViewProduct.adapter = adapterOrder
            listViewProduct.layoutManager = GridLayoutManager(this@Orders, 1)
            adapterOrder.clear()
            ProductsStorage.listOrders.forEach {
                adapterOrder.addElement(it)
            }
        }
    }

    private fun pressBtns(){
        with(binding){
            btnBack.setOnClickListener {
                startActivity(Intent(this@Orders, Home::class.java))
                finish()
            }
        }
    }

    override fun openCardProduct(el: OrderModel) {
        /*startActivity(Intent(this@Orders, CardProduct::class.java))
        finish()*/
    }
}
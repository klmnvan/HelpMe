package com.example.matuleme.screens

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.matuleme.adapters.AdapterProduct
import com.example.matuleme.customActivity.CustomActivity
import com.example.matuleme.databinding.ActivityHomeBinding
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.UserData


class Home : CustomActivity(), AdapterProduct.Listener{
    private lateinit var binding: ActivityHomeBinding
    private val adapterProduct = AdapterProduct(this)

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
            listViewProduct.adapter = adapterProduct
            listViewProduct.layoutManager = GridLayoutManager(this@Home, 2)
            adapterProduct.clear()
            UserData.listCross.forEachIndexed() { i, el ->
                if(i < 2){
                    adapterProduct.addElement(el)
                }
            }
        }
    }

    private fun pressBtns() {
        with(binding){
            btnOpenShop.setOnClickListener {
                startActivity(Intent(this@Home, Basket::class.java))
                finish()
            }
            btnShop.setOnClickListener {
                startActivity(Intent(this@Home, Basket::class.java))
                finish()
            }
            btnOpenFav.setOnClickListener {
                startActivity(Intent(this@Home, Favorites::class.java))
                finish()
            }
            btnOpenNot.setOnClickListener {

            }
            btnOpenProfile.setOnClickListener {

            }
            btnOpenSideMenu.setOnClickListener {

            }
            btnOpenFullAction.setOnClickListener {
                startActivity(Intent(this@Home, Popular::class.java))
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
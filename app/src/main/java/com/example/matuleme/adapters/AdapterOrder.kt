package com.example.matuleme.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matuleme.R
import com.example.matuleme.databinding.ItemOrderBinding
import com.example.matuleme.databinding.ItemProductBaseBinding
import com.example.matuleme.models.OrderModel
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.PrefManager
import com.example.matuleme.objects.ProductsStorage
import com.example.matuleme.objects.ProductsStorage.listProductBasket
import com.example.matuleme.objects.ProductsStorage.listProductFav

class AdapterOrder<T : Any>(private val listener: T): RecyclerView.Adapter<AdapterOrder.Holder>() {
    private var listProduct: MutableList<OrderModel> = ArrayList()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ItemOrderBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(el: OrderModel, list: Listener) {
            with(binding) {
                //Glide.with(root).load("http://i.imgur.com/uELerEW.png").into(img);
                txtTittle.text = el.tittle.toString()
                txtPrice1.text = el.price1.toString() + " р."
                txtPrice1.text = el.price2.toString() + " р."
                txtData.text = el.data
                txtIdOrder.text = el.id
                mainCont.setOnClickListener {
                    ProductsStorage.currentOrder = el
                    list.openCardProduct(el)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listProduct[position], listener as Listener)
    }

    override fun getItemCount(): Int = listProduct.size

    @SuppressLint("NotifyDataSetChanged")
    fun addElement(el: OrderModel) {
        listProduct.add(el)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        listProduct.clear()
        notifyDataSetChanged()
    }

    interface Listener {

        fun openCardProduct(el: OrderModel)

    }
}
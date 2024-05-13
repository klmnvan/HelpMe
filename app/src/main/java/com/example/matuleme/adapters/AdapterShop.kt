package com.example.matuleme.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matuleme.R
import com.example.matuleme.databinding.ItemProductBaseBinding
import com.example.matuleme.models.ShopModelTest
import com.example.matuleme.objects.ProductsStorage
import com.example.matuleme.objects.ProductsStorage.listProductBasket
import com.example.matuleme.objects.ProductsStorage.listProductFav
import com.example.matuleme.objects.UserData

class AdapterShop<T : Any>(private val listener: T): RecyclerView.Adapter<AdapterShop.Holder>() {
    private var listShop: MutableList<ShopModelTest> = ArrayList()

    class Holder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemProductBaseBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(el: ShopModelTest, list: Listener) {
            with(binding) {
                Glide.with(root).load("http://iis.ngknn.ru/NGKNN/МамшеваЮС/MedicMadlab/api/Images/2_news.png").into(img);
                tPrice.text = el.price.toString() + " р."
                tTittle.text = el.name
                btnAddFavorites.setOnClickListener {
                    if (listProductFav.contains(el)) {
                        listProductFav.removeAll{ it == el }
                    } else {
                        listProductFav.add(el)
                    }
                    list.updateListProduct()
                }
                btnAddShop.setOnClickListener {
                    if (listProductBasket.contains(el)) {
                        listProductBasket.removeAll{ it == el }
                    } else {
                        listProductBasket.add(el)
                    }
                }
                mainCont.setOnClickListener {
                    ProductsStorage.currentProduct = el
                    list.openCardProduct(el)
                    list.updateListProduct()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_base, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listShop[position], listener as Listener)
    }

    override fun getItemCount(): Int = listShop.size

    @SuppressLint("NotifyDataSetChanged")
    fun addElement(el: ShopModelTest) {
        listShop.add(el)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        listShop.clear()
        notifyDataSetChanged()
    }

    interface Listener {

        fun openCardProduct(el: ShopModelTest)
        fun updateListProduct()

    }
}
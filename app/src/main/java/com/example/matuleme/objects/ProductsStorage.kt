package com.example.matuleme.objects

import com.example.matuleme.models.OrderModel
import com.example.matuleme.models.ShopModelTest
import io.paperdb.Paper

object ProductsStorage {
    var listProduct: MutableList<ShopModelTest> = ArrayList()
    var listProductFav: MutableList<ShopModelTest> = ArrayList()
    var listProductPopular: MutableList<ShopModelTest> = ArrayList()
    var listProductBasket: MutableList<ShopModelTest> = ArrayList()
    var currentProduct: ShopModelTest = ShopModelTest()
    var currentOrder: OrderModel = OrderModel()
    var currenCategory: String = "Все"
    var listOrders: MutableList<OrderModel> = ArrayList()

    val Price: Int
        get() =  listProductBasket.map { it.price!! }.sum()

    val countProductInB: Int
        get() = listProductBasket.distinct().size

    val allCategories: List<String>
        get() = mutableListOf("Все") + listProduct.map { it.category }.distinct()

    /*var listProductFav: MutableList<ShopModelTest>
        get() = Paper.book().read("fff", ArrayList())!!
        set(value) {
            Paper.book().read("fff", value)
        }*/
}
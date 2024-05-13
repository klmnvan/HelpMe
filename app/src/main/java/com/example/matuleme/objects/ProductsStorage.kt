package com.example.matuleme.objects

import com.example.matuleme.models.ShopModelTest

object ProductsStorage {
    var listProduct: MutableList<ShopModelTest> = ArrayList()
    var listProductFav: MutableList<ShopModelTest> = ArrayList()
    var listProductPopular: MutableList<ShopModelTest> = ArrayList()
    var listProductBasket: MutableList<ShopModelTest> = ArrayList()
    var currentProduct: ShopModelTest = ShopModelTest()

}
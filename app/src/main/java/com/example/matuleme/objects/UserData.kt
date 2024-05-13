package com.example.matuleme.objects

import com.example.matuleme.models.ShopModelTest

object UserData {
    var listCross: MutableList<ShopModelTest> = ArrayList()
    val listAllShop: MutableList<ShopModelTest> = ArrayList()
    val listAllFavorites: MutableList<ShopModelTest> = ArrayList()

    fun addShop(el: ShopModelTest) {
        if(!listAllShop.contains(el)){
            listAllShop.add(el)
        }
    }

    fun addFavorites(el: ShopModelTest) {
        if(!listAllFavorites.contains(el)){
            listAllFavorites.add(el)
        }
    }

    fun getSumShop(){

    }
}
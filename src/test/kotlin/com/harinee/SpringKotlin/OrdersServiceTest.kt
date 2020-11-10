package com.harinee.SpringKotlin

import org.junit.jupiter.api.Test
import java.util.*

class OrdersServiceTest {

    @Test
    fun testCalculateTotalTestCaseOne() {
        //Load mock data to products map
        var productsMap = TreeMap<String, Double>()
        productsMap["Apple"] = 0.6
        productsMap["Orange"] = 0.25

        //Load mock data to products list
        val products = mutableListOf<String>()
        products.add("Apple")
        products.add("Apple")
        products.add("Apple")
        products.add("Apple")
        products.add("Apple")
        products.add("Apple")
        products.add("Apple")
        products.add("Orange")
        products.add("Orange")

        var total = 0.0


        var obj = Orders();

        for(product in products){
            total += obj.calculateTotal(product, productsMap)
        }

        assert(total == 4.7)
    }

    @Test
    fun testCalculateTotalTestCaseTwo() {
        //Load mock data to products map
        var productsMap = TreeMap<String, Double>()
        productsMap["Apple"] = 0.6
        productsMap["Orange"] = 0.25

        //Load mock data to products list
        val products = mutableListOf<String>()
        products.add("Apple")
        products.add("Apple")
        products.add("Apple")
        products.add("Orange")

        var total = 0.0


        var obj = Orders();

        for(product in products){
            total += obj.calculateTotal(product, productsMap)
        }

        assert(total == 2.05)
    }
}
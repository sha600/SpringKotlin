package com.harinee.SpringKotlin

import jdk.nashorn.internal.runtime.JSType.toDouble
import org.springframework.stereotype.Service
import java.util.*

@Service      /*Spring boot registers this as a service*/
class Offers {
    var productsMap = TreeMap<String, Double>()
    var frequencyMap = TreeMap<String, Double>()  /*Store the products added to car and frequency of its occurrence*/
    var total = 0.0
    val products = mutableListOf<String>()  /*Mutable so as to add elements to the list*/
    var orderStatus = "New"
    fun activateOffers(): String {
        productsMap["Apple"] = 0.60
        productsMap["Orange"] = 0.25
        println("Enter the number of products you want to add to your cart")
        var strNumberOfProducts: String? = readLine()
        if (!strNumberOfProducts.isNullOrEmpty() && !strNumberOfProducts.equals("0")){
            var numberOfProducts : Int = strNumberOfProducts.toInt()
            var nums = 1..numberOfProducts
            for(i in nums) {
                println("Enter product ${i}")
                var product = readLine()
                if (!productsMap.containsKey(product)){
                    println("Sorry !! The product ${product} doesn't exist in our inventory")
                    continue
                }
                if (product != null) {
                    products.add(product)  /*Add to the list that maintains the products added to the cart*/
                }
            }
            /*Calculate the frequency of each product*/
            for (name in products){
                /*Collections.frequency returns of the count of elements in the list*/
                frequencyMap.put(name, toDouble(Collections.frequency(products, name)))
            }
            println()
            println("Cart Summary")
            total = calculateTotal(frequencyMap, productsMap)
            println("The total amount of this transaction is $" +total)
            orderStatus = "Order-Submitted"
        }
        else {
            orderStatus = "Order-Submission-Failure"
            println("The number of shopping items cannot be empty")
        }

        return orderStatus

    }

    fun calculateTotal (freqMap : TreeMap<String, Double>, prodMap : TreeMap<String, Double>) : Double{
        for ((k, v) in freqMap) {
            println("$k = $v")
            if(k.equals("Apple")){
                if(v<2){   //Not eligible for offer
                    total += prodMap.get(k)!! * v
                } else {   //Eligible for Offer
                    if(v % 2 == 0.0) total += (prodMap.get(k)!! * (v/2))
                    else {
                        total += (prodMap.get(k)!! * (v/2)) + (prodMap.get(k)!! * 0.5)
                    }
                }
            }
            if(k.equals("Orange")){
                if(v<3){  //Not eligible for offer
                    total += prodMap.get(k)!! * v
                } else {
                    if(v % 3 == 0.0) total += (prodMap.get(k)!! * v) - (prodMap.get(k)!! * (v/3))
                    else {
                        var s = v - v % 3
                        total += (prodMap.get(k)!! * s) - (prodMap.get(k)!! * (s/3)) + (prodMap.get(k)!! * (v % 3))
                    }
                }
            }
            if (!k.equals("Orange") && !k.equals("Apple")) {
                total += (if (prodMap.get(k) != null) prodMap.get(k)!! * v else throw NullPointerException("Expression 'productsMap.get(k)' must not be null"))!!
            }
        }
        return total
    }
}
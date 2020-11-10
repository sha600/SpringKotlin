package com.harinee.SpringKotlin

import org.springframework.stereotype.Service
import java.util.*

@Service               /*Spring boot registers this as a service*/
class Orders {

    fun orderFun() : String {
        var productsMap = TreeMap<String, Double>() //Map to store the product and its cost
        var total = 0.0    /*Total amount of transaction initialized to zero*/
        productsMap["Apple"] = 0.60  /*Adding to productsMap*/
        productsMap["Orange"] = 0.25
        var order_status = "New"

        println("Enter the number of products you want to add to your cart")
        var strNumberOfProducts: String? = readLine()   //Accept User Input
        if (!strNumberOfProducts.isNullOrEmpty() && !strNumberOfProducts.equals("0")){
            var nums = 1..strNumberOfProducts.toInt()  // Defines the range
            for(i in nums) {
                println("Enter product ${i}")  //We use ${} in Kotlin to print
                var product = readLine()
                if (!productsMap.containsKey(product)){
                    println("Sorry !! The product ${product} doesn't exist in our inventory")
                    continue
                }
                if (product != null) {  //null check the product
                    total +=  calculateTotal(product, productsMap) // !! is to add non null assertion. Calculating Total with every iteration
                }
            }
            println("The total amount of this transaction is $" +total)
            order_status = "Order-Submitted" // return the order submission
        }
        else {
            order_status = " Order-Failure"// Failure of Order
            println("The number of shopping items cannot be empty")
        }
        return order_status

    }

    fun calculateTotal(prod : String, prodMap : TreeMap<String, Double>) : Double {
        return prodMap.get(prod)!!
    }

}
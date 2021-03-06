package com.harinee.SpringKotlin

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class OffersServiceTest {

    @Test
    fun testCalculateTotalWithOffer() {
        //Load mock data to products map
        var productsMap = TreeMap<String, Double>()
        productsMap["Apple"] = 0.6
        productsMap["Orange"] = 0.25

        //Load mock data to frequency map
        var frequencyMap = TreeMap<String, Double>()
        frequencyMap["Apple"] = 5.0
        frequencyMap["Orange"] = 5.0

        var obj = Offers();
        var total = obj.calculateTotal(frequencyMap, productsMap)

        assert(total == 2.8)
    }

    @Test
    fun testCalculateTotalWithoutOffer() {
        //Load mock data to products map
        var productsMap = TreeMap<String, Double>()
        productsMap["Apple"] = 0.6
        productsMap["Orange"] = 0.25

        //Load mock data to frequency map
        var frequencyMap = TreeMap<String, Double>()
        frequencyMap["Apple"] = 1.0
        frequencyMap["Orange"] = 2.0

        var obj = Offers();
        var total = obj.calculateTotal(frequencyMap, productsMap)

        assert(total == 1.1)
    }
    
    @Test
    fun testValidateStockTrue() {
        //Load mock data to frequency map
        var frequencyMap = TreeMap<String, Double>()
        frequencyMap["Apple"] = 7.0
        frequencyMap["Orange"] = 2.0

        var obj = Offers();
        var stockBoolean = obj.validateStock(frequencyMap)


        assert(stockBoolean)
    }

    @Test
    fun testValidateAppleOutOfStock() {
        //Load mock data to frequency map
        var frequencyMap = TreeMap<String, Double>()
        frequencyMap["Apple"] = 8.0
        frequencyMap["Orange"] = 3.0

        var obj = Offers();
        var stockBoolean = obj.validateStock(frequencyMap)


        assert(!stockBoolean)
    }

    @Test
    fun testValidateOrangeOutOfStock() {
        //Load mock data to frequency map
        var frequencyMap = TreeMap<String, Double>()
        frequencyMap["Apple"] = 3.0
        frequencyMap["Orange"] = 8.0

        var obj = Offers();
        var stockBoolean = obj.validateStock(frequencyMap)


        assert(!stockBoolean)
    }

}

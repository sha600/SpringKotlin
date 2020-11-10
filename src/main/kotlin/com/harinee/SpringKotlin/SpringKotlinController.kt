package com.harinee.SpringKotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.ListenableFuture


@RestController
class SpringKotlinController {

    @Autowired       //To Inject Kafka template to the controller
    var kafkaTemplate: KafkaTemplate<String, String>? = null
    val topic:String = "OrderStatus"  //Name of the Kafka Topic created in local

    @RequestMapping ("/orders")   // Step 1 of the assesment
    fun orders() : String
    {
        var obj = Orders();  //Obj definition in Kotlin. No new key word
        var status = obj.orderFun()  /* Invoke method inside Orders class */

        return status
    }

    @RequestMapping ("/offers")   // Step 2, 3 and 5 of the assesment
    fun offers() : ResponseEntity<String>
    {
        var obj = Offers();  //Obj definition in Kotlin. No new key word
        var status = obj.activateOffers()  /* Invoke method inside Offers class */
        var message = "Hello Customer, "

        if (status.equals("Order-Submitted")){
            message += "Your Order has moved to Submitted Status."
        } else {
            message + "Your Order submission failed. Please try again."
        }

        /*In the below line, we are using KafkaTemplate to send the message to a topic called OrderStatus.
        This will return a ListenableFuture object from which we can get the result of this action*/
        var lf : ListenableFuture<SendResult<String, String>> = kafkaTemplate?.send(topic, message)!!
        var sendResult: SendResult<String, String> = lf.get()

        return ResponseEntity.ok(sendResult.producerRecord.value() + " status sent to Customer")
    }
}
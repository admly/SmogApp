package com.millerindustries.smog

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver

/**
 * Created by Adam Młynarczyk on 10.04.18.
 */

class AirQualityResultReceiver(val handler: Handler): ResultReceiver(handler){
    private var receiver: Receiver? = null

    // Defines our event interface for communication
    interface Receiver {
        fun onReceiveResult(resultCode: Int, resultData: Bundle)
    }


    // Setter for assigning the receiver
    public fun setReceiver(receiver: Receiver) {
        this.receiver = receiver
    }

    // Delegate method which passes the result to the receiver if the receiver has been assigned
    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
        if (receiver != null) {
            receiver!!.onReceiveResult(resultCode, resultData)
        }
    }
}
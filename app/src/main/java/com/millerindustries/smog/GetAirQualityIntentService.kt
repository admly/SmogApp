package com.millerindustries.smog

import android.app.IntentService
import android.content.Intent
import android.app.Activity
import android.os.Bundle


/**
 * Created by Adam Młynarczyk on 10.04.18.
 */


class GetAirQualityIntentService(val name: String = "GetAirQualityIntentService"): IntentService(name){
    override fun onHandleIntent(intent: Intent?) {


        val resultValue = getAirQuality()


        // Extract the receiver passed into the service
        val rec: AirQualityResultReceiver = intent!!.getParcelableExtra("receiver")
        // Extract additional values from the bundle


        // To send a message to the Activity, create a pass a Bundle
        val bundle = Bundle()
        bundle.putString("resultValue", "My Result Value. Passed in: $resultValue`")
        // Here we call send passing a resultCode and the bundle of extras
        rec.send(Activity.RESULT_OK, bundle)
    }

    private fun getAirQuality() : String {
        getTheStationsList()
        calculateTheNearestStation()
        getAirQualityFromStation()

        //get air q from API and save to shared prefs, if not possible return last known from shared prefs. If shared prefs empty - return default.
        return NO_DATA
    }

    private fun calculateTheNearestStation(){}

    private fun getTheStationsList(){}

    private fun getAirQualityFromStation(){}

}
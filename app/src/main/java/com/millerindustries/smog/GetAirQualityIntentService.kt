package com.millerindustries.smog

import android.app.IntentService
import android.content.Intent
import com.millerindustries.smog.const.NO_DATA


/**
 * Created by Adam Młynarczyk on 10.04.18.
 */


class GetAirQualityIntentService(val name: String = "GetAirQualityIntentService"): IntentService(name){
    override fun onHandleIntent(intent: Intent?) {

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
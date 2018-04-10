package com.millerindustries.smog

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.os.Handler


/**
 * Created by Adam Młynarczyk on 09.04.18.
 */


class WidgetProvider: AppWidgetProvider(){



    val TAG = "WidgetProvider"
    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                appWidgetId: Int) {

        // Create an Intent to launch MainActivity when clicked
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        // Construct the RemoteViews object
        val views = RemoteViews(context.packageName, R.layout.layout_widget_simple)

        // Widgets allow click handlers to only launch pending intents
        views.setOnClickPendingIntent(R.id.widgetImageView, pendingIntent)
        views.setImageViewResource(R.id.widgetImageView, getWidgetImageViewByAirQuality(getAirQuality()))
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun getWidgetImageViewByAirQuality(airQuality: String) : Int{
        when(airQuality){
            GOOD -> return R.mipmap.ic_launcher
            AVERAGE -> return R.mipmap.ahaaa
            BAD -> return R.mipmap.ahaaa
            NO_DATA -> return R.mipmap.ahaaa
        }

        Log.d(TAG, "Unable to return RemoteView based on air quality from API. Major problem detected." )
        throw IllegalStateException()
    }

    fun setupServiceReceiver() {
        val receiverForTest = AirQualityResultReceiver(Handler())
        // This is where we specify what happens when data is received from the service
        receiverForTest.setReceiver(object : AirQualityResultReceiver.Receiver {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                if (resultCode == RESULT_OK) {
                    val resultValue = resultData.getString("resultValue")
                }
            }
        })
    }


}
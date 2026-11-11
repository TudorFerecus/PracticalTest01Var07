package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.Date
import kotlin.math.sqrt

class ProcessingThread(    private val context: Context,
) : Thread() {
    override fun run() {

        while (!isInterrupted) {
            sendMessage()
            sleepThread()
        }

        Log.d(Constants.TAG, "ProcessingThread stopped")
    }
    private fun sendMessage() {

        var v1 = (1..10).random()
        var v2 = (1..10).random()
        var v3 = (1..10).random()
        var v4 = (1..10).random()
        val intent = Intent(Constants.ACTION_1).apply {
            putExtra(Constants.KEY_NUMBER_1, v1.toString())
            putExtra(Constants.KEY_NUMBER_2, v2.toString())
            putExtra(Constants.KEY_NUMBER_3, v3.toString())
            putExtra(Constants.KEY_NUMBER_4, v4.toString())
            setPackage(context.packageName)
        }

        Log.d(Constants.TAG, "Sending broadcast $v1 $v2 $v3 $v4")
        context.sendBroadcast(intent)
    }
    private fun sleepThread() {
        try {
            sleep(Constants.SLEEP_TIME_MS)
        } catch (e: InterruptedException) {
            Log.d(Constants.TAG, "ProcessingThread interrupted during sleep")
            interrupt()
        }
    }
}
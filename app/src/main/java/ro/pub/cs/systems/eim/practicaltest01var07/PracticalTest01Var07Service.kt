package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class PracticalTest01Var07Service : Service() {
    private var processingThread: ProcessingThread? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(Constants.TAG, "PracticalTest01Service.onCreate() invoked")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.CHANNEL_ID,
                Constants.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        val notification: Notification = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("PracticalTest01 Service")
            .setContentText("Calculare.")
            .build()

        startForeground(Constants.NOTIFICATION_ID, notification)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (processingThread == null || !processingThread!!.isAlive) {
            processingThread = ProcessingThread(this)
            processingThread?.start()
        }
        return START_REDELIVER_INTENT

    }

    override fun onDestroy() {
        Log.d(Constants.TAG, "PracticalTest01Service.onDestroy() invoked")
        processingThread?.interrupt()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
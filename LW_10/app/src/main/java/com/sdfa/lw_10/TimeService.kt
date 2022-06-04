package com.sdfa.lw_10

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class TimeService : Service() {
    private var counter = 0
    private lateinit var job: Job
    private var myBinder = MyBinder()
    private var frequency = 1

    inner class MyBinder : Binder() {
        fun getService() : TimeService {
            Log.d("SERVICE", "this@TimeService returned")
            return this@TimeService
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("SERVICE", "Service bound")
        frequency = intent?.getIntExtra("freq", 1)!!
        Log.d("SERVICE", "Received freq:" + frequency.toString())
        return myBinder
    }

    @Override
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("SERVICE", "onStartCommand called")

        job = GlobalScope.launch {
            while (true) {
                delay(1000)
                Log.d("SERVICE", "Timer: " + counter)
                counter++
                val intent = Intent(BROADCAST_TIME_EVENT)

                if (frequency != null && counter % frequency == 0) {
                    intent.putExtra("counter", counter)
                    sendBroadcast(intent)
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("SERVICE", "Service destroyed")
        job.cancel()
        super.onDestroy()
    }

    fun getCounter() : Int {
        return counter
    }
}
package com.sdfa.lw_10

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

const val BROADCAST_TIME_EVENT = "com.sdfa.lw_10.timeevent"

class MainActivity : AppCompatActivity() {
    var myService: TimeService? = null
    var isBound = false
    var receiver: BroadcastReceiver? = null
    var frequency: Int = 1

    val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as TimeService.MyBinder
            myService = binder.getService()
            isBound = true
            Log.d("MAIN", "Service connected, isBound: " + if(isBound) {"True"} else {"False"})
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val counter = intent.getIntExtra("counter", 0)
                Toast.makeText(context, counter.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        val filter = IntentFilter(BROADCAST_TIME_EVENT)
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    fun buttonStartService(view: View) {
        val intent = Intent(this, TimeService::class.java)
        intent.putExtra("freq", frequency)
        Log.d("MAIN", "Sending freq: " + frequency)
        bindService(
            intent,
            myConnection,
            Context.BIND_AUTO_CREATE
        )

        startService(Intent(this, TimeService::class.java))

        Log.d("MAIN", "Trying to bind")
    }

    fun buttonStopService(view: View) {
        stopService(Intent(this, TimeService::class.java))
        unbindService(myConnection)
    }

    fun buttonGetValue(view: View) {
        if (isBound) {
            Toast.makeText(this, myService!!.getCounter().toString(), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Сервис не активен", Toast.LENGTH_SHORT).show()
        }
    }

    fun buttonRestart(view: View) {
        buttonStopService(view)
        buttonStartService(view)
    }

    fun buttonSetFrequency(view: View) {
        var freq : String = findViewById<EditText>(R.id.input_frequency).text.toString()
        frequency = freq.toInt()
    }
}
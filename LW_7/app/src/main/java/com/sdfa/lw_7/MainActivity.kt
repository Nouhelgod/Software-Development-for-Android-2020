package com.sdfa.lw_7

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private val MY_PERMISSIONS_REQUEST_LOCATION = 1
    private var locationManager: LocationManager? = null
    private var coords_list: MutableList<MutableList<String>> = mutableListOf()


    private fun showInfo(location: Location? = null) {
        val isGpsOn = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkOn = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        findViewById<TextView>(R.id.gps_status).text = if (isGpsOn) "GPS ON" else "GPS OFF"
        findViewById<TextView>(R.id.network_status).text = if (isNetworkOn) "NETWORK ON" else "NETWORK OFF"

        if (location != null) {
            if (location.provider == LocationManager.GPS_PROVIDER) {
                findViewById<TextView>(R.id.gps_coords).text =
                    "Широта: " + location.latitude.toString() +
                    "\nДолгота: " + location.longitude.toString()
            }

            if (location.provider == LocationManager.NETWORK_PROVIDER) {
                findViewById<TextView>(R.id.network_coords).text =
                    "Широта: " + location.latitude.toString() +
                    "\nДолгота: " + location.longitude.toString()
            }
        }
    }

    fun refreshList(location: Location? = null) {
        val coords_text_view = findViewById<TextView>(R.id.coords_list)

        coords_text_view.text = ""
        if (coords_list.isNotEmpty()) {
            for (obj in coords_list) {

                val l1 = Location("")
                var status = ""

                l1.longitude = obj[2].toDouble()
                l1.latitude = obj[1].toDouble()


                if (location != null) {
                    Log.i("Info", "long: " + l1.longitude.toString() + "/" + location!!.longitude)
                    Log.i("Info", "lat: " + l1.latitude.toString() + "/" + location!!.latitude)
                }

                if (location == null) {
                    Log.i("Info", "Location null")
                } else {
                    Log.i("Info", "Location != null")

                }


                if (location != null) {
                    Log.i("Info", "Dist: " + l1.distanceTo(location))
                }

                if (location != null && l1.distanceTo(location) < 100f ) {status = "Рядом"}
                else {status = "Далековато"}


                coords_text_view.text = coords_text_view.text.toString() + obj[0] + " : " + status + "\n"

            }
        } else {
            coords_text_view.text = "Ничего не отслеживаем"
        }
    }

    fun stopTracking() {
        locationManager!!.removeUpdates(locationListener)
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) { showInfo(location); refreshList(location) }

        override fun onProviderDisabled(provider: String) { showInfo() }

        override fun onProviderEnabled(provider: String) { showInfo() }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) { showInfo() }
    }

    fun startTracking() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            }
            else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_LOCATION)

            }
        }
        else {
            locationManager!!.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000, 10f, locationListener)


            locationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 1000, 10f, locationListener)

            showInfo()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
       if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
           super.onRequestPermissionsResult(requestCode, permissions, grantResults)
       } else {
           //
       }
    }

    override fun onResume() {
        super.onResume()
        startTracking()
    }

    override fun onPause() {
        super.onPause()
        stopTracking()
    }

    fun buttonOpenSettings(view: View) {
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    fun buttonAddCoords(view: View) {

        val name = findViewById<EditText>(R.id.coords_name).text.toString()
        val longitude = findViewById<EditText>(R.id.coords_longitude).text.toString()
        val latitude = findViewById<EditText>(R.id.coords_latitude).text.toString()

        if (name != "" && longitude != "" && latitude != "") {
            val obj = mutableListOf(name, longitude, latitude)
            coords_list.add(obj)
            refreshList()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        startTracking()
    }
}
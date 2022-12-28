package com.samsung.sam_day9_location

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.permissionx.guolindev.PermissionX

class MainActivity : AppCompatActivity() {
    lateinit var locationManager: LocationManager

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val button=findViewById<Button>(R.id.button)
        val button2=findViewById<Button>(R.id.button2)



        PermissionX.init(this)
            .permissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {

                    locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f,
                        object : LocationListener {
                            override fun onLocationChanged(l: Location) {

                                Toast.makeText(
                                    this@MainActivity,
                                    " ${l.latitude}  ${l.longitude}",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                println(l)
                            }

                        })

                }else{ }
            }


        button.setOnClickListener {






            Toast.makeText(this, "${locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.latitude}", Toast.LENGTH_SHORT).show()





        }


        button2.setOnClickListener {

            fusedLocationClient.lastLocation.addOnSuccessListener {

                Toast.makeText(this, "${it.latitude}", Toast.LENGTH_SHORT).show()
            }
            
        }



    }
}
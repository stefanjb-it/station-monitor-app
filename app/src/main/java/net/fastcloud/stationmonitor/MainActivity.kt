package net.fastcloud.stationmonitor

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient;
    private lateinit var locationCallback: LocationCallback;
    private var locationRequest: LocationRequest = LocationRequest();
    private val PERMISSIONS = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET)
    private val PERMISSIONS_ALL = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(PERMISSIONS.toTypedArray(), PERMISSIONS_ALL)
        }
        HafasApi().retrofitService.getStationDeparture(
            691543
        ).enqueue(object :
            Callback<List<Departure>> {
                override fun onResponse(
                    call: Call<List<Departure>>,
                    response: Response<List<Departure>>
                ) {
                    println(response.body())
                }

                override fun onFailure(call: Call<List<Departure>>, t: Throwable) {
                    println("Cannot get location")
                }
            }
        )
        HafasApi().retrofitService.getStationArrival(
            691543
        ).enqueue(object :
            Callback<List<Departure>> {
                override fun onResponse(
                    call: Call<List<Departure>>,
                    response: Response<List<Departure>>
                ) {
                    println(response.body())
                }

                override fun onFailure(call: Call<List<Departure>>, t: Throwable) {
                    println("Cannot get location")
                }
            }
        )
    }
}
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
        /*fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations){
                    println(location.toString())
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        fusedLocationClient.getCurrentLocation(
            LocationRequest.PRIORITY_HIGH_ACCURACY,
            object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) =
                    CancellationTokenSource().token

                override fun isCancellationRequested() = false
            })
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    HafasApi().retrofitService.getStations(
                        location.latitude,
                        location.longitude,
                        250
                    ).enqueue(object :
                        Callback<List<Station>> {
                        override fun onResponse(
                            call: Call<List<Station>>,
                            response: Response<List<Station>>
                        ) {
                            println(response.body())
                        }

                        override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                            println("Cannot get location")
                        }
                    })
                }
            }*/
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
    }
}
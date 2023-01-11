package net.fastcloud.stationmonitor

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalTime

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
        /*HafasApi().retrofitService.getStationDeparture(
            691543,
            30
        ).enqueue(object :
            Callback<List<Departure>> {
                override fun onResponse(
                    call: Call<List<Departure>>,
                    response: Response<List<Departure>>
                ) {
                    println(response.body()?.last())
                }

                override fun onFailure(call: Call<List<Departure>>, t: Throwable) {
                    println("Cannot get location")
                }
            }
        )
        HafasApi().retrofitService.getStationArrival(
            691543,
            30
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
        )*/
        HafasApi().retrofitService.getJourneys(
            691123,
            691143,
            (System.currentTimeMillis()/1000).toString()
        ).enqueue(object :
            Callback<Routes> {
            override fun onResponse(
                call: Call<Routes>,
                response: Response<Routes>
            ) {
                println(response.body())
                println((System.currentTimeMillis()/1000).toString())
            }

            override fun onFailure(call: Call<Routes>, t: Throwable) {
                println("Cannot get location")
                println((System.currentTimeMillis()/1000).toString())
                println(t.message)
            }
        }
        )
    }
}
package net.fastcloud.stationmonitor

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

class HafasApi {
    val retrofit: Retrofit
    val retrofitService: HafasApiService
    init {
        val moshi = Moshi.Builder().build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://oebb-hafas.fastcloud-it.net")
            .build()
        retrofitService = retrofit.create(HafasApiService::class.java)
    }
}
interface HafasApiService {
    @GET("/locations/nearby/")
    fun getStations(@Query("latitude") latitude: Double, @Query("longitude") longitude: Double, @Query("distance") distance: Int):Call<List<Station>>
    //fun getStations(@Path("lat") latitude: Double, @Path("long") longitude: Double, @Path("dist") distance: Int): Call<List<Station>>
    //@GET("/stations")
}
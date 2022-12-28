package net.fastcloud.stationmonitor

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Station(
    val type: String, val id: Int, val name: String, val location: Location, val products: Products,
    val distance: Int?, val isMeta: Boolean?
    ) {
}
@JsonClass(generateAdapter = true)
data class Location(
    val type: String, val id: Int, val latitude: Double, val longitude: Double
    ) {
}
@JsonClass(generateAdapter = true)
data class Products(
    val nationalExpress: Boolean?, val national: Boolean?, val interregional: Boolean?, val regional: Boolean?,
    val suburban: Boolean?, val bus: Boolean?, val ferry: Boolean?, val subway: Boolean?, val tram: Boolean?, val onCall: Boolean?
    ) {
}
@JsonClass(generateAdapter = true)
data class Line(
    val type: String, val id: String, val fahrtNr: Int, val name: String, val public: Boolean, val adminCode: String, val productName: String,
    val mode: String, val product: String, val operator: Operator
    ) {
}
@JsonClass(generateAdapter = true)
data class Operator(
    val type: String, val id: String, val name:String
    ) {
}
@JsonClass(generateAdapter = true)
data class Routes(
    val earlierRef: String, val laterRef: String, val journeys: ArrayList<Journey>, val realtimeDataFrom: Int
    ) {
}
@JsonClass(generateAdapter = true)
data class Journey(
    val type: String, val legs: ArrayList<Leg>, val refreshToken: String, val cycle: Cycle
    ) {
}
@JsonClass(generateAdapter = true)
data class Cycle(
    val type: String?, val min: String?, val max: String?
)
@JsonClass(generateAdapter = true)
data class Leg(
    val origin: Station, val destination: Station, val departure: String, val plannedDeparture: String,  val departureDelay: Int,
    val arrival: String, val plannedArrival: String, val arrivalDelay: Int, val reachable: Boolean, val tripId: String, val line: Line,
    val direction: String, val currentLocation: Location, val arrivalPlatform: String, val plannedArrivalPlatform: String,
    val arrivalPrognosisType: String, val departurePlatform: String, val  plannedDeparturePlatform: String, val departurePrognosisType: String,
    val cycle: Cycle, val alternatives: ArrayList<Alternative>
    ) {
}
//TODO change when parameter
@JsonClass(generateAdapter = true)
data class Alternative(
        val tripId: String, val line: Line, val direction: String, val when: String, val plannedWhen: String, val delay: String
    ) {
}
//TODO more requests: https://json2kt.com/
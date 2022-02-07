package de.kfzteile24.app.data.locations.remote.api

import de.kfzteile24.app.data.locations.remote.dto.LocationResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApi {

    @GET("/api/architecture/web-chapter-coding-challenge-api/locations")
    suspend fun getLocations(): Response<LocationResponseDto>

    @GET("/api/architecture/web-chapter-coding-challenge-api/vehicles/{locationName}")
    suspend fun getVehicle(@Path("locationName") locationName: String)
}
package de.kfzteile24.app.data.vehicles.remote.api

import de.kfzteile24.app.data.vehicles.remote.dto.VehicleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VehiclesApi {

    @GET("/api/architecture/web-chapter-coding-challenge-api/vehicles/{locationName}")
    suspend fun getVehicles(@Path("locationName") locationName: String) : Response<VehicleResponseDto>
}
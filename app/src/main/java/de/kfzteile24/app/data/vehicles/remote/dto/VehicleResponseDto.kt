package de.kfzteile24.app.data.vehicles.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleResponseDto(
    @SerialName("data")
    val data: List<VehicleDto>
)

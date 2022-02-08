package de.kfzteile24.app.data.vehicles.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleDto(
    @SerialName("fuel")
    val fuel: Double,
    @SerialName("id")
    val id: Int,
    @SerialName("locationId")
    val locationId: Int,
    @SerialName("model")
    val model: String,
    @SerialName("numberPlate")
    val numberPlate: String,
    @SerialName("position")
    val position: PositionDto?,
    @SerialName("vin")
    val vin: String
)
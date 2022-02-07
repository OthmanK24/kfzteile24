package de.kfzteile24.app.data.locations.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponseDto(
  @SerialName("data")
  val locationData: List<LocationData>
)

@Serializable
data class LocationData(
  @SerialName("id")
  val id: Int,
  @SerialName("name")
  val name: String
)

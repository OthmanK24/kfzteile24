package de.kfzteile24.app.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.kfzteile24.app.domain.common.BaseResult
import de.kfzteile24.app.domain.vehicles.entity.VehicleEntity
import de.kfzteile24.app.domain.vehicles.usecase.VehicleUseCase
import de.kfzteile24.app.presentation.location.LocationScreenState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel constructor(private val useCase: VehicleUseCase) : ViewModel() {
    private val state = MutableStateFlow<LocationScreenState>(LocationScreenState.Init)
    val mState: StateFlow<LocationScreenState> get() = state

    private val vehicles = MutableStateFlow<List<VehicleEntity>>(mutableListOf())
    val mVehicles: StateFlow<List<VehicleEntity>> get() = vehicles

    fun fetchVehicles(locationName: String) {
        viewModelScope.launch {
            useCase.execute(locationName)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    when (result) {
                        is BaseResult.Success -> {
                            result.data.data?.let { vehicles.value = it }
                        }
                        is BaseResult.Error -> {
                            showToast(result.rawResponse.message ?: result.toString())
                        }
                    }
                }
        }
    }

    private fun showToast(message: String) {
        state.value = LocationScreenState.ShowToast(message)
    }

    private fun setLoading() {
        state.value = LocationScreenState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = LocationScreenState.IsLoading(false)
    }
}

sealed class DetailScreenState {
    object Init : DetailScreenState()
    data class IsLoading(val isLoading: Boolean) : DetailScreenState()
    data class OnError(val message: String) : DetailScreenState()
    data class ShowToast(val toastMessage: String) : DetailScreenState()
}
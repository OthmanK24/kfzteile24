package de.kfzteile24.app.presentation.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.kfzteile24.app.domain.common.BaseResult
import de.kfzteile24.app.domain.locations.entity.LocationEntity
import de.kfzteile24.app.domain.locations.usecase.LocationUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LocationViewModel constructor(private val locationUseCase: LocationUseCase) : ViewModel() {
    private val state = MutableStateFlow<LocationScreenState>(LocationScreenState.Init)
    val mState: StateFlow<LocationScreenState> get() = state

    private val locations = MutableStateFlow<List<LocationEntity>>(mutableListOf())
    val mLocations: StateFlow<List<LocationEntity>> get() = locations

    init {
        fetchLocations()
    }

    private fun fetchLocations() {
        viewModelScope.launch {
            locationUseCase.execute()
                .onStart {
                    setLoading()
                }
                .catch {  exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    when(result) {
                        is BaseResult.Success -> {
                            locations.value = result.data
                        }
                        is BaseResult.Error -> {
                            showToast(result.rawResponse)
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
sealed class LocationScreenState{
    object Init: LocationScreenState()
    data class IsLoading(val isLoading: Boolean) : LocationScreenState()
    data class OnError(val message: String) : LocationScreenState()
    data class ShowToast(val toastMessage: String) : LocationScreenState()
}
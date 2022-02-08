package de.kfzteile24.app.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import de.kfzteile24.app.R
import de.kfzteile24.app.databinding.DetailFragmentBinding
import de.kfzteile24.app.databinding.LocationFragmentBinding
import de.kfzteile24.app.domain.locations.entity.LocationEntity
import de.kfzteile24.app.domain.vehicles.entity.VehicleEntity
import de.kfzteile24.app.presentation.common.extension.gone
import de.kfzteile24.app.presentation.common.extension.showToast
import de.kfzteile24.app.presentation.common.extension.visible
import de.kfzteile24.app.presentation.location.LocationAdapter
import de.kfzteile24.app.presentation.location.LocationScreenState
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinApplication.Companion.init

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailFragmentBinding.bind(view)
        setupRecyclerView()
        observe()
        initUi()
    }

    private fun initUi() {
        try {
            arguments?.getString("location_name").let { locationName ->
                locationName?.let {
                    detailViewModel.fetchVehicles(it)
                }
            }
        }catch(e: Exception){
            e.printStackTrace()
        }
    }


    private fun setupRecyclerView() {
        val mAdapter = VehicleAdapter(mutableListOf())
        binding.vehiclesRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun observe() {
        observeState()
        observeLocations()
    }

    private fun observeLocations() {
        lifecycleScope.launchWhenResumed {
            detailViewModel.mVehicles.collect {
            handleVehicles(it)
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launchWhenResumed {
            detailViewModel.mState.collect {
                handleState(it)
            }
        }
    }

    private fun handleState(state: LocationScreenState) {
        when (state) {
            is LocationScreenState.IsLoading -> handleLoading(state.isLoading)
            is LocationScreenState.ShowToast -> requireActivity().showToast(state.toastMessage)
            is LocationScreenState.Init -> Unit
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingProgressBar.visible()
        } else {
            binding.loadingProgressBar.gone()
        }
    }

    private fun handleVehicles(vehicles: List<VehicleEntity>) {
        binding.vehiclesRecyclerView.adapter?.let {
            if (it is VehicleAdapter) {
                it.updateList(vehicles)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
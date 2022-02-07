package de.kfzteile24.app.presentation.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.kfzteile24.app.R
import de.kfzteile24.app.databinding.LocationFragmentBinding
import de.kfzteile24.app.domain.locations.entity.LocationEntity
import de.kfzteile24.app.presentation.common.extension.gone
import de.kfzteile24.app.presentation.common.extension.showToast
import de.kfzteile24.app.presentation.common.extension.visible
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : Fragment(R.layout.location_fragment) {

    private val mViewModel: LocationViewModel by viewModel()

    private var _binding: LocationFragmentBinding? = null
    private val locationFragmentBinding get() = _binding!!

    private val onItemClick: (LocationEntity) -> Unit = { location ->
        navigateToDetail(location)
    }

    private fun navigateToDetail(entity: LocationEntity) {
        findNavController().navigate(
            R.id.action_locationFragment_to_detailFragment,
            bundleOf("location_name" to entity.name)
        )
        //LocationFragmentDirections.actionLocationFragmentToDetailFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LocationFragmentBinding.bind(view)
        setupRecyclerView()
        observe()
    }

    private fun setupRecyclerView() {
        val mAdapter = LocationAdapter(mutableListOf(), onItemClick)
        locationFragmentBinding.rvList.apply {
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
            mViewModel.mLocations.collect {
                handleLocations(it)
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launchWhenResumed {
            mViewModel.mState.collect {
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
            locationFragmentBinding.loadingProgressBar.visible()
        } else {
            locationFragmentBinding.loadingProgressBar.gone()
        }
    }

    private fun handleLocations(locations: List<LocationEntity>) {
        locationFragmentBinding.rvList.adapter?.let {
            if (it is LocationAdapter) {
                it.updateList(locations)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
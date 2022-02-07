package de.kfzteile24.app.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import de.kfzteile24.app.R
import de.kfzteile24.app.databinding.DetailFragmentBinding

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailFragmentBinding.bind(view)
        initUi()
    }

    private fun initUi() {
        arguments?.getString("location_name").let {
            binding.tvName.text = it
        }
    }
}
package com.java90apps.spotifyrebus.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.java90apps.spotifyrebus.databinding.FragmentHomeBinding
import com.java90apps.spotifyrebus.domain.base.BaseFragment
import com.java90apps.spotifyrebus.domain.constants.ONE_UNIT
import com.java90apps.spotifyrebus.domain.extensions.hideProgressBar
import com.java90apps.spotifyrebus.domain.extensions.hideRetryButton
import com.java90apps.spotifyrebus.domain.extensions.hideTextError
import com.java90apps.spotifyrebus.domain.extensions.navigateWithDirection
import com.java90apps.spotifyrebus.domain.extensions.showProgressBar
import com.java90apps.spotifyrebus.domain.extensions.showRetryButton
import com.java90apps.spotifyrebus.domain.extensions.showTextError
import com.java90apps.spotifyrebus.domain.models.StateResult
import com.java90apps.spotifyrebus.ui.adapters.ChannelsAdapter
import com.java90apps.spotifyrebus.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpObservers()
        setUpListeners()
    }

    private fun setUpRecyclerView() {
        binding.rvChannels.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ChannelsAdapter() { channelId ->
                navigateToPlayList(channelId)
            }
            addItemDecoration(DividerItemDecoration(requireContext(), ONE_UNIT))
        }
    }

    private fun setUpListeners() {
        binding.btnLoadChannelsAgain.setOnClickListener {
            viewModel.getChannelsRecommended()
        }
    }

    private fun setUpObservers() {
        with(viewModel) {
            channels.observe(viewLifecycleOwner) { result ->
                when(result) {
                    is StateResult.Loading -> {
                        binding.pbChannels.showProgressBar()
                        binding.tvChannelsError.hideTextError()
                        binding.btnLoadChannelsAgain.hideRetryButton()
                    }
                    is StateResult.Success -> {
                        with(binding) {
                            pbChannels.hideProgressBar()
                            tvChannelsError.hideTextError()
                            btnLoadChannelsAgain.hideRetryButton()

                            rvChannels.adapter?.let { adapter ->
                                result.data?.let { list ->
                                    (adapter as ChannelsAdapter).submitList(list.body)
                                }
                            }
                        }
                    }
                    is StateResult.Failed -> {
                        with(binding) {
                            pbChannels.showProgressBar()
                            tvChannelsError.showTextError()
                            btnLoadChannelsAgain.showRetryButton()
                        }
                    }
                }
            }
        }
    }

    private fun navigateToPlayList(channelId: Int) {
        navigateWithDirection(HomeFragmentDirections.actionHomeFragmentToChannelFragment(channelId))
    }
}
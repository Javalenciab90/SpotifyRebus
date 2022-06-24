package com.java90apps.spotifyrebus.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.java90apps.spotifyrebus.databinding.FragmentChannelClipsBinding
import com.java90apps.spotifyrebus.domain.base.BaseFragment
import com.java90apps.spotifyrebus.domain.extensions.hideProgressBar
import com.java90apps.spotifyrebus.domain.extensions.hideRetryButton
import com.java90apps.spotifyrebus.domain.extensions.hideTextError
import com.java90apps.spotifyrebus.domain.extensions.showProgressBar
import com.java90apps.spotifyrebus.domain.extensions.showRetryButton
import com.java90apps.spotifyrebus.domain.extensions.showTextError
import com.java90apps.spotifyrebus.domain.models.StateResult
import com.java90apps.spotifyrebus.ui.viewmodels.ChannelPlayListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChannelClipsFragment : BaseFragment<FragmentChannelClipsBinding>(FragmentChannelClipsBinding::inflate) {

    private val viewModel by viewModels<ChannelPlayListViewModel>()

    private val args: ChannelClipsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlayListChannel(args.channelId)
        //setUpRecyclerView()
        setUpObservers()
        setUpListeners()
    }

    private fun setUpRecyclerView() {
        binding.rvClips.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setUpListeners() {
        binding.btnLoadClipsAgain.setOnClickListener {
            viewModel.getPlayListChannel(args.channelId)
            with(binding) {
                pbClips.hideProgressBar()
                tvClipsError.hideTextError()
                btnLoadClipsAgain.hideRetryButton()
            }
        }
    }

    private fun setUpObservers() {
        with(viewModel) {
            playList.observe(viewLifecycleOwner) { result ->
                when(result) {
                    is StateResult.Loading -> {
                        binding.pbClips.showProgressBar()
                    }
                    is StateResult.Success -> {
                        with(binding) {
                            pbClips.hideProgressBar()
                            tvClipsError.hideTextError()
                            btnLoadClipsAgain.hideRetryButton()

                            // TODO: Create and  Set the adpater
                        }
                    }
                    is StateResult.Failed -> {
                        with(binding) {
                            pbClips.showProgressBar()
                            tvClipsError.showTextError()
                            btnLoadClipsAgain.showRetryButton()
                        }
                    }
                }
            }
        }
    }
}
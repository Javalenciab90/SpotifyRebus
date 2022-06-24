package com.java90apps.spotifyrebus.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.java90apps.spotifyrebus.databinding.FragmentChannelClipsBinding
import com.java90apps.spotifyrebus.domain.base.BaseFragment
import com.java90apps.spotifyrebus.domain.extensions.hideProgressBar
import com.java90apps.spotifyrebus.domain.extensions.hideRetryButton
import com.java90apps.spotifyrebus.domain.extensions.hideTextError
import com.java90apps.spotifyrebus.domain.extensions.navigateWithDirection
import com.java90apps.spotifyrebus.domain.extensions.showProgressBar
import com.java90apps.spotifyrebus.domain.extensions.showRetryButton
import com.java90apps.spotifyrebus.domain.extensions.showTextError
import com.java90apps.spotifyrebus.domain.models.AudioClipModel
import com.java90apps.spotifyrebus.domain.models.StateResult
import com.java90apps.spotifyrebus.ui.adapters.PlayListAdapter
import com.java90apps.spotifyrebus.ui.viewmodels.ChannelPlayListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChannelClipsFragment : BaseFragment<FragmentChannelClipsBinding>(FragmentChannelClipsBinding::inflate) {

    private val viewModel by viewModels<ChannelPlayListViewModel>()

    private val args: ChannelClipsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlayListChannel(args.channelId)
        setUpRecyclerView()
        setUpObservers()
        setUpListeners()
    }

    private fun setUpRecyclerView() {
        binding.rvClips.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PlayListAdapter() {
                playClip(it)
            }
        }
    }

    private fun playClip(clip: AudioClipModel) {
        navigateWithDirection(ChannelClipsFragmentDirections.actionChannelFragmentToPlayClipFragment(clip))
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

                            rvClips.adapter?.let { adapter ->
                                (adapter as PlayListAdapter).submitList(result.data)
                            }
                        }
                    }
                    is StateResult.Failed -> {
                        Toast.makeText(requireContext(), "No se pudo encontrar la lista!. Intenta nuevamente!", Toast.LENGTH_LONG).show()
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
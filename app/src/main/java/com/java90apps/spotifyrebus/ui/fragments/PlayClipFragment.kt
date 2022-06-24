package com.java90apps.spotifyrebus.ui.fragments

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.java90apps.spotifyrebus.databinding.FragmentMediaPlayerBinding
import com.java90apps.spotifyrebus.domain.base.BaseFragment

class PlayClipFragment : BaseFragment<FragmentMediaPlayerBinding>(FragmentMediaPlayerBinding::inflate) {

    private val args: PlayClipFragmentArgs by navArgs()

    private lateinit var mediaPlayer: MediaPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMediaPlayer()
        setUpListeners()
    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(requireContext(), Uri.parse(args.clipToPlay.urls.high_mp3))
        mediaPlayer.prepare()
    }

    private fun setUpListeners() {
        with(binding) {
            Glide.with(requireContext())
                .load(args.clipToPlay.urls.image)
                .into(ivClip)

            tvTitle.text = args.clipToPlay.title

            btnStop.setOnClickListener {
                mediaPlayer.stop()
                mediaPlayer.reset()
                initMediaPlayer()
            }

            btnPause.setOnClickListener {
                mediaPlayer.pause()
            }

            btnPlay.setOnClickListener {
                mediaPlayer.start()
            }
        }
    }
}
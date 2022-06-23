package com.java90apps.spotifyrebus.domain.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.IllegalArgumentException

abstract class BaseFragment<VB: ViewBinding>(
    private  val bindingInflater: (Inflater: LayoutInflater) -> VB
): Fragment() {

    private var _binding: VB? = null

    val  binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if(_binding == null)
            throw IllegalArgumentException("binding cannot be null")
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
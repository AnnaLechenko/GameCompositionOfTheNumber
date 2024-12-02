package com.annalech.gamecompositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.annalech.gamecompositionofthenumber.R
import com.annalech.gamecompositionofthenumber.databinding.FragmentFinishGameBinding

class GameFinishFragment:Fragment() {
    private var _binding : FragmentFinishGameBinding? = null
    private val binding : FragmentFinishGameBinding
        get() = _binding ?: throw RuntimeException("  FragmentFinishGameBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinishGameBinding.inflate(inflater,container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.annalech.gamecompositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.annalech.gamecompositionofthenumber.R
import com.annalech.gamecompositionofthenumber.databinding.FragmentFinishGameBinding
import com.annalech.gamecompositionofthenumber.domain.entity.GameResult



class GameFinishFragment:Fragment() {


    private val args by navArgs<GameFinishFragmentArgs>()

    private var _binding: FragmentFinishGameBinding? = null
    private val binding: FragmentFinishGameBinding
        get() = _binding ?: throw RuntimeException("  FragmentFinishGameBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinishGameBinding.inflate(inflater, container, false)
        return binding.root
    }


    //кнопка назад на переход страницы выбора упровня или другую
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListners()
        bindViews()


    }

    private fun bindViews() {
        binding.gameResult = args.gameResult
    }


    private fun setupClickListners() {

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun retryGame() {
        findNavController().popBackStack()
    }


}
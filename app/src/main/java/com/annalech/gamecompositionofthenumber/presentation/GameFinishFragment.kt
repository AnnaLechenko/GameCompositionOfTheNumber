package com.annalech.gamecompositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.annalech.gamecompositionofthenumber.R
import com.annalech.gamecompositionofthenumber.databinding.FragmentFinishGameBinding
import com.annalech.gamecompositionofthenumber.domain.entity.GameResult

class GameFinishFragment:Fragment() {
    private lateinit var gameResult: GameResult


    private var _binding : FragmentFinishGameBinding? = null
    private val binding : FragmentFinishGameBinding
        get() = _binding ?: throw RuntimeException("  FragmentFinishGameBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseGameResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinishGameBinding.inflate(inflater,container, false)
        return  binding.root
    }


    //кнопка назад на переход страницы выбора упровня или другую
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               retryGame()
            }

        })

        binding.buttonRetry.setOnClickListener{
            retryGame()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseGameResult(){
             requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
                 gameResult = it
             }  }

    private fun retryGame(){
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }




    companion object{

        private const val KEY_GAME_RESULT = "result game"

        fun newInstance(gameResult: GameResult):GameFinishFragment{
            return GameFinishFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}
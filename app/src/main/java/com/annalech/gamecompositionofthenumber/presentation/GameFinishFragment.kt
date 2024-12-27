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
       setupClickListners()
        bindViews()


    }
private fun bindViews(){
    binding.imageResult.setImageResource(getSmileResId())
    binding.tvRequieredAnswer.text = String.format(
        getString(R.string.requiredAnswer),
        gameResult.gameSettings.minCountOfRightAnswer
    )
    binding.tvScoreAnswer.text = String.format(
        getString(R.string.scoreAnswer),
        gameResult.countOfRightAnswer
    )
    binding.tvRequieredPercentage.text = String.format(
        getString(R.string.requiered_percent),
        gameResult.gameSettings.minPercentOfRightAnswer
    )
    binding.tvScorePercentage.text = String.format(
        getString(R.string.score_perce),
       getPercentRightAnswers()   )
}

    private fun getPercentRightAnswers() = with(gameResult){
        if (countOfRightAnswer==0){
            0
        }else {
            ( (countOfRightAnswer/countOfQuestions.toDouble())*100).toInt()
        }
    }


    private fun getSmileResId():Int{
        return if(gameResult.winner){
            R.drawable.suscess_final_ic
        }else{
            R.drawable.no_sucsess_final
        }
    }

    private fun setupClickListners(){
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
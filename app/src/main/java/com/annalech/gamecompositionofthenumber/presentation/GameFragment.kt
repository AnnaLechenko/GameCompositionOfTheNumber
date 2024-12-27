package com.annalech.gamecompositionofthenumber.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.annalech.gamecompositionofthenumber.R
import com.annalech.gamecompositionofthenumber.databinding.FragmentGameBinding
import com.annalech.gamecompositionofthenumber.domain.entity.GameResult
import com.annalech.gamecompositionofthenumber.domain.entity.GameSetting
import com.annalech.gamecompositionofthenumber.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level : Level

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            )[GameViewModel::class.java]
    }
    private val tvOption by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tbOption1)
            add(binding.tbOption2)
            add(binding.tbOption3)
            add(binding.tbOption4)
            add(binding.tbOption5)
            add(binding.tbOption6)
        }
    }

    private var _binding : FragmentGameBinding? = null
    private val binding : FragmentGameBinding
        get() = _binding ?: throw RuntimeException("  FragmentGameBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseLevel()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater,container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
         setClickListnerToOption()
        viewModel.startGame(level)


    }

    private fun setClickListnerToOption(){
        for(option in tvOption){
            option.setOnClickListener{
                viewModel.chooseAnswer(option.text.toString().toInt())
            }
        }
    }


    private fun observeViewModel(){
        //установка текста кнопкам игры
        viewModel.question.observe(viewLifecycleOwner){
            binding.tvQustionSum.text = it.sum.toString()
            binding.tvLeftNumber.text = it.visibibleNumber.toString()
            for (i in 0 until tvOption.size){
                tvOption[i].text = it.options[i].toString()
            }
        }
        viewModel.percentRightAnswer.observe(viewLifecycleOwner){
            binding.progressBar.setProgress(it,true)
        }
        viewModel.enoughContOfRightAnswer.observe(viewLifecycleOwner){
            binding.tvProgressAnswer.setTextColor(getColorByState(it))
        }
        viewModel.enoughPerrcentOfRightAnswer.observe(viewLifecycleOwner){
            binding.progressBar.progressTintList = ColorStateList.valueOf(getColorByState(it))
        }
        viewModel.formattedTime.observe(viewLifecycleOwner){
            binding.tvTimer.text = it
        }
        viewModel.minPercent.observe(viewLifecycleOwner){
            binding.progressBar.secondaryProgress =it
        }
        viewModel.gameResult.observe(viewLifecycleOwner){
            launchGameFinishFragment(it)
        }
        viewModel.progressAnswer.observe(viewLifecycleOwner){
            binding.tvProgressAnswer.text = it
        }

    }

    private fun getColorByState(goodState:Boolean):Int{
        val colorResId = if (goodState){
            android.R.color.holo_green_light
        } else{
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun launchGameFinishFragment(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()

    }
    private fun parseLevel(){
         requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }


    companion object{

        const val  NAME = "GameFragment"
        private const val KEY_LEVEL =  "level"

        fun newInstance(level: Level):GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL,level)
                }
            }
        }
    }
}
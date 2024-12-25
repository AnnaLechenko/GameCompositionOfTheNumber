package com.annalech.gamecompositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.annalech.gamecompositionofthenumber.R
import com.annalech.gamecompositionofthenumber.databinding.FragmentGameBinding
import com.annalech.gamecompositionofthenumber.domain.entity.GameSetting
import com.annalech.gamecompositionofthenumber.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level : Level

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun parseLevel(){
        level = requireArguments().getSerializable(KEY_LEVEL) as Level
    }


    companion object{

        private const val KEY_LEVEL =  "level"

        fun newInstance(level: Level):GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL,level)
                }
            }
        }
    }
}
package com.annalech.gamecompositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.annalech.gamecompositionofthenumber.R

import com.annalech.gamecompositionofthenumber.databinding.FragmentChooseLevelBinding
import com.annalech.gamecompositionofthenumber.domain.entity.Level

class ChooseLevelFragment: Fragment() {

    private var _binding : FragmentChooseLevelBinding? = null
    private val binding : FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("  FragmentChooseLevelBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseLevelBinding.inflate(inflater,container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLevelTest.setOnClickListener{
            transitionToLevel(Level.TESTY)
        }

        binding.buttonLevelEasy.setOnClickListener{
            transitionToLevel(Level.EASY)
        }

        binding.buttonLevelNormal.setOnClickListener{
            transitionToLevel(Level.NORMAL)
        }

        binding.buttonLevelHard.setOnClickListener{
            transitionToLevel(Level.HARD)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun transitionToLevel(level: Level){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFragment.newInstance(level))
            .addToBackStack(null)
            .commit()
    }


    companion object{
        fun newInstance(): ChooseLevelFragment{
            return ChooseLevelFragment()
        }
    }
}
package com.example.testaiwe.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.testaiwe.R
import com.example.testaiwe.databinding.FragmentMainBinding
import com.example.testaiwe.di.InfoComponent
import com.example.testaiwe.view.adapters.ItemAdapter
import com.example.testaiwe.view.viewmodel.InfoViewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val component: InfoComponent by lazy {
        InfoComponent.build(requireActivity().application)
    }
    private val viewModel: InfoViewModel by lazy {
        component.infoViewModel().create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ItemAdapter()
        binding.recyclerView.adapter = adapter

        binding.button2.setOnClickListener {
            val messageCompleteAction = {
                Toast.makeText(context, "Parse is complete!", Toast.LENGTH_LONG).show()
            }

            val messageErrorAction = { error: String ->
                Toast.makeText(context, "Parse is not complete:\n${error}", Toast.LENGTH_LONG)
                    .show()
            }
            viewModel.parseInfo(messageCompleteAction, messageErrorAction)
        }
        binding.button.setOnClickListener {
            viewModel.getInfo { error ->
                Toast.makeText(context, "Error on get Info:\n${error}", Toast.LENGTH_LONG)
                    .show()
            }
        }

        viewModel.info.observe(viewLifecycleOwner) {
            binding.textViewActive.text =
                getString(R.string.active_cryptocurrencies, it.activeCryptocurrencies)
            binding.textViewEnded.text = getString(R.string.ended_icos, it.endedIcos)
            binding.textViewMarkets.text = getString(R.string.markets, it.markets)
            binding.textViewOngoing.text = getString(R.string.ongoing_icos, it.ongoingIcos)
            binding.textViewUpcoming.text = getString(R.string.upcoming_icos, it.upcomingIcos)

            adapter.submitList(it.marketCapsPercentage)
        }
    }

}

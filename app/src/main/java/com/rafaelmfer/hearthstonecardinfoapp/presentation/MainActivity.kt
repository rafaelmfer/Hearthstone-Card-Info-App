package com.rafaelmfer.hearthstonecardinfoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.rafaelmfer.commons.extensions.gone
import com.rafaelmfer.commons.extensions.viewBinding
import com.rafaelmfer.commons.extensions.visible
import com.rafaelmfer.hearthstonecardinfoapp.data.repository.State
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardsInfoModel
import com.rafaelmfer.heartstonecardinfoapp.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainViewModel>()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val cardsClassesAdapter = CardsClassesAdapter()
    private val cardAdapter = CardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.onViewCreated()
    }

    private fun ActivityMainBinding.onViewCreated() {
        setupRecyclers()
        observables()
    }

    private fun ActivityMainBinding.setupRecyclers() {
        rvCardsClasses.apply {
            adapter = cardsClassesAdapter
            itemAnimator = DefaultItemAnimator()
        }
        rvCards.apply {
            adapter = cardAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun observables() {
        viewModel.run {
            cardsInfoLiveData.observe(this@MainActivity) {
                setupCardsClasses(it)
            }

            cardsLiveData.observe(this@MainActivity) {
                setupCardList(it)
            }
        }
    }

    private fun setupCardsClasses(state: State<CardsInfoModel>) {
        when (state) {
            is State.Loading -> {
                binding.pbCardsClasses.visible
            }
            is State.Success -> {
                binding.pbCardsClasses.gone
                cardsClassesAdapter.addList(state.model.classes)
                cardsClassesAdapter.setAction {
                    viewModel.getCardsByClasses(playerClass = it)
                }
            }
            is State.Error -> {
                binding.pbCardsClasses.gone
                binding.pbCards.gone
                binding.tvInstructionsCardList.text = state.message
            }
        }
    }

    private fun setupCardList(state: State<List<CardModel>>) {
        when (state) {
            is State.Loading -> {
                binding.pbCards.visible
                binding.tvInstructionsCardList.gone
            }
            is State.Success -> {
                binding.pbCards.gone
                binding.tvInstructionsCardList.gone
                cardAdapter.addList(state.model)
            }
            is State.Error -> {
                binding.pbCards.gone
                binding.tvInstructionsCardList.apply {
                    visible
                    text = state.message
                }
            }
        }
    }
}
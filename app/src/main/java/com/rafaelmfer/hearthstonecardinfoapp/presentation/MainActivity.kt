package com.rafaelmfer.hearthstonecardinfoapp.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DefaultItemAnimator
import com.rafaelmfer.commons.extensions.onSingleClick
import com.rafaelmfer.commons.extensions.viewBinding
import com.rafaelmfer.hearthstonecardinfoapp.data.repository.State
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardsInfoModel
import com.rafaelmfer.heartstonecardinfoapp.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainViewModel>()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val cardsClassesAdapter = CardsClassesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.onViewCreated()
    }

    private fun ActivityMainBinding.onViewCreated() {
        setDarkModeButton()
        setupRecycler()
        observables()
    }

    private fun ActivityMainBinding.setDarkModeButton() {
        mbtDarkMode.onSingleClick {
            when (this@MainActivity.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }
    }

    private fun ActivityMainBinding.setupRecycler() {
        rvCardsClasses.apply {
            adapter = cardsClassesAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun observables() {
        viewModel.cardsInfoLiveData.observe(this@MainActivity) {
            setupCardsList(it)
        }
    }

    private fun setupCardsList(state: State<CardsInfoModel>) {
        when (state) {
            is State.Loading -> {}
            is State.Success -> {
                cardsClassesAdapter.addList(state.model.classes)
            }
            is State.Error -> {}
        }

    }
}
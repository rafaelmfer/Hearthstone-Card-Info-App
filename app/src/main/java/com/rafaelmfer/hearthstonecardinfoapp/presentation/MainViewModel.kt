package com.rafaelmfer.hearthstonecardinfoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelmfer.hearthstonecardinfoapp.data.repository.State
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardsInfoModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.repository.IHearthstoneRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: IHearthstoneRepository
) : ViewModel() {

    private val cardsInfoMutableLiveData = MutableLiveData<State<CardsInfoModel>>()
    val cardsInfoLiveData: LiveData<State<CardsInfoModel>> get() = cardsInfoMutableLiveData

    init {
        getAllCards()
    }

    private fun getAllCards() {
        cardsInfoMutableLiveData.postValue(State.Loading)
        viewModelScope.launch {
            try {
                val result = repository.getCardsInfo()
                cardsInfoMutableLiveData.postValue(result)
            } catch (ex: Exception) {
                cardsInfoMutableLiveData.postValue(State.Error(ex.localizedMessage))
            }
        }
    }
}
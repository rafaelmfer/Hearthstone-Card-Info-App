package com.rafaelmfer.hearthstonecardinfoapp.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelmfer.hearthstonecardinfoapp.data.repository.State
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardsInfoModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.repository.IHearthstoneRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: IHearthstoneRepository
) : ViewModel() {

    private val cardsInfoMutableLiveData = MutableLiveData<State<CardsInfoModel>>()
    val cardsInfoLiveData: LiveData<State<CardsInfoModel>> get() = cardsInfoMutableLiveData

    private val cardsMutableLiveData = MutableLiveData<State<List<CardModel>>>()
    val cardsLiveData: LiveData<State<List<CardModel>>> get() = cardsMutableLiveData

    init {
        getAllCards()
    }

    @VisibleForTesting
    fun getAllCards() {
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

    fun getCardsByClasses(playerClass: String) {
        cardsMutableLiveData.postValue(State.Loading)
        viewModelScope.launch {
            try {
                val result = repository.getCardsByClass(playerClass)
                cardsMutableLiveData.postValue(
                    State.Success(
                        (result as State.Success).model
                            .filter {
                                it.img != null
                            }
                    )
                )
            } catch (ex: Exception) {
                cardsMutableLiveData.postValue(State.Error(ex.localizedMessage))
            }
        }
    }
}
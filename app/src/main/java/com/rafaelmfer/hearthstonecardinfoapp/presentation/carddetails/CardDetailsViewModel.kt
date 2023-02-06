package com.rafaelmfer.hearthstonecardinfoapp.presentation.carddetails

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelmfer.hearthstonecardinfoapp.data.repository.State
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.repository.IHearthstoneRepository
import kotlinx.coroutines.launch

class CardDetailsViewModel(
    private val cardId: String,
    private val repository: IHearthstoneRepository
) : ViewModel() {

    private val cardMutableLiveData = MutableLiveData<State<CardModel>>()
    val cardLiveData: LiveData<State<CardModel>> get() = cardMutableLiveData

    init {
        getSingleCard(cardId)
    }

    @VisibleForTesting
    fun getSingleCard(cardId: String) {
        cardMutableLiveData.postValue(State.Loading)
        viewModelScope.launch {
            try {
                val result = repository.getSingleCard(cardId)
                cardMutableLiveData.postValue(State.Success((result as State.Success).model[0]))
            } catch (ex: Exception) {
                cardMutableLiveData.postValue(State.Error(ex.localizedMessage))
            }
        }
    }
}
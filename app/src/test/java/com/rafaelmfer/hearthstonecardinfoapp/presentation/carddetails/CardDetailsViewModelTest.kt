package com.rafaelmfer.hearthstonecardinfoapp.presentation.carddetails

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rafaelmfer.hearthstonecardinfoapp.MainCoroutineRule
import com.rafaelmfer.hearthstonecardinfoapp.data.FakeHearthstoneRepository
import com.rafaelmfer.hearthstonecardinfoapp.data.repository.State
import com.rafaelmfer.hearthstonecardinfoapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.pauseDispatcher
import kotlinx.coroutines.test.resumeDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class CardDetailsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var hearthstoneRepository: FakeHearthstoneRepository

    // Class under test
    private lateinit var cardDetailsViewModel: CardDetailsViewModel

    @Before
    fun setUp() {
        hearthstoneRepository = FakeHearthstoneRepository()
        cardDetailsViewModel = CardDetailsViewModel("", hearthstoneRepository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get single card info _ show loading then should return one single card`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            val cardId = "ONY_029"

            //WHEN
            mainCoroutineRule.pauseDispatcher()
            cardDetailsViewModel.getSingleCard(cardId)

            //THEN
            MatcherAssert.assertThat(cardDetailsViewModel.cardLiveData.getOrAwaitValue(), Is.`is`(State.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat(
                (cardDetailsViewModel.cardLiveData.value as State.Success).model,
                Matchers.`is`(FakeHearthstoneRepository.cardModel.first { it.cardId == cardId })
            )
        }
    }

    @Test
    fun `get all cards info _ should return error`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            hearthstoneRepository.shouldReturnError(true)
            val cardId = "ONY_029"

            //WHEN
            mainCoroutineRule.pauseDispatcher()
            cardDetailsViewModel.getSingleCard(cardId)

            //THEN
            MatcherAssert.assertThat(cardDetailsViewModel.cardLiveData.getOrAwaitValue(), Is.`is`(State.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat(
                cardDetailsViewModel.cardLiveData.value,
                Matchers.`is`(not(State.Success(FakeHearthstoneRepository.cardModel.first { it.cardId == cardId })))
            )
        }
    }
}
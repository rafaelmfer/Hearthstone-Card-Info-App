package com.rafaelmfer.hearthstonecardinfoapp.presentation

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
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
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
class MainViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var hearthstoneRepository: FakeHearthstoneRepository

    // Class under test
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        hearthstoneRepository = FakeHearthstoneRepository()
        mainViewModel = MainViewModel(hearthstoneRepository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get all cards info _ should return true`() {
        mainCoroutineRule.runBlockingTest {
            //WHEN
            mainCoroutineRule.pauseDispatcher()
            mainViewModel.getAllCards()

            //THEN
            MatcherAssert.assertThat(mainViewModel.cardsInfoLiveData.getOrAwaitValue(), `is`(State.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat(
                (mainViewModel.cardsInfoLiveData.value as State.Success).model, Matchers.`is`(FakeHearthstoneRepository.cardInfoModel)
            )
        }
    }

    @Test
    fun `get all cards info _ should return error`() {
        mainCoroutineRule.runBlockingTest {
            //GIVEN
            hearthstoneRepository.shouldReturnError(true)

            //WHEN
            mainCoroutineRule.pauseDispatcher()
            mainViewModel.getAllCards()

            //THEN
            MatcherAssert.assertThat(mainViewModel.cardsInfoLiveData.getOrAwaitValue(), `is`(State.Loading))
            mainCoroutineRule.resumeDispatcher()
            MatcherAssert.assertThat(
                (mainViewModel.cardsInfoLiveData.value as State.Error).message, Matchers.`is`(State.Error(FakeHearthstoneRepository.CARD_NOT_FOUND).message)
            )
        }
    }

    @Test
    fun `get cards by class _ should return true`() {
        //GIVEN
        val playerClass = "mage"

        //WHEN
        mainCoroutineRule.pauseDispatcher()
        mainViewModel.getCardsByClasses(playerClass)

        //THEN
        MatcherAssert.assertThat(mainViewModel.cardsLiveData.getOrAwaitValue(), `is`(State.Loading))
        mainCoroutineRule.resumeDispatcher()
        MatcherAssert.assertThat(
            (mainViewModel.cardsLiveData.value as State.Success).model, `is`(FakeHearthstoneRepository.cardModel.filter { it.img != null })
        )
    }

    @Test
    fun `get cards by class _ should return error`() {
        //GIVEN
        hearthstoneRepository.shouldReturnError(true)
        val playerClass = "mage"

        //WHEN
        mainCoroutineRule.pauseDispatcher()
        mainViewModel.getCardsByClasses(playerClass)

        //THEN
        MatcherAssert.assertThat(mainViewModel.cardsLiveData.getOrAwaitValue(), `is`(State.Loading))
        mainCoroutineRule.resumeDispatcher()
        MatcherAssert.assertThat(
            mainViewModel.cardsLiveData.value, Matchers.`is`(not(State.Success(FakeHearthstoneRepository.cardModel)))
        )
    }
}
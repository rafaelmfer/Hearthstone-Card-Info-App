package com.rafaelmfer.hearthstonecardinfoapp.presentation

import android.app.Application
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.rafaelmfer.hearthstonecardinfoapp.data.network.HTTPClient
import com.rafaelmfer.hearthstonecardinfoapp.data.remote.api.IHearthstoneApi
import com.rafaelmfer.hearthstonecardinfoapp.data.repository.HearthstoneRepository
import com.rafaelmfer.hearthstonecardinfoapp.domain.repository.IHearthstoneRepository
import com.rafaelmfer.hearthstonecardinfoapp.presentation.carddetails.CardDetailsActivity
import com.rafaelmfer.hearthstonecardinfoapp.presentation.carddetails.CardDetailsViewModel
import com.rafaelmfer.hearthstonecardinfoapp.util.RecyclerViewMatcher
import com.rafaelmfer.heartstonecardinfoapp.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

/**
 * Scenario:
 *
 * This this test goes from the MainActivity to the CardDetailsActivity
 * It tests if you can select the card's class, then choose one card from the list of that class,
 * then open the next screen with the details of that card chosen
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
//END TO END test to black box test the app
class MainActivityTest : KoinTest {

    private lateinit var repository: IHearthstoneRepository
    private lateinit var viewModel: MainViewModel
    private lateinit var appContext: Application

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    private lateinit var decorView: View

    /**
     * As we use Koin as a Service Locator Library to develop our code, we'll also use Koin to test our code.
     * at this step we will initialize Koin related code to be able to use it in out testing.
     */
    @Before
    fun init() {
        stopKoin()//stop the original app koin
        appContext = ApplicationProvider.getApplicationContext()
        val myModule = module {
            single { HTTPClient().create(IHearthstoneApi::class) }
            single<IHearthstoneRepository> { HearthstoneRepository(get()) }
            viewModel { MainViewModel(get()) }
            viewModel { (cardId: String) -> CardDetailsViewModel(cardId, get()) }
        }
        //declare a new koin module
        startKoin {
            modules(listOf(myModule))
        }
        //Get our real repository
        repository = get()
        viewModel = get()

        activityScenarioRule.scenario.onActivity { activity ->
            decorView = activity.window.decorView
        }

        Thread.sleep(3000L)
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun checkItemWhenScrollOnRecyclerCardClasses() {
        val recyclerCardClasses = R.id.rv_cards_classes

        ActivityScenario.launch(MainActivity::class.java)
        waitViewAppear(onView(withId(recyclerCardClasses)))

        RecyclerViewMatcher.scrollToPosition(recyclerCardClasses, 5)
        RecyclerViewMatcher.hasViewInPosition(recyclerCardClasses, 5)

        RecyclerViewMatcher.scrollToPosition(recyclerCardClasses, 2)
        RecyclerViewMatcher.hasViewInPosition(recyclerCardClasses, 2)
    }

    @Test
    fun checkItemWhenScrollOnRecyclerCards() {
        val recyclerCardClasses = R.id.rv_cards_classes
        val recyclerCards = R.id.rv_cards

        ActivityScenario.launch(MainActivity::class.java)
        waitViewAppear(onView(withId(recyclerCardClasses)))

        RecyclerViewMatcher.scrollToPosition(recyclerCardClasses, 11)
        RecyclerViewMatcher.clickOnPosition(recyclerCardClasses, 5)
        Thread.sleep(20000L)

        RecyclerViewMatcher.scrollToPosition(recyclerCards, 11)
        RecyclerViewMatcher.hasViewInPosition(recyclerCards, 11)

        RecyclerViewMatcher.scrollToPosition(recyclerCards, 4)
        RecyclerViewMatcher.hasViewInPosition(recyclerCards, 4)
    }

    @Test
    fun checkIfItIsPossibleToChooseACardToSeeTheDetails() {
        val recyclerCardClasses = R.id.rv_cards_classes
        val recyclerCards = R.id.rv_cards

        ActivityScenario.launch(MainActivity::class.java)
        waitViewAppear(onView(withId(recyclerCardClasses)))

        RecyclerViewMatcher.clickOnPosition(recyclerCardClasses, 5)
        Thread.sleep(20000L)

        RecyclerViewMatcher.scrollToPosition(recyclerCards, 11)
        RecyclerViewMatcher.hasViewInPosition(recyclerCards, 11)

        RecyclerViewMatcher.clickOnPosition(recyclerCards, 11)

        intended(hasComponent(CardDetailsActivity::class.qualifiedName))
    }

    private fun waitViewAppear(viewInteraction: ViewInteraction) {
        var i = 500
        var throwable: Throwable? = null
        while (i > 0) {
            try {
                viewInteraction.check(matches(isDisplayed()))
                break
            } catch (t: Throwable) {
                i--
                throwable = t
                Thread.sleep(10)
            }
        }
        if (i == 0) {
            throw throwable!!
        }
    }
}
package com.rafaelmfer.hearthstonecardinfoapp.presentation.carddetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rafaelmfer.commons.customview.dialog.newPanel
import com.rafaelmfer.commons.extensions.gone
import com.rafaelmfer.commons.extensions.onSingleClick
import com.rafaelmfer.commons.extensions.viewBinding
import com.rafaelmfer.commons.extensions.visible
import com.rafaelmfer.hearthstonecardinfoapp.data.repository.State
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardModel
import com.rafaelmfer.hearthstonecardinfoapp.util.INTENT_PARAM_CARD_ID
import com.rafaelmfer.heartstonecardinfoapp.R
import com.rafaelmfer.heartstonecardinfoapp.databinding.ActivityCardDetailsBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CardDetailsActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context, cardId: String) {
            context.startActivity(
                Intent(context, CardDetailsActivity::class.java).putExtra(INTENT_PARAM_CARD_ID, cardId)
            )
        }
    }

    private val cardId by lazy { intent.getStringExtra(INTENT_PARAM_CARD_ID) as String }
    private val viewModel by inject<CardDetailsViewModel> { parametersOf(cardId) }

    private val binding by viewBinding(ActivityCardDetailsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observables()
    }

    private fun observables() {
        viewModel.cardLiveData.observe(this@CardDetailsActivity) {
            loadCardDetails(it)
        }
    }

    private fun loadCardDetails(state: State<CardModel>) {
        when (state) {
            is State.Loading -> {
                binding.pbCardDetails.visible
                binding.groupCardDetails.gone
                binding.tvCardError.gone
            }
            is State.Success -> {
                binding.pbCardDetails.gone
                binding.groupCardDetails.visible
                binding.tvCardError.gone
                val card = state.model
                binding.apply {
                    tvCardName.text = card.name
                    tvCardId.text = card.cardId
                    tvCardSet.text = card.cardSet
                    tvCardFaction.text = card.faction ?: getString(R.string.no_information)
                    tvCardRarity.text = card.rarity ?: getString(R.string.no_information)
                    tvPlayerClass.text = card.playerClass
                    tvType.text = card.type ?: getString(R.string.no_information)
                    tvCardCost.text = card.cost.toString()
                    tvCardAttack.text = card.attack.toString()
                    tvCardHealth.text = card.health.toString()
                    tvCardFlavor.text = card.flavor ?: getString(R.string.no_information)
                    tvText.text = card.text?.let { Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY) } ?: getString(R.string.no_information)

                    Glide.with(this@CardDetailsActivity)
                        .load(card.img)
                        .placeholder(R.drawable.hearthstone_back_card)
                        .into(ivCardImage)

                    ivCardImage.onSingleClick {
                        it.newPanel(layout = R.layout.dialog_card) {
                            findViewById<ImageView>(R.id.iv_card_picture)?.let { imageView ->
                                Glide.with(context)
                                    .load(card.img)
                                    .into(imageView)
                            }
                        }
                    }

                }
            }
            is State.Error -> {
                binding.pbCardDetails.gone
                binding.groupCardDetails.gone
                binding.tvCardError.apply {
                    visible
                    text = state.message
                }
            }
        }
    }
}
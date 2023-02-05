package com.rafaelmfer.hearthstonecardinfoapp.presentation

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaelmfer.commons.extensions.onSingleClick
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardModel
import com.rafaelmfer.hearthstonecardinfoapp.presentation.carddetails.CardDetailsActivity
import com.rafaelmfer.heartstonecardinfoapp.R
import com.rafaelmfer.heartstonecardinfoapp.databinding.ItemCardBinding

class CardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<CardModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardViewHolder.from(parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardViewHolder -> {
                holder.bind(list[position])
            }
        }
    }

    fun addList(list: List<CardModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}

class CardViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): CardViewHolder {
            val view = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CardViewHolder(view)
        }
    }

    fun bind(item: CardModel) {
        binding.apply {
            tvName.text = item.name
            tvCardId.text = item.dbfId.toString()
            tvCardSet.text = item.cardSet
            tvText.text =
                item.text?.let { Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY) } ?: itemView.context.getString(R.string.no_information)
            tvType.text = item.type
            tvPlayerClass.text = item.playerClass

            mcvCard.onSingleClick {
                CardDetailsActivity.startActivity(it.context, item.cardId)
            }
        }
    }
}
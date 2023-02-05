package com.rafaelmfer.hearthstonecardinfoapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaelmfer.heartstonecardinfoapp.databinding.ItemCardClassesBinding

class CardsClassesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardsClassesViewHolder.from(parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardsClassesViewHolder -> {
                holder.bind(list[position])
            }
        }
    }

    class CardsClassesViewHolder(private val binding: ItemCardClassesBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): CardsClassesViewHolder {
                val view = ItemCardClassesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CardsClassesViewHolder(view)
            }
        }

        fun bind(name: String) {
            binding.mbtName.text = name
        }
    }

    fun addList(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }
}
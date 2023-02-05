package com.rafaelmfer.hearthstonecardinfoapp.domain.model

class CardModel(
    val dbfId: Int,
    val img: String?,
    val name: String,
    val flavor: String?,
    val cardSet: String,
    val playerClass: String,
    val type: String?,
    val text: String?,
    val faction: String?,
    val rarity: String?,
    val attack: Int,
    val cost: Int,
    val health: Int,
)
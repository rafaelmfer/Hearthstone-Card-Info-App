package com.rafaelmfer.hearthstonecardinfoapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class CardResponse(
    @SerializedName("cardId") val cardId: String,
    @SerializedName("dbfId") val dbfId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cardSet") val cardSet: String,
    @SerializedName("type") val type: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("playerClass") val playerClass: String,
    @SerializedName("locale") val locale: String,
    @SerializedName("mechanics") val mechanics: List<MechanicResponse>,
    @SerializedName("faction") val faction: String?,
    @SerializedName("rarity") val rarity: String?,
    @SerializedName("cost") val cost: Int,
    @SerializedName("flavor") val flavor: String?,
    @SerializedName("artist") val artist: String?,
    @SerializedName("collectible") val collectible: Boolean?,
    @SerializedName("howToGet") val howToGet: String?,
    @SerializedName("howToGetGold") val howToGetGold: String?,
    @SerializedName("img") val img: String?,
    @SerializedName("imgGold") val imgGold: String?,
    @SerializedName("spellSchool") val spellSchool: String?,
    @SerializedName("attack") val attack: Int,
    @SerializedName("health") val health: Int,
    @SerializedName("race") val race: String,
    @SerializedName("durability") val durability: Int?,
    @SerializedName("otherRaces") val otherRaces: List<String>?,
    @SerializedName("runeCost") val runeCost: RuneCostResponse?,
    @SerializedName("elite") val elite: Boolean,
    @SerializedName("howToGetDiamond") val howToGetDiamond: String?,
    @SerializedName("howToGetSignature") val howToGetSignature: String?,
    @SerializedName("armor") val armor: String?,
    @SerializedName("classes") val classes: List<String>?,
    @SerializedName("multiClassGroup") val multiClassGroup: String?,
)

data class RuneCostResponse(
    @SerializedName("blood") val blood: Int,
    @SerializedName("frost") val frost: Int,
    @SerializedName("unholy") val unholy: Int
)

data class MechanicResponse(
    @SerializedName("name") val name: String
)
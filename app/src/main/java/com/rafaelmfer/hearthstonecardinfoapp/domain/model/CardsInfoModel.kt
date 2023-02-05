package com.rafaelmfer.hearthstonecardinfoapp.domain.model

data class CardsInfoModel(
    val patch: String,
    val classes: List<String>,
    val sets: List<String>,
    val standard: List<String>,
    val wild: List<String>,
    val types: List<String>,
    val factions: List<String>,
    val qualities: List<String>,
    val races: List<String>,
    val locales: LocalesModel
)

data class LocalesModel(
    val dEDE: String,
    val eNGB: String,
    val eNUS: String,
    val eSES: String,
    val eSMX: String,
    val fRFR: String,
    val iTIT: String,
    val kOKR: String,
    val pLPL: String,
    val pTBR: String,
    val rURU: String,
    val zHCN: String,
    val zHTW: String,
    val jAJP: String,
    val tHTH: String
)
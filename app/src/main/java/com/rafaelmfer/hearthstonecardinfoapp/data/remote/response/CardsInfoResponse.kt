package com.rafaelmfer.hearthstonecardinfoapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class CardsInfoResponse(
    @SerializedName("patch") val patch: String,
    @SerializedName("classes") val classes: List<String>,
    @SerializedName("sets") val sets: List<String>,
    @SerializedName("standard") val standard: List<String>,
    @SerializedName("wild") val wild: List<String>,
    @SerializedName("types") val types: List<String>,
    @SerializedName("factions") val factions: List<String>,
    @SerializedName("qualities") val qualities: List<String>,
    @SerializedName("races") val races: List<String>,
    @SerializedName("locales") val locales: LocalesResponse
)

data class LocalesResponse(
    @SerializedName("DE_DE") val dEDE: String,
    @SerializedName("EN_GB") val eNGB: String,
    @SerializedName("EN_US") val eNUS: String,
    @SerializedName("ES_ES") val eSES: String,
    @SerializedName("ES_MX") val eSMX: String,
    @SerializedName("FR_FR") val fRFR: String,
    @SerializedName("IT_IT") val iTIT: String,
    @SerializedName("KO_KR") val kOKR: String,
    @SerializedName("PL_PL") val pLPL: String,
    @SerializedName("PT_BR") val pTBR: String,
    @SerializedName("RU_RU") val rURU: String,
    @SerializedName("ZH_CN") val zHCN: String,
    @SerializedName("ZH_TW") val zHTW: String,
    @SerializedName("JA_JP") val jAJP: String,
    @SerializedName("TH_TH") val tHTH: String
)
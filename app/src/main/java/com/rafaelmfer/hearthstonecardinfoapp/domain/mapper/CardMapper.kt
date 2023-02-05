package com.rafaelmfer.hearthstonecardinfoapp.domain.mapper

import com.rafaelmfer.hearthstonecardinfoapp.data.remote.response.CardResponse
import com.rafaelmfer.hearthstonecardinfoapp.data.remote.response.CardsInfoResponse
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.CardsInfoModel
import com.rafaelmfer.hearthstonecardinfoapp.domain.model.LocalesModel

fun CardResponse.asDomainModel() =
    CardModel(
        dbfId = dbfId,
        img = img,
        name = name,
        flavor = flavor,
        cardSet = cardSet,
        playerClass = playerClass,
        type = type,
        text = text,
        faction = faction,
        rarity = rarity,
        attack = attack,
        cost = cost,
        health = health
    )

fun CardsInfoResponse.asDomainModel() =
    CardsInfoModel(
        patch = patch,
        classes = classes,
        sets = sets,
        standard = standard,
        wild = wild,
        types = types,
        factions = factions,
        qualities = qualities,
        races = races,
        locales = LocalesModel(
            dEDE = locales.dEDE,
            eNGB = locales.eNGB,
            eNUS = locales.eNUS,
            eSES = locales.eSES,
            eSMX = locales.eSMX,
            fRFR = locales.fRFR,
            iTIT = locales.iTIT,
            kOKR = locales.kOKR,
            pLPL = locales.pLPL,
            pTBR = locales.pTBR,
            rURU = locales.rURU,
            zHCN = locales.zHCN,
            zHTW = locales.zHTW,
            jAJP = locales.jAJP,
            tHTH = locales.tHTH
        )
    )
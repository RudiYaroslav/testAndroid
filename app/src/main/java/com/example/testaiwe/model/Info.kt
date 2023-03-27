package com.example.testaiwe.model

import com.example.testaiwe.data.api.ResponseMarketCapPercentage
import com.example.testaiwe.data.db.AllInfoRoom
import com.example.testaiwe.data.db.InfoRoom
import com.google.gson.annotations.SerializedName

data class Info(

    val activeCryptocurrencies: Int,


    val upcomingIcos: Int,


    val ongoingIcos: Int,


    val endedIcos: Int,


    val markets: Int,


    val marketCapsPercentage: List<MarketCapPercentage>
)

fun Info.toRoom(): AllInfoRoom {
    return AllInfoRoom(
        infoRoom = InfoRoom(
            activeCryptocurrencies = activeCryptocurrencies,
            upcomingIcos = upcomingIcos,
            ongoingIcos = ongoingIcos,
            endedIcos =endedIcos,
            markets = markets,
            id = 0
        ),
        marketCapsPercentage.map {
            it.toRoom()
        }
    )
}

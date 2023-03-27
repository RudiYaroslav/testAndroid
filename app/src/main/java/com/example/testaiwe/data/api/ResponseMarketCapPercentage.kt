package com.example.testaiwe.data.api

import android.util.Log
import com.example.testaiwe.model.MarketCapPercentage
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ResponseMarketCapPercentage(
    @SerializedName("btc")
    val btc: Double,
    @SerializedName("eth")
    val eth: Double,
    @SerializedName("usdt")
    val usdt : Double,
    @SerializedName("bnb")
    val bnb : Double
)

fun ResponseMarketCapPercentage.toModel():List<MarketCapPercentage>{

    val btc = MarketCapPercentage("btc", this.btc)
    val eth = MarketCapPercentage("eth", this.eth)
    val usdt = MarketCapPercentage("usdt", this.usdt)
    val bnb = MarketCapPercentage("bnb", this.bnb)

    return listOf(btc,eth,usdt,bnb)
}



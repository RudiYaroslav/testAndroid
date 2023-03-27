package com.example.testaiwe.data.api

import android.util.Log
import com.example.testaiwe.model.Info
import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("data")
    val data: ResponseInfo
)
data class ResponseInfo (
    @SerializedName("active_cryptocurrencies")
    val activeCryptocurrencies:Int,

    @SerializedName("upcoming_icos")
    val upcomingIcos :Int,

    @SerializedName("ongoing_icos")
    val ongoingIcos : Int,

    @SerializedName("ended_icos")
    val endedIcos : Int,

    @SerializedName("markets")
    val markets : Int,



    @SerializedName("market_cap_percentage")
    val responseMarketCapsPercentage : ResponseMarketCapPercentage,


)
fun ResponseInfo.toModel():Info{

    return Info(
        activeCryptocurrencies = this.activeCryptocurrencies,
        upcomingIcos = this.upcomingIcos,
        ongoingIcos = this.ongoingIcos,
        endedIcos = this.endedIcos,
        markets = this.markets,
        marketCapsPercentage = this.responseMarketCapsPercentage.toModel()
    )
}
package com.example.testaiwe.data.db

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.testaiwe.model.Info
import com.example.testaiwe.model.toRoom
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

@Dao
interface InfoDao {
    @Transaction
    @Query("SELECT * FROM infoData")
    fun getAllInfo(): Single<AllInfoRoom>


    @Insert
    fun insertInfo(infoRoom: InfoRoom): Long

    @Insert
    fun insertPercentages(percentagesRoom: List<MarketCapPercentageRoom>)

    @Transaction
    @Insert
    fun insertAll(info: Info): Completable {
        Log.d("DB",info.toString())
        return Completable.create { emmitter ->

            val roomInfo = info.toRoom()
            Log.d("DB",roomInfo.toString())
            val id = insertInfo(roomInfo.infoRoom)
            roomInfo.marketCapsPercentageRoom.forEach {
                it.infoId = id

            }
            insertPercentages(roomInfo.marketCapsPercentageRoom)

        }
    }
}
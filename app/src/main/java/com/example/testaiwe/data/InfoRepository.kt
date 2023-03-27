package com.example.testaiwe.data

import android.util.Log
import com.example.testaiwe.data.api.InfoApi
import com.example.testaiwe.data.api.toModel
import com.example.testaiwe.data.db.InfoDao
import com.example.testaiwe.data.db.toModel
import com.example.testaiwe.model.Info
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InfoRepository @Inject
constructor(
    private val api: InfoApi,
    private val infoDao: InfoDao
) {

    fun parse(): Completable {
        return Completable.fromSingle(
            api.getDataInfo().doOnSuccess {
                Log.d("API",it.toString())
                infoDao.insertAll(it.data.toModel()).subscribe()
            }
        )
    }
    fun getInfo():Single<Info>{
        return infoDao.getAllInfo().map {
            it.toModel()
        }
    }
}
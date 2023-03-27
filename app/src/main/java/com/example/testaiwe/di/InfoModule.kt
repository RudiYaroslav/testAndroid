package com.example.testaiwe.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.testaiwe.data.api.InfoApi
import com.example.testaiwe.data.db.InfoDB
import com.example.testaiwe.data.db.InfoDao
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module

class InfoModule(private val app:Application) {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
    private  companion object{
        val BASE_URL = "https://api.coingecko.com/api/v3/"
    }

    @Singleton
    @Provides
    fun provideInfoApi(retrofit: Retrofit):InfoApi{
        return retrofit.create(InfoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRoom(context : Context):InfoDB{
        return Room.databaseBuilder(
            context,
            InfoDB::class.java,
            "app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideInfoDao(db: InfoDB): InfoDao {
        return db.InfoDao()
    }
    @Singleton
    @Provides
    fun provideContext(): Context = app
}
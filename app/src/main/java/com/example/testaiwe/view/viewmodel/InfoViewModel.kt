package com.example.testaiwe.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testaiwe.data.InfoRepository
import com.example.testaiwe.model.Info
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class InfoViewModel
@AssistedInject
    constructor(
        private  val repository: InfoRepository)
    : RxViewModel() {

    private val infoLiveData: MutableLiveData<Info> = MutableLiveData<Info>()
    public val info:LiveData<Info>
    get() = infoLiveData


    @AssistedFactory
    interface Factory{
        fun create(): InfoViewModel
    }

    fun parseInfo(
        messageCompleteAction:()->Unit,
        messageErrorAction:(error:String)->Unit
    ){
        repository.parse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete ={
                    messageCompleteAction()
                },
                onError = {
                    it.message?.let {
                            it1 -> messageErrorAction(it1)
                    }
                }

            )
            .disposeOnFinish()
    }
    fun getInfo(messageErrorAction:(error:String)->Unit){
        repository.getInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    infoLiveData.value = it
                },
                onError = {
                    it.message?.let {
                        message->messageErrorAction(message)
                    }
                }
            )
            .disposeOnFinish()
    }
}

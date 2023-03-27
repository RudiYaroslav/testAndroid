package com.example.testaiwe.view.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxViewModel: ViewModel() {
    private val disposable = CompositeDisposable()

    fun Disposable.disposeOnFinish() {
        disposable.add(this)
    }



    override fun onCleared() {
        disposable.clear()
    }
}
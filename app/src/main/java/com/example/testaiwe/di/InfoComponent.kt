package com.example.testaiwe.di

import android.app.Application
import com.example.testaiwe.view.viewmodel.InfoViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [InfoModule::class]
)

interface InfoComponent {
    fun infoViewModel(): InfoViewModel.Factory

    companion object{
        private var infoComponent: InfoComponent? = null
        fun build(application: Application):InfoComponent{
            if (infoComponent == null){
                infoComponent=DaggerInfoComponent.builder()
                    .infoModule(InfoModule(application))
                    .build()
            }
            return infoComponent!!
        }
    }

}
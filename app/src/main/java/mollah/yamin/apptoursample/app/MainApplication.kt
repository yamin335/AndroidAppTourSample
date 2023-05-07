package mollah.yamin.apptoursample.app

import android.app.Application
import androidx.databinding.DataBindingUtil
import mollah.yamin.apptoursample.binding.AppDataBindingComponent

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
    }
}
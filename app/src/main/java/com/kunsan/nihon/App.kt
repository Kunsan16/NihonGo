package com.kunsan.nihon

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils


/**
 * Created by moge on 2018/10/27.
 */
class App : Application(){


    override fun onCreate() {
        super.onCreate()

        Utils.init(this)

        LogUtils.getConfig().setSingleTagSwitch(true).setBorderSwitch(false)

        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
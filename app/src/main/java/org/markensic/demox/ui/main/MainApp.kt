package org.markensic.demox.ui.main

import android.app.Application
import org.markensic.baselibrary.global.CrashHandler

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        CrashHandler.init()
    }
}
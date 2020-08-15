package org.markensic.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.markensic.baselibrary.api.utils.LogFileUtil
import org.markensic.baselibrary.api.utils.PermissionsUtil
import org.markensic.baselibrary.global.AppLog
import org.markensic.demo.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
        PermissionsUtil.requestPermission(this, PermissionsUtil.readWritePermission)
    }
}
package org.markensic.demox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.markensic.baselibrary.api.utils.Permissions
import org.markensic.demox.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, MainFragment.newInstance())
        .commitNow()
    }
    Permissions.requestPermission(this, Permissions.readWritePermission)
  }
}

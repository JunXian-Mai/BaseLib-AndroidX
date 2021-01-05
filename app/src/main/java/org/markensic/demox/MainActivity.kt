package org.markensic.demox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    GlobalScope.launch(Dispatchers.Main) {
      runIOTask(1)
      runCPUTask(1)
    }
  }
}


suspend fun runIOTask(index: Int) {
  withContext(Dispatchers.IO) {
    println("run IO Task$index in Current Thread Name: ${Thread.currentThread().name}")
  }
}

fun runCPUTask(index: Int) {
  println("run CPU Task$index in Current Thread Name: ${Thread.currentThread().name}")
}

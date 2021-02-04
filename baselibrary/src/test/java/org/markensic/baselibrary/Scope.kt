package org.markensic.baselibrary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun main() {
  GlobalScope.launch(Dispatchers.Main) {
    val data = getData()
    val processedData = processData(data)
    println("$processedData")
  }
  Thread.sleep(500)
}

// 耗时函数 1
private suspend fun getData(): String {
  return withContext(Dispatchers.IO) {
    // 假设这个函数比较耗时，需要放在后台
    "hen_coder"
  }
}

// 耗时函数 2
private suspend fun processData(data: String): String {
  return withContext(Dispatchers.IO) {
    // 假设这个函数也比较耗时，需要放在后台
    data.split("_") // 把 "hen_coder" 拆成 ["hen", "coder"]
      .map { it.capitalize() } // 把 ["hen", "coder"] 改成 ["Hen", "Coder"]
      .reduce { acc, s -> acc + s } // 把 ["Hen", "Coder"] 改成 "HenCoder"
  }
}

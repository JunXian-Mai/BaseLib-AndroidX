package org.markensic.baselibrary.global

import java.lang.Exception


fun <T> tryCatch(process: () -> T) : String? {
  var errorMsg: String? = null
  try {
    process()
  } catch (e: Exception) {
    errorMsg = """
      msg: ${e.message}
      loc: ${e.localizedMessage}
      cause: ${e.cause}
      excep: $e
    """.trimIndent()
  } finally {
    return errorMsg
  }
}

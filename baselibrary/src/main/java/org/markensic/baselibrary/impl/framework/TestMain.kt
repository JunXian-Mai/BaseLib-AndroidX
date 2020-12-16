package org.markensic.baselibrary.impl.framework

val biBao: () -> (() -> Int) = {
  var count = 0
  { count++ }
}

fun main() {
  val fun1 = biBao()
  val fun2 = biBao()
  println(fun1())
  println(fun1())
  println(fun1())
  println(fun2())
  println(fun2())
  println(fun2())
}

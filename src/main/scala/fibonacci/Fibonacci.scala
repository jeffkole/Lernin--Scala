// LERNIN: package declarations cannot be used when running through scala directly
package com.kolesky.fibonacci

abstract class Fibonacci {
  def fibonacci(n: Int): Int
}

object RecursiveFibonacci extends Fibonacci {
  def fibonacci(n: Int): Int = {
    if (n < 3) {
      1
    }
    else {
      fibonacci(n-1) + fibonacci(n-2)
    }
  }
}

object IterativeFibonacci extends Fibonacci {
  def fibonacci(n: Int): Int = {
    if (n < 3) {
      return 1
    }
    var prevPrev = 1
    var prev = 1
    for (i <- 2 to n) {
      val tmp = prevPrev + prev
      prevPrev = prev
      prev = tmp
    }
    prevPrev
  }
}

// LERNIN: package declarations cannot be used when running through scala directly
// package com.kolesky.fibonacci

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

// 1 1 2 3 5 8 13 21 34 55
val tests = List((1, 1),
                 (2, 1),
                 (3, 2),
                 (4, 3),
                 (5, 5),
                 (6, 8),
                 (7, 13),
                 (8, 21),
                 (9, 34),
                 (10, 55)
               )
val fibers = List(RecursiveFibonacci, IterativeFibonacci)
// LERNIN: is this syntax appropriate for the foreach method (ie, using {}
// instead of another way to make anonymous functions
fibers.foreach { fiber =>
  // LERNIN: current emacs set up fails to indent properly with nested
  // anonymous functions like this
  tests.foreach { test =>
    val n = test._1
    val expected = test._2
    val value = fiber.fibonacci(n)
    // LERNIN: I guess one must find the formats for format in non-Scala docs.
    // no big deal
    println(format("%s: fib(%s) returned %s; expected %s; %s",
                   fiber.getClass.getName, n, value, expected,
                   (if (expected == value) { "OK" } else { "FAIL" })))
  }
}

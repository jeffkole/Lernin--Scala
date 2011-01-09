package com.kolesky.fibonacci

import org.scalatest.FunSuite

class FibonacciTest extends FunSuite {
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

  test("Recursive version") {
    doTest(RecursiveFibonacci)
  }

  test("Iterative version") {
    doTest(IterativeFibonacci)
  }

  private def doTest(fiber: Fibonacci): Unit = {
    tests.foreach { test =>
      val n = test._1
      val expected = test._2
      val value = fiber.fibonacci(n)
      assert(value == expected)
    }
  }
}

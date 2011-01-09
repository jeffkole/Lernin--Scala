package com.kolesky.fibonacci

import org.scalatest.fixture.FixtureFunSuite

class FibonacciFixtureTest extends FixtureFunSuite {
  // define the type of the fixture to be passed in -- a pair of Ints
  type FixtureParam = (Int, Int)

  // withFixture calls the tests with the fixture created
  def withFixture(test: OneArgTest) {
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
    tests foreach { testPair =>
      test(testPair)
    }
  }

  test("Recursive version") { fixture =>
    val (n, expected) = fixture
    val value = RecursiveFibonacci.fibonacci(n)
    assert(value == expected)
  }

  test("Iterative version") { fixture =>
    val (n, expected) = fixture
    val value = RecursiveFibonacci.fibonacci(n)
    assert(value == expected)
  }
}

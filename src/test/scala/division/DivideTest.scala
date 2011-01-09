package com.kolesky.division

import org.scalatest.FunSuite

class DivideTest extends FunSuite {
  val divider = new Divide

  def runTest(numerator: Int, divisor: Int,
              expectedQuotient: Int, expectedRemainder: Int) = {
    val (quotient, remainder) = divider.divide(numerator, divisor)
    expect(expectedQuotient, "quotient wrong")(quotient)
    expect(expectedRemainder, "remainder wrong")(remainder)
  }

  test("Basic") {
    runTest(11, 4, 2, 3)
  }

  test("No remainder") {
    runTest(10, 2, 5, 0)
  }

  test("Divisor larger than numerator") {
    runTest(2, 5, 0, 2)
  }

  test("Numerator == divisor") {
    runTest(17, 17, 1, 0)
  }
}

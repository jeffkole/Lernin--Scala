package com.kolesky.division

class Divide {
  /**
   * Performs division on positive value integers without using / !!
   *
   * @param numerator the numerator in a division equation
   * @param divisor the divisor in a division equation
   * @return a Pair of (quotient, remainder)
   */
  def divide(numerator: Int, divisor: Int): (Int, Int) = {
    var quotient: Int = 0
    while (quotient * divisor <= numerator) {
      quotient += 1
    }
    quotient -= 1
    val remainder = numerator - (divisor * quotient)
    (quotient, remainder)
  }
}

package com.kolesky.implicits

case class Code(val code: String) {
  assert(code.length <= 4, "code '%s' must be fewer than 5 characters".format(code))
  assert(code.length >= 2, "code '%s' must be more than 1 character".format(code))
}

object Code {
  /**
   * Converts a Code to an Int based on the code String.  The first character is converted to a char and bit-shifted left
   * to become the most significant bits of the resulting Int.  The following characters are done the same way until an
   * Int has been constructed.  No biggie.  Oh, except I treat the characters as 8 bits only; perhaps I should validate
   * that in the constructor as well.
   */
  implicit def code2Int(code: Code): Int = {
    code.code.foldLeft[Int](0) { (value: Int, char: Char) => (value << 8 | char) }
  }

  implicit def int2Code(incoming: Int): Code = {
    var code = new StringBuilder(4)
    val mask = 0xFF
    var value = incoming
    while (value > 0) {
      code.append((value & mask).asInstanceOf[Char])
      value = value >> 8
    }
    Code(code.reverse.toString)
  }
}

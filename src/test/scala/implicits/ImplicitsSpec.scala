package com.kolesky.implicits

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ImplicitsSpec extends FlatSpec with ShouldMatchers {
  "A Code" should "not allow a code larger than 4 characters" in {
    val good = new Code("hell")
    intercept[AssertionError] {
      val bad = new Code("hello")
    }
    intercept[AssertionError] {
      val bad = Code("Hello")
    }
  }

  it should "not allow a code smaller than 2" in {
    val good = new Code("hi")
    intercept[AssertionError] {
      val bad = new Code("i")
    }
    intercept[AssertionError] {
      val bad = Code("I")
    }
  }

  it should "be converted to an Int implicitly" in {
    import com.kolesky.implicits.Code._
    // in ascii: 0x31 0x32 0x33 0x34 = 0x31323334 = 825373492
    val code = Code("1234")
    code2Int(code) should equal (0x31323334)
    val value = code + 0 // implicit
    value should equal (0x31323334)
    val value2 = code + 356 // implicit
    value2 should equal (825373848) // 825373492
  }

  it should "be converted from an Int implicitly" in {
    import Code._
    val value = 0x31323334
    int2Code(value) should equal (Code("1234"))
    val code = value.code // implicit
    code should equal ("1234")
  }

  it should "convert back and forth just fine" in {
    import Code._
    val tests = List("AB", "ABC", "ABCD")
    tests foreach { test =>
      val code = Code(test)
      int2Code(code2Int(code)) should equal (code)
    }
  }
}

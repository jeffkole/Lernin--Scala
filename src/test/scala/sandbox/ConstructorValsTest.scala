package com.kolesky.sandbox

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class ConstructorValsTest extends FunSuite with ShouldMatchers {
  test("Immutable read fail") {
    val c = new ImmutableContainer("name")
    // LERNIN: name is private, so this results in a compiler error
//    c.name should equal ("name")
  }
  test("Immutable read") {
    val c = new ImmutableContainer2("name")
    c.name should equal ("name")
  }
  test("Immutable write") {
    val c = new ImmutableContainer2("name")
    c.name should equal ("name")
    // LERNIN: name is only readable, so this results in a compiler error
//    c.name = "last name"
  }
  test("Mutable write") {
    val c = new MutableContainer("name")
    c.name should equal ("name")
    c.name = "last name"
    c.name should equal ("last name")
  }
  test("Premutate read") {
    val c = new PremutateContainer("name")
    // LERNIN: nameEntry is private, so this results in a compiler error
//    c.nameEntry should equal ("name")
    c.name should equal ("n")
    // LERNIN: nameEntry is private, so this results in a compiler error
//    c.nameEntry = "another"
    // LERNIN: name is a val, which cannot be reassigned, so another compiler error
//    c.name = "a"
  }
  test("Premutate 2") {
    val c = new PremutateContainer2("name")
    // LERNIN: nameEntry is private, so this results in a compiler error
//    c.nameEntry should equal ("name")
    c.name should equal ("n")
    // LERNIN: nameEntry is private, so this results in a compiler error
//    c.nameEntry = "another"
    // LERNIN: name is a val, which cannot be reassigned, so another compiler error
//    c.name = "a"
    c.originalName should equal ("name")
  }
}

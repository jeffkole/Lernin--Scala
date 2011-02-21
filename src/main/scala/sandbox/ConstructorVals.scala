package com.kolesky.sandbox

// LERNIN: this 'name' is private
class ImmutableContainer(name: String) {
}

// LERNIN: this 'name' is only readable
class ImmutableContainer2(val name: String) {
}

class MutableContainer(var name: String) {
}

class PremutateContainer(private val nameEntry: String) {
  val name = nameEntry(0).toString
}

class PremutateContainer2(nameEntry: String) {
  val name = nameEntry(0).toString
  def originalName = nameEntry
  // LERNIN: nameEntry is a private val, so it is immutable
//  def changeName(newName: String) = nameEntry = newName
}

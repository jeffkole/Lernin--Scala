package com.kolesky.sockets

import java.io._
import java.net.Socket

// LERNIN: I would have liked msgDelimiter to be of type T, but it results in a compilation error when declared that way
abstract class AbstractSocket(hostname: String, port: Int, msgDelimiter: Array[Byte]) {
  type T

  val socket = new Socket(hostname, port)
  val output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream))
  val input = new DataInputStream(new BufferedInputStream(socket.getInputStream))

  def send(data: T): Unit = {
    output.write(toBytes(data))
    output.write(msgDelimiter)
    output.flush
  }

  def receive: T = {
    val contents = new ByteArrayOutputStream
    val buffer = new Array[Byte](8 * 1024)
    var read = -1
    do {
      read = input.read(buffer)
      contents.write(buffer, 0, read)
      // TODO: check for msgDelimiter at end of message
    } while (read > 0 && read >= buffer.size)
    contents.close
    fromBytes(contents.toByteArray)
  }

  def close: Unit = {
    output.close
    input.close
    socket.close
  }

  protected[this] def fromBytes(data: Array[Byte]): T
  protected[this] def toBytes(data: T): Array[Byte]
}

class SimpleSocket(hostname: String, port: Int, msgDelimiter: Array[Byte]) extends AbstractSocket(hostname, port, msgDelimiter) {
  type T = Array[Byte]

  override def fromBytes(data: Array[Byte]): Array[Byte] = data
  override def toBytes(data: Array[Byte]): Array[Byte] = data
}

class StringSocket(hostname: String, port: Int, msgDelimiter: Array[Byte]) extends AbstractSocket(hostname, port, msgDelimiter) {
  type T = String

  override def fromBytes(data: Array[Byte]): String = new String(data, "UTF-8")
  override def toBytes(data: String): Array[Byte] = data.getBytes("UTF-8")
}

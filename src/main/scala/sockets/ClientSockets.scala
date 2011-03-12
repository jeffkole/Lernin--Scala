package com.kolesky.sockets

import java.net.Socket

// LERNIN: The type used for these methods is defined by the AbstractSocket.  Wow.
abstract class AbstractClientSocket(hostname: String, port: Int) {
  val socket = makeSocket(new Socket(hostname, port))

  def send(data: socket.T): Unit = {
    socket.send(data)
  }

  def receive: socket.T = {
    socket.receive
  }

  def close: Unit = {
    socket.close
  }

  def makeSocket(socket: Socket): AbstractSocket
}

class SimpleClientSocket(hostname: String, port: Int, msgDelimiter: Byte) extends AbstractClientSocket(hostname, port) {
  override def makeSocket(socket: Socket): AbstractSocket = new SimpleSocket(socket, msgDelimiter)
}

class StringClientSocket(hostname: String, port: Int, msgDelimiter: Byte) extends AbstractClientSocket(hostname, port) {
  override def makeSocket(socket: Socket): AbstractSocket = new StringSocket(socket, msgDelimiter)
}

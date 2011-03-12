package com.kolesky.sockets

import java.net.ServerSocket
import java.net.Socket

class EchoServer(port: Int) {
  def run: Unit = {
    val server = new ServerSocket(port)
    while (true) {
      var run = true
      val socket = new SimpleSocket(server.accept(), 0x0)
      while (run) {
        val msg = socket.receive
        printf("[S] received '%s'%n", msg)
        socket.send(msg)
        if (msg.length <= 0) { run = false }
      }
      socket.close
    }
  }
}

object EchoServer {
  def main(args: Array[String]) {
    (new EchoServer(11111)).run
  }
}

class EchoClient(hostname: String, port: Int) {
  def run: Unit = {
    val socket = new StringSocket(new Socket(hostname, port), 0x0)
    while (true) {
      printf("> ")
      val echo = readLine
      printf("[C] read from line '%s'%n", echo)

      socket.send(echo)
      printf("[C] ECHO: %s%n", socket.receive)
    }
    socket.close
  }

  // Really just meant to run some tests
  def runOnce(msg: String): String = {
    val socket = new StringSocket(new Socket(hostname, port), 0x0)
    socket.send(msg)
    val response = socket.receive
    socket.close
    response.asInstanceOf[String]
  }
}

object EchoClient {
  def main(args: Array[String]) {
    (new EchoClient("localhost", 11111)).run
  }
}

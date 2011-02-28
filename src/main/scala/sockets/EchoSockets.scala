package com.kolesky.sockets

import java.io._
import java.net._

class EchoServer(port: Int) {
  def run: Unit = {
    val server = new ServerSocket(port)
    while (true) {
      val socket = server.accept()
      val input = new DataInputStream(new BufferedInputStream(socket.getInputStream))
      val output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream))

      val contents = new ByteArrayOutputStream

      val buffer = new Array[Byte](8 * 1024)
      var read = -1
      do {
        read = input.read(buffer)
        printf("read %d bytes: '%s'%n", read, new String(buffer))
        contents.write(buffer, 0, read)
        printf("writing '%s'%n", new String(buffer))
        output.write(buffer, 0, read)
        output.flush
        if (read < buffer.size) { read = -1 }
      } while (read > 0)

      contents.close
      printf("read in total '%s'%n", contents.toString)

      output.close
      input.close
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
    while (true) {
      printf("> ")
      val echo = readLine
      printf("read from line '%s'%n", echo)

      val socket = new StringSocket(hostname, port, Array[Byte]('\n'.asInstanceOf[Byte]))
      socket.send(echo)
      printf("ECHO: %s%n", socket.receive)
      socket.close
    }
  }
}

object EchoClient {
  def main(args: Array[String]) {
    (new EchoClient("localhost", 11111)).run
  }
}

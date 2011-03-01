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

      var run = true
      while (run) {
        val contents = new ByteArrayOutputStream
        val buffer = new Array[Byte](8 * 1024)
        var read = -1
        do {
          read = input.read(buffer)
          if (read > 0) {
            printf("read %d bytes: '%s'%n", read, new String(buffer))
            contents.write(buffer, 0, read)
            printf("writing '%s'%n", new String(buffer))
            output.write(buffer, 0, read)
            output.flush
          }
        } while (read > 0 && read >= buffer.size)
        contents.close
        printf("read in total '%s'%n", contents.toString)
        if (read <= 0) { run = false }
      }

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
    val socket = new StringSocket(hostname, port, '\n'.asInstanceOf[Byte])
    while (true) {
      printf("> ")
      val echo = readLine
      printf("read from line '%s'%n", echo)

      socket.send(echo)
      printf("ECHO: %s%n", socket.receive)
    }
    socket.close
  }
}

object EchoClient {
  def main(args: Array[String]) {
    (new EchoClient("localhost", 11111)).run
  }
}

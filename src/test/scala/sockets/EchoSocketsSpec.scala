package com.kolesky.sockets

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class EchoSocketsSpec extends FlatSpec with ShouldMatchers {
  private val port = 11111

  private class Server extends Runnable {
    val echo = new EchoServer(port)

    def run = {
      echo.run
    }
  }

  private val server = new Thread(new Server)
  server.start

  "A client" should "receive a message from the client after sending one" in {
    val client = new EchoClient("localhost", port)
    val response = client.runOnce("Hello")
    response should equal ("Hello")
  }

  it should "be able to do that a few times" in {
    val client = new EchoClient("localhost", port)
    client.runOnce("Who's there?") should equal ("Who's there?")
    client.runOnce("No one") should equal ("No one")
    client.runOnce("Then why did you knock?") should equal ("Then why did you knock?")
  }
}

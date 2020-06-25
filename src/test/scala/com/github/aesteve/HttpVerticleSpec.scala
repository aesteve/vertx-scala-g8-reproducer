package com.github.aesteve

import org.scalatest.Matchers
import io.vertx.scala.ext.web.client._

class HttpVerticleSpec extends VerticleTesting[HttpVerticle] with Matchers {

  "HttpVerticle" should "bind to 8666 and answer with 'world'" in {
    WebClient.create(vertx, WebClientOptions().setDefaultHost("127.0.0.1").setDefaultPort(8666))
      .get("/hello")
      .sendFuture()
      .map(res => res.bodyAsString should equal("world"))
  }

}

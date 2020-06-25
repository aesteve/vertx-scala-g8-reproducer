package com.github.aesteve

import io.vertx.scala.ext.web.client._
import org.scalatest.matchers.should.Matchers

class HttpVerticleSpec extends VerticleTesting[HttpVerticle] with Matchers {

  "HttpVerticle" should "bind to 8666 and answer with 'world'" in {
    /*
    // the expected working version would be:
    vertx.createHttpClient()
      .getNowFuture(8666, "127.0.0.1", "/hello")
      .flatMap(_.bodyFuture)
      .map(_.toString("UTF-8") should equal("world"))
    // but unfortunately it fails with IllegalStateException (problem with futurised versions of the HttpClient's API)
     */
    WebClient.create(vertx, WebClientOptions().setDefaultHost("127.0.0.1").setDefaultPort(8666))
      .get("/hello")
      .sendFuture()
      .map(res => res.bodyAsString should equal("world"))
  }

}

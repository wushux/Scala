package CSYE7200

/**
  * Created by Shuxian on 9/11/16.
  */
import org.scalatest.{FlatSpec, Matchers}


class HelloWorldSpec extends FlatSpec with Matchers {

  behavior of "HelloWorld"

  it should "get the correct greeting" in {
    val greeting = HelloWorld.greeting
    greeting shouldBe "Hello World!"
  }
}

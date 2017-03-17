package uk.gov.hmrc.checkout

import org.scalatest.{Matchers, WordSpec}

class ShoppingCartSpec extends WordSpec with Matchers {

  private val shoppingCart = new ShoppingCart

  "total cost" should {

    "return the total cost of the products" in {
      val result = shoppingCart.totalCost(Seq("Apple", "Apple", "Orange", "Apple"))

      result shouldBe 2.05
    }

    "ignore unknown products in the calculation" in {
      val result = shoppingCart.totalCost(Seq("Apple", "Apple", "Orange", "Apple", "Banana", "Kiwifruit"))

      result shouldBe 2.05
    }
  }
}
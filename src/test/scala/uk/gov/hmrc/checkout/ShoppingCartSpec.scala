package uk.gov.hmrc.checkout

import org.scalatest.{Matchers, WordSpec}

class ShoppingCartSpec extends WordSpec with Matchers {

  private val shoppingCart = new ShoppingCart

  "total cost" should {

    "return the total cost of the products" in {
      val result = shoppingCart.totalCost(Seq("Apple", "Apple", "Orange", "Apple"))

      result shouldBe 1.45
    }

    "ignore unknown products in the calculation" in {
      val result = shoppingCart.totalCost(Seq("Apple", "Apple", "Orange", "Apple", "Banana", "Kiwifruit"))

      result shouldBe 1.45
    }

    "return 0 if the input is empty" in {
      val result = shoppingCart.totalCost(Seq())

      result shouldBe 0
    }

    "apply the buy one get one free on Apples offer on the product list" in {
      val result = shoppingCart.totalCost(Seq("Apple", "Apple", "Orange"))

      result shouldBe 0.85
    }

    "apply the 3 for the price of two on Oranges offer on the product list" in {
      val result = shoppingCart.totalCost(Seq("Orange", "Orange", "Orange", "Apple"))

      result shouldBe 1.10
    }

    "combine multiple product offers on the product list" in {
      val result = shoppingCart.totalCost(Seq("Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple"))

      result shouldBe (0.75 + 1.20)
    }
  }
}
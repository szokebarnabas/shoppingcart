package uk.gov.hmrc.checkout

import scala.math.BigDecimal.RoundingMode

class ShoppingCart {

  private val productCatalog = Map(
    "Apple" -> BigDecimal(0.6),
    "Orange" -> BigDecimal(0.25)
  )

  private val offers = Seq(
    Offer("Apple", 2, BigDecimal(0.5)),
    Offer("Orange", 3, BigDecimal(2.0 / 3.0))
  )

  private def costOfProdGroup(group: (String, Int)): BigDecimal = {

    def sum(group: (String, Int), cost: BigDecimal): BigDecimal = {
      val productName = group._1
      val count = group._2

      if (count == 0) return cost

      val foundOffer = offers.find(offer => offer.productName == productName && offer.quantity <= count)
      val (processedElements: Int, costOfItems: BigDecimal) = processElement(productName, foundOffer)
      sum((productName, count - processedElements), cost + costOfItems)
    }

    sum(group, 0.0)
  }

  private def processElement(productName: String, foundOffer: Option[Offer]): (Int, BigDecimal) = {
    foundOffer.map(offer => {
      val price = offer.quantity * productCatalog.getOrElse(productName, BigDecimal(0.0)) * offer.discount
      (offer.quantity, price)
    }).getOrElse {
      productCatalog.get(productName).map((1, _)).getOrElse((1, BigDecimal(0.0)))
    }
  }

  def totalCost(items: Seq[String]): BigDecimal = {
    val sum = items
      .groupBy(identity)
      .map { case (a, b) => costOfProdGroup(a, b.size) }
      .sum

    sum.setScale(2, RoundingMode.CEILING)
  }

  case class Offer(val productName: String, val quantity: Int, val discount: BigDecimal)

}

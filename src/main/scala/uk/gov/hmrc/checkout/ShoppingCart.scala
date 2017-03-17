package uk.gov.hmrc.checkout

class ShoppingCart {

  private val productCatalog = Map(
    "Apple" -> BigDecimal(0.6),
    "Orange" -> BigDecimal(0.25)
  )

  def costOfProdGroup(group: (String, Int)): BigDecimal = {

    def sum(group: (String, Int), cost: BigDecimal): BigDecimal = {
      val productName = group._1
      val count = group._2
      if (count == 0) return cost
      sum((productName, count - 1), cost + productCatalog.getOrElse(productName, 0))
    }

    sum(group, 0.0)
  }

  def totalCost(items: Seq[String]): BigDecimal = {
    items
      .groupBy(identity)
      .map { case (a, b) => costOfProdGroup(a, b.size) }
      .sum
  }
}

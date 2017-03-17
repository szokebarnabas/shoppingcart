package uk.gov.hmrc.checkout

class ShoppingCart {

  private val productCatalog = Map(
    "Apple" -> BigDecimal(0.6),
    "Orange" -> BigDecimal(0.25)
  )

  def totalCost(items: Seq[String]): BigDecimal = {
    items.foldLeft(BigDecimal(0.0))((sum, prod) => sum + productCatalog.getOrElse(prod, 0))
  }
}

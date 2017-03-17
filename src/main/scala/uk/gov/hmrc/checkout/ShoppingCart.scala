package uk.gov.hmrc.checkout

class ShoppingCart {

  def totalCost(items: Seq[String]): BigDecimal = {
    items.foldLeft(BigDecimal(0.0)) {
      case (sum, "Apple") => sum + BigDecimal(0.6)
      case (sum, "Orange") => sum + BigDecimal(0.25)
      case (sum, _) => sum
    }
  }
}

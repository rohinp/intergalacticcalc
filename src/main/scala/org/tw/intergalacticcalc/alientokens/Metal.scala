package org.tw.intergalacticcalc.alientokens

trait Metal extends Token
case class Silver(value:Float) extends Metal {
  override def toString: String = "Silver"
}
case class Iron(value:Float) extends Metal{
  override def toString: String = "Iron"
}
case class Gold(value:Float) extends Metal{
  override def toString: String = "Gold"
}

object Metal {
  def apply(m: String, value: Float):Metal = m match {
    case "Silver" => Silver(value)
    case "Iron" => Iron(value)
    case "Gold" => Gold(value)
  }

  val list = List("Silver","Iron","Gold")


}
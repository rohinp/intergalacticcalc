package org.tw.intergalacticcalc.alientokens

trait AlienLang extends Token {
  val sym: Symbol
}
case class Glob(sym: Symbol) extends AlienLang {
  override def toString: String = "glob"
}
case class Prok(sym: Symbol) extends AlienLang {
  override def toString: String = "prok"
}
case class Pish(sym: Symbol) extends AlienLang {
  override def toString: String = "pish"
}
case class Tegj(sym: Symbol) extends AlienLang {
  override def toString: String = "tegj"
}

object AlienLang {


  def list = List("glob","prok","pish","tegj")

  def apply(at: String, symbol: Symbol):AlienLang = at match {
    case "glob" => Glob(symbol)
    case "prok" => Prok(symbol)
    case "pish" => Pish(symbol)
    case "tegj" => Tegj(symbol)
  }
}

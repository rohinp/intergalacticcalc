package org.tw.intergalacticcalc.expression

import org.tw.intergalacticcalc.alientokens.Symbol


trait InvalidExpression{
  val forSymbol:Symbol
}
case class InvalidOccurrence(forSymbol:Symbol) extends InvalidExpression
case class InvalidSuccession(forSymbol:Symbol) extends InvalidExpression

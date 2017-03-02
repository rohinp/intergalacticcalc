package org.tw.intergalacticcalc.expression

import org.tw.intergalacticcalc.Expression

case class MetalExtractor(expression:Expression,totalVal:Float) {
  def metalCost:Either[InvalidExpression,Float] = ExpressionEvaluator(expression).eval match {
    case e@Left(_) => e
    case Right(v) => Right(totalVal/v)
  }
}

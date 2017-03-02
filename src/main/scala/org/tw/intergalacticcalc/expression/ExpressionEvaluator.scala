package org.tw.intergalacticcalc.expression

import org.tw.intergalacticcalc._
import org.tw.intergalacticcalc.alientokens._

trait ExpressionEvaluator {
  import Expr._

  val expression:Expression
  def eval:Either[InvalidExpression,Float]

  protected def evalExpr:Either[InvalidExpression,Float] = Expr(expression) match {
    case Left(e) => Left(e)
    case Right(v) => v.pairIt match {
      case EvenPairedExpr(expr) => Right(expr.foldLeft(0)((a,b) => a + operate(b)))
      case OddPairedExpr(h,expr) => Right(h + expr.foldLeft(0)((a,b) => a + operate(b)))
    }
  }

  private[this] def operate(ps:(Symbol,Symbol)):Int =
    if(ps._1 >= ps._2) ps._1 + ps._2
    else ps._2 - ps._1
}

case class NoMetalEpr(expression:Expression) extends ExpressionEvaluator {
  override def eval: Either[InvalidExpression,Float] = evalExpr
}

case class MetalExpr(expression: Expression,metal:Metal) extends ExpressionEvaluator {

  val metalValue: Float = metal match {
    case Gold(v) => v
    case Silver(v) => v
    case Iron(v) => v
  }

  override def eval: Either[InvalidExpression,Float] = evalExpr match {
    case e@Left(_) => e
    case Right(value) => Right(value * metalValue)
  }
}

object ExpressionEvaluator {
  def apply(expression: Expression): ExpressionEvaluator = NoMetalEpr(expression)
  def apply(expression: Expression, metal: Metal): ExpressionEvaluator = MetalExpr(expression, metal)
}
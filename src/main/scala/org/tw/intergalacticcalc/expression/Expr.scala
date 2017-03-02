package org.tw.intergalacticcalc.expression

import org.tw.intergalacticcalc._
import org.tw.intergalacticcalc.alientokens.Symbol._
import org.tw.intergalacticcalc.alientokens.{AlienLang, _}
import org.tw.intergalacticcalc.parser.TokensRepo

object Expr {

  private[this] val validations:List[ValidationStrategy] =
    List (
      (expr) => occurrenceCheck(expr,I,4),
      (expr) => occurrenceCheck(expr,X,4),
      (expr) => occurrenceCheck(expr,C,4),
      (expr) => occurrenceCheck(expr,M,4),
      (expr) => occurrenceCheck(expr,D,2),
      (expr) => occurrenceCheck(expr,L,2),
      (expr) => occurrenceCheck(expr,V,2),
      (expr) => successionRule(expr,(I,List(I,V,X))),
      (expr) => successionRule(expr,(X,List(X,L,C))),
      (expr) => successionRule(expr,(C,List(D,M))),
      (expr) => successionRule(expr,(V,List(I))),
      (expr) => successionRule(expr,(L,List(X,V,I))),
      (expr) => successionRule(expr,(D,List(C,X,V,I)))
    )

  private[this] val applyValidation:ValidationStrategy =
    (e:Expression) => validations.find(v => v(e) match {
      case Left(_) => true
      case _ => false
    }).getOrElse((x:Expression) => Right(x))(e)

  //utility validation functions
  private[this] def occurrenceCheck(expression: Expression, symbol: Symbol, times:Int):Either[InvalidExpression,Expression] = {

    val regX:Boolean = expression.mkString.matches(".*" + symbol +"{" + times + ",}.*")

    if(regX) Left(InvalidOccurrence(symbol))
    else Right(expression)
  }

  private[this] def successionRule(expression: Expression, rule:(Symbol,List[Symbol])):Either[InvalidExpression,Expression] = {

    def isValid(expr: List[Symbol]):Boolean = expr match {
      case x::y::xs => if((x == rule._1) && !rule._2.contains(y)) false else isValid(y::xs)
      case _ => true
    }

    if(expression.contains(rule._1) && !isValid(expression)) Left(InvalidSuccession(rule._1))
    else Right(expression)
  }

  implicit class ExpressionExtension(expression: Expression) {
    def pairIt:PairedExpr = {
      def loop(ex:Expression,acc:List[(Symbol,Symbol)]):List[(Symbol,Symbol)] = ex match {
        case List() => acc
        case x::xs => loop(xs.tail,acc ++ List((xs.head,x)))
      }
      if(expression.length % 2 == 0) EvenPairedExpr(loop(expression.reverse,List()))
      else OddPairedExpr(expression.head,loop(expression.tail.reverse,List()))
    }
  }

  def toExpression(tr:TokensRepo,l:List[String]):Expression =
    l.map(e => (tr.alienLangTokens(e) match {
      case al:AlienLang => al
    }).sym)

  def apply(expr: List[Symbol]): Either[InvalidExpression,Expression] = applyValidation(expr)
}

package org.tw.intergalacticcalc.parser

import org.tw.intergalacticcalc.alientokens._
import org.tw.intergalacticcalc.expression.{Expr, ExpressionEvaluator, MetalExtractor}

import scala.util.parsing.combinator._

object Parsers extends RegexParsers with JavaTokenParsers{

  val alienLangToSymbolParser:Parser[Token] =
    s"""${AlienLang.list.mkString("|")}""".r ~
      "is".r ~
      s"""${Symbol.list.map(_.toString).mkString("|")}""".r ^^ {
      e => AlienLang(e._1._1,e._2)
    }

  val extractMetalCostParser:(TokensRepo => Parser[Metal]) = (tr:TokensRepo) =>
    rep(s"""${AlienLang.list.mkString("|")}""".r) ~
      s"""${Metal.list.mkString("|")}""".r ~
      "is".r ~ floatingPointNumber ~
      "Credits".r ^^ {
        e => Metal(e._1._1._1._2,MetalExtractor(Expr.toExpression(tr,e._1._1._1._1),e._1._2.toFloat).metalCost.right.get)
      }

  val exprParser:(TokensRepo => Parser[String]) = (tr:TokensRepo) =>
    "how".r ~ "much".r ~ "is".r ~
      rep(s"""${AlienLang.list.mkString("|")}""".r) ~
      """\?""".r ^^ {
      e => e._1._2.mkString(" ") + " is " + ExpressionEvaluator(Expr.toExpression(tr,e._1._2)).eval.right.get
    }

  val exprWithMetalParser:(TokensRepo) => Parser[String] = (tr:TokensRepo) =>
    """how""".r ~ """many""".r ~ """Credits""".r ~ "is".r ~
      rep(s"""${AlienLang.list.mkString("|")}""".r) ~
      s"""${Metal.list.mkString("|")}""".r ~
      """\?""".r ^^ {
      e => s"${e._1._1._2.mkString(" ")} ${e._1._2} is ${ExpressionEvaluator(Expr.toExpression(tr,e._1._1._2),tr.metals(e._1._2)).eval.right.get} Credits"
    }

  val questionParsers: (TokensRepo => Parser[String]) = (tr:TokensRepo) => exprParser(tr) | exprWithMetalParser(tr)

}

package org.tw.intergalacticcalc

import org.tw.intergalacticcalc.alientokens.{Metal, Token}
import org.tw.intergalacticcalc.parser.{Parsers, TokensRepo}

case class IntergalacticCalc(input:List[String]) {
  import Parsers._

  def parseToExtractTokens: (TokensRepo, List[String]) = {

    lazy val ats: (Map[String, Token], List[String]) = input.foldLeftTuple((Map.empty[String,Token],List[String]()))((acc, b) => {
      val at: ParseResult[Token] = parseAll(alienLangToSymbolParser, b)
      if(at.successful)  (acc._1 + (at.get.toString -> at.get),acc._2) else (acc._1,acc._2 ++ List(b))
    })

    val tr = TokensRepo(ats._1,Map())

    lazy val ms: (Map[String, Metal], List[String]) = ats._2.foldLeftTuple((Map.empty[String,Metal],List[String]()))((acc, b) => {
      val m: ParseResult[Metal] = parseAll(extractMetalCostParser(tr), b)
      if(m.successful) (acc._1 + (m.get.toString -> m.get),acc._2) else (acc._1,acc._2 ++ List(b))
    })

    (TokensRepo(tr.alienLangTokens,ms._1),ms._2)
  }


  def parse: List[String] = {
    val (tokenRepo,remainingList) = parseToExtractTokens
    remainingList.map(question => {
      parseAll(questionParsers(tokenRepo),question).getOrElse("I have no idea what you are talking about")
    })
  }

}

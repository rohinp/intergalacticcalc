package org.tw.intergalacticcalc.parsers

import org.junit.Test
import org.scalatest.Matchers._
import org.tw.intergalacticcalc.alientokens.Symbol._
import org.tw.intergalacticcalc.alientokens._
import org.tw.intergalacticcalc.parser.Parsers._
import org.tw.intergalacticcalc.parser.TokensRepo

class ParsersTests {

  @Test
  def shouldParseStringContaining_AlienLangAndSymbolMapping(): Unit = {
    //given
    val input1 = "glob is I"
    val input2 = "prok is V"
    val input3 = "pish is X"
    val input4 = "tegj is L"
    //when

    //then
    parseAll(alienLangToSymbolParser,input1).get shouldEqual Glob(I)
    parseAll(alienLangToSymbolParser,input2).get shouldEqual Prok(V)
    parseAll(alienLangToSymbolParser,input3).get shouldEqual Pish(X)
    parseAll(alienLangToSymbolParser,input4).get shouldEqual Tegj(L)
  }

  @Test
  def shouldParseStringContaining_MetalCostMapping(): Unit = {
    //given
    val input1 = "glob glob Silver is 34 Credits"
    val input2 = "glob prok Gold is 57800 Credits"
    val input3 = "pish pish Iron is 3910 Credits"

    val tr = TokensRepo(Map(("glob",Glob(I)),("tegj",Tegj(L)),("prok",Prok(V)),("pish",Pish(X))),Map())
    //when

    //then
    parseAll(extractMetalCostParser(tr),input1).get shouldEqual Silver(17)
    parseAll(extractMetalCostParser(tr),input2).get shouldEqual Gold(14450)
    parseAll(extractMetalCostParser(tr),input3).get shouldEqual Iron(195.5F)
  }


  @Test
  def shouldParseStringResolvingItToAnExpression(): Unit = {
    //given
    val input = "how much is pish tegj glob glob ?"

    val tr = TokensRepo(
      Map(("glob",Glob(I)),("tegj",Tegj(L)),("prok",Prok(V)),("pish",Pish(X))),
      Map(("Silver",Silver(17)),("Gold",Gold(14450)),("Iron",Iron(195.5F))))
    //when

    //then
    parseAll(exprParser(tr),input).get shouldBe "pish tegj glob glob is 42.0"
  }

  @Test
  def shouldParseStringResolvingItToAnExpression_WithMetal(): Unit = {
    //given
    val input1 = "how many Credits is glob prok Silver ?"
    val input2 = "how many Credits is glob prok Gold ?"
    val input3 = "how many Credits is glob prok Iron ?"

    val tr = TokensRepo(
      Map(("glob",Glob(I)),("tegj",Tegj(L)),("prok",Prok(V)),("pish",Pish(X))),
      Map(("Silver",Silver(17)),("Gold",Gold(14450)),("Iron",Iron(195.5F))))
    //when

    //then
    parseAll(exprWithMetalParser(tr),input1).get shouldEqual "glob prok Silver is 68.0 Credits"
    parseAll(exprWithMetalParser(tr),input2).get shouldEqual "glob prok Gold is 57800.0 Credits"
    parseAll(exprWithMetalParser(tr),input3).get shouldEqual "glob prok Iron is 782.0 Credits"
  }

}

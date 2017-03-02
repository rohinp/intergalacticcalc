package org.tw.intergalacticcalc.parsers

import org.junit.Test
import org.tw.intergalacticcalc.parser.TokensRepo
import org.scalatest.Matchers._
import org.tw.intergalacticcalc.IntergalacticCalc
import org.tw.intergalacticcalc.alientokens._
import org.tw.intergalacticcalc.alientokens.Symbol._

class TokenRepoTests {

  @Test
  def shouldFillRepoWithAlienLangTokensAfterParsing(): Unit = {
    //given
    val input = List(
      "glob is I",
      "prok is V",
      "pish is X",
      "tegj is L"
    )
    //when
    val (tr:TokensRepo,_:List[String]) = IntergalacticCalc(input).parseToExtractTokens
    //then
    tr.alienLangTokens should contain theSameElementsAs Map("glob" -> Glob(I), "tegj" -> Tegj(L), "prok" -> Prok(V), "pish" -> Pish(X))
  }

  @Test
  def shouldFillRepoWithAlienMetalTokensAfterParsing(): Unit = {
    //given
    val input = List(
      "glob is I",
      "prok is V",
      "pish is X",
      "tegj is L",
      "glob glob Silver is 34 Credits",
      "glob prok Gold is 57800 Credits",
      "pish pish Iron is 3910 Credits"
    )
    //when
    val (tr:TokensRepo, _:List[String]) = IntergalacticCalc(input).parseToExtractTokens
    //then
    tr.metals should contain theSameElementsAs Map(("Silver",Silver(17)),("Gold",Gold(14450)),("Iron",Iron(195.5F)))
  }

  @Test
  def shouldFillRepoWithAlienLangAndMetalTokensAfterParsing(): Unit = {
    //given
    val input = List(
      "glob is I",
      "prok is V",
      "pish is X",
      "tegj is L",
      "glob glob Silver is 34 Credits",
      "glob prok Gold is 57800 Credits",
      "pish pish Iron is 3910 Credits"
    )
    //when
    val (tr:TokensRepo, _:List[String]) = IntergalacticCalc(input).parseToExtractTokens
    //then
    tr.metals should contain theSameElementsAs Map(("Silver",Silver(17)),("Gold",Gold(14450)),("Iron",Iron(195.5F)))
    tr.alienLangTokens should contain theSameElementsAs Map("glob" -> Glob(I), "tegj" -> Tegj(L), "prok" -> Prok(V), "pish" -> Pish(X))
  }

  @Test
  def shouldFillRepoAndSeparateUnParsedLinesAfterParsing(): Unit = {
    //given
    val input = List(
      "glob is I",
      "prok is V",
      "pish is X",
      "tegj is L",
      "glob glob Silver is 34 Credits",
      "glob prok Gold is 57800 Credits",
      "pish pish Iron is 3910 Credits",
      "This doesn't belong to token repo"
    )
    //when
    val (tr:TokensRepo, remaining:List[String]) = IntergalacticCalc(input).parseToExtractTokens
    //then
    tr.metals should contain theSameElementsAs Map(("Silver",Silver(17)),("Gold",Gold(14450)),("Iron",Iron(195.5F)))
    tr.alienLangTokens should contain theSameElementsAs Map("glob" -> Glob(I), "tegj" -> Tegj(L), "prok" -> Prok(V), "pish" -> Pish(X))
    remaining should contain theSameElementsAs List("This doesn't belong to token repo")
  }


}

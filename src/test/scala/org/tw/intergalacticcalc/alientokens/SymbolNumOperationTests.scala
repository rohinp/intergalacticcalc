package org.tw.intergalacticcalc.alientokens

import org.junit.Test
import org.scalatest.Matchers._
import org.tw.intergalacticcalc.alientokens.Symbol._

class SymbolNumOperationTests {

  @Test
  def shouldAddTwoSymbolsToInts(): Unit = {
    //given
    //when
    //then
    I + V shouldBe 6
  }

  @Test
  def shouldSubTwoSymbolsToInts(): Unit = {
    //given
    //when
    //then
    V - I shouldBe 4
  }

}

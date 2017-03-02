package org.tw.intergalacticcalc.alientokens

import org.junit.Test
import org.scalatest.Matchers._
import org.tw.intergalacticcalc.alientokens.Symbol._
import org.tw.intergalacticcalc._

class SymbolConversionTests {

  @Test
  def shouldGiveNumForAllGivenSymbols(): Unit = {
    //given
    //when
    //then
    I + 0 shouldBe 1
    V + 0 shouldBe 5
    X + 0 shouldBe 10
    L + 0 shouldBe 50
    C + 0 shouldBe 100
    D + 0 shouldBe 500
    M + 0 shouldBe 1000
  }
}

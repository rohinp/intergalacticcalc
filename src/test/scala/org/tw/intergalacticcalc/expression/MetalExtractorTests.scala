package org.tw.intergalacticcalc.expression

import org.junit.Test
import org.scalatest.Matchers._
import org.tw.intergalacticcalc.alientokens.Symbol.I

class MetalExtractorTests {
  @Test
  def itShouldEvaluateCostOfMetalWithExpression(): Unit ={
    //given
    val ee = MetalExtractor(List(I,I),totalVal = 34)
    //when
    //then
    ee.metalCost shouldBe Right(17)
  }

}

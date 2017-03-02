package org.tw.intergalacticcalc.expression

import org.junit.Test
import org.scalatest.Matchers._
import org.tw.intergalacticcalc.alientokens._
import org.tw.intergalacticcalc.alientokens.Symbol._
import org.tw.intergalacticcalc.expression.Expr._

class ExpressionEvaluationTests {

  @Test
  def itShouldPairExpression_OddExpr(): Unit = {
    //given
    val expr = List(M,C,M,X,L,I,V)
    //when
    val paired = expr.pairIt
    //then
    paired shouldBe OddPairedExpr(M,List((I,V), (X,L), (C,M)))
  }

  @Test
  def itShouldPairExpression_EvenExpr(): Unit = {
    //given
    val expr = List(C,M,X,L,I,V)
    //when
    val paired = expr.pairIt
    //then
    paired shouldBe EvenPairedExpr(List((I,V), (X,L), (C,M)))
  }

  @Test
  def itShouldEvaluateAnExpression(): Unit ={
    //given
    val ee = ExpressionEvaluator(List(M,C,M,X,L,I,V))
    //when

    //then
    ee.eval shouldBe Right(1944)
  }

  @Test
  def itShouldEvaluateExpressionWithMetal(): Unit ={
    //given
    val ee = ExpressionEvaluator(List(I ,I),Silver(17))
    //when
    //then
    ee.eval shouldBe Right(34)
  }
}

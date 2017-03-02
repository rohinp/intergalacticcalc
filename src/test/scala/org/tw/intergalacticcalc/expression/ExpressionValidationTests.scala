package org.tw.intergalacticcalc.expression

import org.junit.Test
import org.scalatest.Matchers._
import org.tw.intergalacticcalc.alientokens.Symbol._

class ExpressionValidationTests {

  @Test
  def itShouldNotAllowMoreThen3SuccessionOf_I(): Unit ={
    //given
    //when
    //then
    Expr(List(I,I,I,I,X)) shouldBe Left(InvalidOccurrence(I))
  }

  @Test
  def itShouldNotAllowMoreThen3SuccessionOf_X(): Unit ={
    //given
    //when
    //then
    Expr(List(X,X,X,X)) shouldBe Left(InvalidOccurrence(X))
  }

    @Test
    def itShouldNotAllowMoreThen3SuccessionOf_C(): Unit ={
      //given
      //when
      //then
      Expr(List(C,C,C,C)) shouldBe Left(InvalidOccurrence(C))
    }

    @Test
    def itShouldNotAllowMoreThen3SuccessionOf_M(): Unit ={
      //given
      //when
      //then
      Expr(List(M,M,M,M)) shouldBe Left(InvalidOccurrence(M))
    }

    @Test
    def itShouldNotAllowMoreThen1SuccessionOf_D(): Unit ={
      //given
      //when
      //then
      Expr(List(D,D)) shouldBe Left(InvalidOccurrence(D))
    }

    @Test
    def itShouldNotAllowMoreThen1SuccessionOf_L(): Unit ={
      //given
      //when
      //then
      Expr(List(L,L)) shouldBe Left(InvalidOccurrence(L))
    }

    @Test
    def itShouldNotAllowMoreThen1SuccessionOf_V(): Unit ={
      //given
      //when
      //then
      Expr(List(V,V)) shouldBe Left(InvalidOccurrence(V))
    }

    @Test
    def itShouldAllowSubtractionOf_I_WithXAndVOnly_valid(): Unit ={
      //given
      //when
      //then
      Expr(List(I,V)) shouldBe Right(List(I,V))
      Expr(List(I,X)) shouldBe Right(List(I,X))
    }

    @Test
    def itShouldAllowSubtractionOf_I_WithXAndVOnly_invalid(): Unit ={
      //given
      //when
      //then
      Expr(List(I,M)) shouldBe Left(InvalidSuccession(I))
      Expr(List(I,C)) shouldBe Left(InvalidSuccession(I))
    }

    @Test
    def itShouldAllowSubtractionOf_X_WithLAndCOnly_valid(): Unit ={
      //given
      //when
      //then
      Expr(List(X,L)) shouldBe Right(List(X,L))
      Expr(List(X,C)) shouldBe Right(List(X,C))
    }

    @Test
    def itShouldAllowSubtractionOf_X_WithLAndCOnly_invalid(): Unit ={
      //given
      //when
      //then
      Expr(List(X,M)) shouldBe Left(InvalidSuccession(X))
      Expr(List(X,V)) shouldBe Left(InvalidSuccession(X))
    }

    @Test
    def itShouldAllowSubtractionOf_C_WithDAndMOnly_valid(): Unit ={
      //given
      //when
      //then
      Expr(List(C,D)) shouldBe Right(List(C,D))
      Expr(List(C,M)) shouldBe Right(List(C,M))
    }

    @Test
    def itShouldAllowSubtractionOf_C_WithDAndMOnly_invalid(): Unit ={
      //given
      //when
      //then
      //test for not expected = classOf[InvalidExpression]
      Expr(List(C,X)) shouldBe Left(InvalidSuccession(C))
      Expr(List(C,V)) shouldBe Left(InvalidSuccession(C))
    }

    @Test
    def itShouldNotAllowSubtractionOf_V(): Unit ={
      //given
      //when
      //then
      Expr(List(V,X)) shouldBe Left(InvalidSuccession(V))
    }

    @Test
    def itShouldNotAllowSubtractionOf_L(): Unit ={
      //given
      //when
      //then
      Expr(List(L,C)) shouldBe Left(InvalidSuccession(L))
    }

    @Test
    def itShouldNotAllowSubtractionOf_D(): Unit ={
      //given
      //when
      //then
      Expr(List(D,M)) shouldBe Left(InvalidSuccession(D))
    }
}

package org.tw.intergalacticcalc.alientokens

import org.junit.Test
import org.scalatest.Matchers._
import org.tw.intergalacticcalc.alientokens.Symbol._

class SymbolOrderTests {

  @Test
  def shouldCompareSymbols_forEquality(): Unit = {
    //given

    //when

    //then
    I == I shouldBe true
    V == V shouldBe true
    X == X shouldBe true
    L == L shouldBe true
    C == C shouldBe true
    D == D shouldBe true
    M == M shouldBe true
  }

  @Test
  def shouldCompareSymbols_LessThen(): Unit = {
    //given

    //when

    //then
    I < V shouldBe true
    V < X shouldBe true
    X < L shouldBe true
    L < C shouldBe true
    C < D shouldBe true
    D < M shouldBe true
  }

  @Test
  def shouldCompareSymbols_GreaterThen(): Unit = {
    //given

    //when

    //then
    V > I shouldBe true
    X > V shouldBe true
    L > X shouldBe true
    C > L shouldBe true
    D > C shouldBe true
  }

  @Test
  def shouldCompareSymbols_LessThenEqualTo(): Unit = {
    //given

    //when

    //then
    I <= V shouldBe true
    V <= X shouldBe true
    X <= L shouldBe true
    L <= C shouldBe true
    C <= D shouldBe true
    D <= M shouldBe true

    I <= I shouldBe true
    V <= V shouldBe true
    X <= X shouldBe true
    L <= L shouldBe true
    C <= C shouldBe true
    D <= D shouldBe true
    M <= M shouldBe true

  }

  @Test
  def shouldCompareSymbols_GreaterThenEqualTo(): Unit = {
    //given

    //when

    //then
    I >= V shouldBe false
    V >= X shouldBe false
    X >= L shouldBe false
    L >= C shouldBe false
    C >= D shouldBe false
    D >= M shouldBe false

    I >= I shouldBe true
    V >= V shouldBe true
    X >= X shouldBe true
    L >= L shouldBe true
    C >= C shouldBe true
    D >= D shouldBe true
    M >= M shouldBe true
  }


}

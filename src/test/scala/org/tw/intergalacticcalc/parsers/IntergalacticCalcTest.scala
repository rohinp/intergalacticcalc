package org.tw.intergalacticcalc.parsers

import org.junit.Test
import org.scalatest.Matchers._
import org.tw.intergalacticcalc.IntergalacticCalc

class IntergalacticCalcTest {
  @Test
  def shouldParseInputCreateRepoAndAnswerQuestions(): Unit = {
    //given
    val input = List(
      "glob is I",
      "prok is V",
      "pish is X",
      "tegj is L",
      "glob glob Silver is 34 Credits",
      "glob prok Gold is 57800 Credits",
      "pish pish Iron is 3910 Credits",
      "how much is pish tegj glob glob ?",
      "how many Credits is glob prok Silver ?",
      "how many Credits is glob prok Gold ?",
      "how many Credits is glob prok Iron ?",
      "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?")
    //when
    val answers = IntergalacticCalc(input).parse

    //then
    answers should contain theSameElementsAs List(
      "pish tegj glob glob is 42.0",
      "glob prok Silver is 68.0 Credits",
      "glob prok Gold is 57800.0 Credits",
      "glob prok Iron is 782.0 Credits",
      "I have no idea what you are talking about"
    )
  }
}

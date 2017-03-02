package org.tw.intergalacticcalc

import org.junit.Test
import org.scalatest.Matchers._

class InterGalacticCalcIntegrationTest {

  @Test
  def shouldReadFileInputParseAndGiveResult(): Unit = {
    //given
    val filePath = "input.txt"

    //when
    val answers = IntergalacticCalc(FileIO.readFile(filePath)).parse

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

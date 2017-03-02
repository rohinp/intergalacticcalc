package org.tw.intergalacticcalc

import scala.io.Source._

object FileIO {
  def readFile(filePath:String):List[String] =
    fromResource(filePath).getLines().toList
}

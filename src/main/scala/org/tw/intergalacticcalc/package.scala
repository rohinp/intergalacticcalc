package org.tw

import org.tw.intergalacticcalc.alientokens.Symbol
import org.tw.intergalacticcalc.expression.InvalidExpression

package object intergalacticcalc {

  //type declarations
  type Expression = List[Symbol]
  type ValidationStrategy = Expression => Either[InvalidExpression,Expression]
  type Parser[A] = List[Char] => Either[String,(A,List[Char])]

  //all implicit conversions here
  implicit def symbolToInt(symbol: alientokens.Symbol):Int = {

    def intForSym(numbers:List[Int], acc:List[Int]): List[Int] = (numbers,acc) match {
      case (List(),_) => acc
      case (_::xs,List()) => intForSym(xs,List(1))
      case (x::xs,ys) => if (x%2 == 0) intForSym(xs,ys ++ List(ys.last * 5) )else intForSym(xs, ys ++ List(ys.last * 2))
    }

    lazy val symNum: List[Int] = intForSym((1 to alientokens.Symbol.list.length).toList,List())

    alientokens.Symbol.list.zip(symNum).toMap[alientokens.Symbol,Int].get(symbol).get
  }

  import org.tw.intergalacticcalc.alientokens._
  implicit def stringToSymbol(sym:String):Symbol = Symbol.list.find(_.toString.equals(sym)).get

  implicit class ListExtension[A](list: List[A]) {
    def foldLeftTuple[B,C](acc:(B,C))(f:((B ,C),A) => (B,C)):(B,C) = {
      def loop(l:List[A],ac:(B,C)):(B,C) = l match {
        case List() => ac
        case x::xs => loop(xs,f(ac,x))
      }
      loop(list,acc)
    }
  }
}

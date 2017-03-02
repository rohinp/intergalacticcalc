package org.tw.intergalacticcalc.alientokens

trait Symbol extends Token

object Symbol {
  case object I extends Symbol
  case object V extends Symbol
  case object X extends Symbol
  case object L extends Symbol
  case object C extends Symbol
  case object D extends Symbol
  case object M extends Symbol

  def list:List[Symbol] = List(I,V,X,L,C,D,M)

  implicit class SymbolOrder(t1: Symbol) extends Order[Symbol] {

    trait Compare
    case object LT extends Compare
    case object GT extends Compare
    case object EQ extends Compare

    def ord(t2: Symbol):Compare = {
      def loop(orderedList:List[Symbol]):Compare = orderedList match {
        case List() => EQ
        case x::xs if !List(t1,t2).contains(x) => loop(xs)
        case x::_ if x.equals(t1) => LT
        case x::_ if x.equals(t2) => GT
      }
      loop(Symbol.list)
    }


    override def <(t2:Symbol):Boolean = ord(t2) match {
      case LT => true
      case _ => false
    }

    override def >(t2:Symbol):Boolean = ord(t2) match {
      case GT => true
      case _ => false
    }

    override def <=(t2:Symbol):Boolean = t1 == t2 || (ord(t2) match {
      case LT => true
      case _ => false
    })

    override def >=(t2:Symbol):Boolean = t1 == t2 || (ord(t2) match {
      case GT => true
      case _ => false
    })

  }

  implicit class SymbolNum(sym: Symbol) extends Num[Symbol] {
    override def +(t: Symbol): Int = 0 + sym + t
    override def -(t: Symbol): Int = 0 + sym - t
  }

}



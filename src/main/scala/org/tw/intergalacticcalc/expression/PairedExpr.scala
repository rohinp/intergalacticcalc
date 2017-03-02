package org.tw.intergalacticcalc.expression

import org.tw.intergalacticcalc.alientokens.Symbol

trait PairedExpr
case class EvenPairedExpr(expr:List[(Symbol,Symbol)]) extends PairedExpr
case class OddPairedExpr(head:Symbol, tail:List[(Symbol,Symbol)]) extends PairedExpr

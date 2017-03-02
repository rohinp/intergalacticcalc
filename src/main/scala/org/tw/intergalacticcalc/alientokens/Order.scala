package org.tw.intergalacticcalc.alientokens

trait Order[T] {
  def <(t2:T):Boolean
  def >(t2:T):Boolean
  def <=(t2:T):Boolean
  def >=(t2:T):Boolean
}

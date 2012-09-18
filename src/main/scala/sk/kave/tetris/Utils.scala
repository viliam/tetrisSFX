package sk.kave.tetris

import scalafx.beans.binding.{NumberExpression, NumberBinding}
import scalafx.beans.property.IntegerProperty

object Utils {

  implicit def numberBiding2int( n : NumberBinding) : Int = n.value.intValue()
  implicit def numberExpression2int( n : NumberExpression) : Int = n.getValue.intValue()

  implicit def bindAndGet( bindingX : NumberExpression) : NumberExpression= {
    val ip = new IntegerProperty()
    ip <== bindingX
    ip
  }
}

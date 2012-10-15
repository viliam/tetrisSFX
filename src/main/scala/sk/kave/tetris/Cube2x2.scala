package sk.kave.tetris

import scalafx.beans.property.{DoubleProperty, IntegerProperty}
import scalafx.Includes._
import scalafx.beans.binding.{NumberExpression, NumberBinding}
import sk.kave.tetris.Property._

class Cube2x2 (x : DoubleProperty)(implicit override val board : Board) extends Cube(x)  {

  override def position = //List( (0,0),( 1, 0 ), (0, 1), (1, 1) )
              List( (0,0), (0,1), (0,2), (1,2))

}

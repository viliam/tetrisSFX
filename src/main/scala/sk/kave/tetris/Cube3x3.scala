package sk.kave.tetris

import scalafx.beans.property.DoubleProperty
import collection.parallel.immutable

object Cube3X3 {
  def posL = List( (0,0), (0,1), (0,2), (1,2))
  def posLrev = List()
  def posZ = List()
  def posS = List()
  def posT = List()
}

abstract class Cube3x3 (x : DoubleProperty)(implicit override val board : Board) extends Cube(x)  {

  var actualPosition : List[ (Int, Int)]

  override def position = actualPosition

  var rotation = 0

  //def rotationRight =
}

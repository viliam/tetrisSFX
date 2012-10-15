package sk.kave.tetris

import scalafx.scene.{Node, Group}
import scalafx.beans.property.{DoubleProperty, IntegerProperty}
import scalafx.Includes._
import scalafx.beans.binding.{NumberExpression, NumberBinding}
import sk.kave.tetris.Utils._
import scalafx.animation.Timeline
import javafx.event.EventHandler
import javafx.event

object Cube {

  final private[tetris] def isFree( positions : List[ (Int,Int)])(implicit board: Board) : Boolean = {
    for ( (x,y) <- positions
        if board.isOut( x,y) || !board.isFreeItem( x,y) ) {
      println ( "isn't free: " +x+ "," + y)
      return false
    }
    true
  }

  def apply(x : Int) (implicit board:Board) =  new Cube( DoubleProperty(x))

}
class Cube (
   var x : DoubleProperty,
   var y : DoubleProperty = DoubleProperty(0) )
  (implicit val board : Board)  {

  var isMoving = false

  def position : List[ (Int, Int)] = List( (0,0) )

  def moveLeft()  : Boolean = moveX( -1)
  def moveRight() : Boolean = moveX( 1)

  private def moveX( dx : Int) : Boolean =
    if ( !isMoving && isXfree(dx) ) {
      isMoving = true
      new Timeline() {
        onFinished = new EventHandler[event.ActionEvent] {
          def handle(e : event.ActionEvent) {
            isMoving = false
          }
        }
        keyFrames = Seq(
          at(0 ms)         { Set( x ->  x() )},
          at(50 ms)        { Set( x -> ( x() + dx) ) }
        )
      }.play
      true
    } else
      false


  def isXfree(dx: Int) : Boolean = {
    Cube.isFree(for ((xx, yy) <- position) yield (x().toInt + xx + dx, round(y()) + yy))
  }

  def isDownFree : Boolean =
      Cube.isFree( for ( (xx,yy) <- position) yield ( x().toInt + xx, y().toInt + yy +1) )


  def round( x : Double) : Int =
     if ( x.toInt == x) x.toInt
     else x.toInt + 1

}

package sk.kave.tetris

import scalafx.scene.{Node, Group}
import scalafx.beans.property.IntegerProperty
import javafx.beans.property

object Cube {

  final private[tetris] def isFree( positions : List[ (Int,Int)])(implicit board: Board) : Boolean = {
      for ( (x,y) <- positions
            if !board.isFree( x,y) || board.isOut( x,y))
        return false
      true
  }

  def apply(x : Int) (implicit board:Board) =
    new Cube( IntegerProperty(x))

}
class Cube (
   val x : IntegerProperty,
   val y :IntegerProperty = IntegerProperty(0) )
  (implicit val board : Board)  {


  def position : List[ (Int, Int)] = List((0,0))


  def moveLeft() =
    if ( Cube.isFree(  for ( (x,y) <- position) yield (x-1,y))   )
      x - 1

  def moveRight() =
    if ( Cube.isFree( for ( (x,y) <- position) yield (x+1,y)) )
      x + 1

  //TODO: def rotate()

  def freeze() =
    for ( (x,y) <- position) board.freeze(x,y)


  def isDownFree : Boolean =
    Cube.isFree( for ( (x,y) <- position) yield (x,y+1))


  //TODO: def cleanFullRows()

  final def moveDown : Boolean =
      if ( isDownFree ) {
        y+1
        true
      } else {
        freeze()
        //TOOD cleanFullRows()
        false
      }

//  class Cube2x2 extends Cube {
//
//    //def this( x : Int, board :Board ) =this(x, 0) ( board)
//
//    def position = List( (x,y), (x+1,y), (x, y+1), (x+1, y+1) )
//
//  }

}

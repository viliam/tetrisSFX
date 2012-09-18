package sk.kave.tetris

import scalafx.scene.{Node, Group}
import scalafx.beans.property.IntegerProperty
import scalafx.Includes._
import scalafx.beans.binding.{NumberExpression, NumberBinding}
import sk.kave.tetris.Utils._

object Cube {

  final private[tetris] def isFree( positions : List[ (Int,Int)])(implicit board: Board) : Boolean = {
      for ( (x,y) <- positions
            if !board.isFreeItem( x,y) || board.isOut( x,y))
        return false
      true
  }

  def apply(x : Int) (implicit board:Board) =
    new Cube( IntegerProperty(x))

}
class Cube (
   var x : Int,
   var y : Int= 0 )
  (implicit val board : Board)  {


  def position : List[ (Int, Int)] =
    List( (0,0) )

  def moveLeft() = {;}
        //ak je volne pre kazdy poziciu x-1 (TODO: !pozor na prekryvanie sameho so sebou)
//    if ( Cube.isFreeItem(  for ( (xx,yy) <- position) yield (xx-1, yy ))   )
//      //posun x-vou suradnicu dolava
//      x.set( x() - 1)

  def moveRight() = {;}
//    if ( Cube.isFreeItem( for ( (x,y) <- position) yield ( x + 1, y() )) )
//      x + 1

  //TODO: def rotate()

  def freeze() =
    for ( (x,y) <- position) board.freeze(x,y)


  def isDownFree : Boolean =
    Cube.isFree( for ( (xx,yy) <- position) yield ( x + xx, y + yy +1) )

  //TODO: def cleanFullRows()

  final def moveDown : Boolean =
    if ( isDownFree ) {
      y +=1
      true
    } else {
      freeze()
      //TOOD cleanFullRows()
      false
    }

}

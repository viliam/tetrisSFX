package sk.kave.tetris

import sk.kave.tetris.Property._
import scalafx.beans.property.BooleanProperty
import scalafx.beans.property.BooleanProperty._

object Board {

  def makeFrozenItems : Array[Array[BooleanProperty]]  = {
    val frozenItems = new Array[Array[BooleanProperty]] (Cols)
    for (i <- (0 until Cols) ) {
      frozenItems(i) = new Array[BooleanProperty](Rows)
      for ( j <- (0 until Rows))
        frozenItems(i)(j) = false
    }
    frozenItems
  }


}

class Board( val frozenItems : Array[Array[BooleanProperty]] = Board.makeFrozenItems ) {

  def clear() {
    for (i <- 0 until Rows; if ( isFullRow(i)) ) {
        clearRow(i)
    }
  }

  /**
   * Recognize if given rull is full
   */
  def isFullRow(row: Int): Boolean =  {
    for ( cols <-  frozenItems; if cols(row)() == false )
      return false

    true
  }

  /**
   * Given row will be erased. Every rows up to given row, will be
   * turning down, to fill full empty place, which create erasing row
   */
  def clearRow(row : Int) {
    assert( isFullRow( row))

    for (
      iCol  <- 0 until frozenItems.length;
      iRow  <- List.range( row, 1, -1)) {
        frozenItems(iCol)(iRow).value = frozenItems(iCol)(iRow-1)()
    }

    for (col <- frozenItems) {
      col( 0).value = false
    }
  }

  def isFreeItem(item : (Int, Int) )  = !frozenItems(item._1)( item._2)()
  def isFree(position : List [ (Int, Int) ]) =
    position.forall( !isOut (_) ) && position.forall( isFreeItem (_) )

  def freeze(x: Int, y: Int)  { frozenItems(x)(y).value = true }
  def freeze( cube : Cube ) {
     for ( (xx,yy) <- cube.position) freeze( cube.x().toInt + xx,  cube.y().toInt + yy)
  }

  def isOut(item : ( Int, Int) ) : Boolean  = {
    val x = item._1
    val y = item._2
    x <0 || x == frozenItems.length || y >= frozenItems( x).length
  }
//  def isOut( cube : Cube) : Boolean = {
//    !cube.position.forall( !isOut (_) )
//  }

}

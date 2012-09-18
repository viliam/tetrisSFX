package sk.kave.tetris

import sk.kave.tetris.Property._

object Board {

  def makeFrozenItems : Array[Array[Boolean]]  = {
    val frozenItems = new Array[Array[Boolean]] (Cols)
    for (i <- (0 until Cols) ) {
      frozenItems(i) = new Array[Boolean](Rows)
    }
    frozenItems
  }


}

class Board( val frozenItems : Array[Array[Boolean]] = Board.makeFrozenItems ) {

  /**
   * Recognize if given rull is full
   */
  def isFullRow(row: Int): Boolean =  {
    for ( cols <-  frozenItems; if cols(row) == false )
      return false
    true
  }

  /**
   * potrebujem vsetky riadky vyssie ako row-index posunut
   * o riadok nizsie
   *
   * Given row will be erased. Every rows up to given row, will be
   * turning down, to fill full empty place, which create erasing row
   */
  def clearRow(row : Int) {
    assert( isFullRow( row))

    for (
      col  <- frozenItems;
      iRow <- row until col.length -1) {
      col(iRow) = col(iRow+1 )
    }

    for (col <- frozenItems) {
      col( col.length -1) = false
    }
  }

  def isFreeItem(item : (Int, Int) )  = !frozenItems(item._1)( item._2)
  def isFree(position : List [ (Int, Int) ]) =
    position.forall( !isOut (_) ) && position.forall( isFreeItem (_) )

  def freeze(x: Int, y: Int)  { frozenItems(x)(y) = true }
  def freeze( cube : Cube ) {
     for ( (xx,yy) <- cube.position) freeze( cube.x + xx,  cube.y + yy)
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

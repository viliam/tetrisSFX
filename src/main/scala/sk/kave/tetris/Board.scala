package sk.kave.tetris

import sk.kave.tetris.Property._

object Board {

  def makeFrozenItems : Array[Array[Boolean]]  = {
    val frozenItems = new Array[Array[Boolean]] (Rows)
    for (i <- (0 until Rows) ) {
      frozenItems(i) = new Array[Boolean](Cols)
    }
    frozenItems
  }


}

class Board( val frozenItems : Array[Array[Boolean]] = Board.makeFrozenItems ) {

  /**
   * Recognize if given rull is full
   */
  def isFullRow(row: Int): Boolean =  {
    for ( row <-  frozenItems; item <- row; if item == false )
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

    val maxRow = frozenItems.length -1
    for ( iRow <- row+1 to maxRow)
      frozenItems (iRow-1) = frozenItems(iRow )

    frozenItems(maxRow) = Array.fill( frozenItems(maxRow).length)( false)
  }

  def isFree(x : Int, y : Int) = frozenItems(x)(y)

  def freeze(x: Int, y: Int)  { frozenItems(x)(y) = true }

  def isOut(x : Int, y : Int)  = { x<0 || x>= frozenItems.length || y >= frozenItems(x).length }

}

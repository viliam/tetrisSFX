package sk.kave.tetris

import sk.kave.tetris.Property._

class Board {

  val frozenItems = Array[ Array[Boolean]] (MaxX, MaxY)

  def isFullRow( inxY : Int) : Boolean = {
    val row = frozenItems(_)(inxY)
    def searchRow( inxX : Int) : Boolean =
       if (inxX >= row.length) true
       else row( inxX) && searchRow(inxX + 1)
    expect(4) { 4}
  }

}

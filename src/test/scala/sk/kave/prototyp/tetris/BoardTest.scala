package sk.kave.tetris

import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import sk.kave.tetris.Property._
import scalafx.beans.property.BooleanProperty


@RunWith(classOf[JUnitRunner])
class BoardTest extends FlatSpec with ShouldMatchers {

//  val testFrozenItem = Array( Array(false, false, true, false),
//                              Array(false, true,  true, true),
//                              Array(false, true,  true, false))
//
//  def deepCopy(a : Array[Array[BooleanProperty]]) : Array[Array[BooleanProperty]] = {
//      for (row <- a) yield for ( item <- row) yield item()
//  }
//
//  "A Board" should " initialized corretly" in {
//    val board : Board = new Board( deepCopy (testFrozenItem) )
//
//    assert( board.frozenItems.length == 3)
//    for (col <- board.frozenItems)
//      assert( col.length == 4)
//  }
//
//  it should " recognize if row is full" in {
//    val board : Board = new Board( deepCopy (testFrozenItem) )
//
//    assert( !board.isFullRow( 0) )
//    assert( board.isFullRow( 2))
//  }
//
//  it should " be able clear full row " in {
//    val board : Board = new Board( deepCopy (testFrozenItem) )
//
//    board.clearRow(2)
//
//    for (col <- board.frozenItems)  //clear last row
//      assert( col(3) == false)
//
//    for (i <- 1 until board.frozenItems.length)
//      assert( testFrozenItem(i)(3) == board.frozenItems(i)(2))
//  }
//
//  it should " recognize if item is free" in {
//    val board : Board = new Board( deepCopy (testFrozenItem) )
//
//    assert( board.isFreeItem( (0,0)) == true)
//    assert( board.isFreeItem( (0,2)) == false)
//  }
//
//  it should " throw an AssertionError if trying clear not full row" in {
//    val board : Board = new Board( deepCopy (testFrozenItem) )
//
//    evaluating {
//      board.clearRow( 0)
//    } should produce [AssertionError]
//  }


}
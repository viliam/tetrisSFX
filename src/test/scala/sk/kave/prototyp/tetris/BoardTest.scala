package sk.kave.tetris

import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import sk.kave.tetris.Property._


@RunWith(classOf[JUnitRunner])
class BoardTest extends FlatSpec with ShouldMatchers {

  val testFrozenItem = Array( Array(false, false, false),
                              Array(false, false, true),
                              Array(true,  true,  true),
                              Array(false, true,  true))

  val board : Board = new Board( deepCopy (testFrozenItem) )

  def deepCopy(a : Array[Array[Boolean]]) : Array[Array[Boolean]] = {
//    val b = new Array[Array[Boolean]] (a.length)
//    b =
      for (row <- a) yield for ( item <- row) yield item
        // b(i) = new Array[Boolean](a(i).length)
//         j <- 0 until a(i).length )  {
//       b(i)(j) = a(i)(j)
//    }
//    b
  }

  "A Board" should " recognize if row is full" in {
    assert( !board.isFullRow( 0) )
    assert( board.isFullRow( 2))
  }

  it should " be able clear full row " in {
    board.clearRow(2)
    assert( testFrozenItem(3) == board.frozenItems(2))
    assert( board.frozenItems(3).forall( _ == false))
  }

  it should " throw an AssertionError if trying clear not full row" in {

    evaluating {
      board.clearRow( 0)
    } should produce [AssertionError]
  }
}


//class FadeTransitionSpec extends FlatSpec with PropertyComparator {
// "A FadeTransition" should "implement all the JavaFX properties" in {
//   compareProperties(classOf[jfxa.FadeTransition], classOf[FadeTransition])
// }
//
// it should "implement all the JavaFX builder properties" in {
//   compareBuilderProperties(classOf[jfxa.FadeTransitionBuilder], classOf[FadeTransition])
// }
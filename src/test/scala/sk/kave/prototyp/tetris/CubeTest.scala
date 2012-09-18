package sk.kave.prototyp.tetris

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import sk.kave.tetris.{Cube, Board}

@RunWith(classOf[JUnitRunner])
class CubeTest extends FlatSpec with ShouldMatchers {


  class TestCube(x : Int)(implicit override val board : Board) extends Cube(x) {
    override def position : List[ (Int, Int)] = List( (0,0), (0,1) )
  }

  val testFrozenItem = Array( Array(false, false, false, false),
                              Array(false, true,  false, false),
                              Array(false, true,  true,  false))


  implicit val board = new Board( testFrozenItem )

  "A Cube " should " initialized correctly" in {
    val cube = new Cube(10,5)
    assert( cube.x == 10)
    assert( cube.y == 5)
    assert( cube.position.size == 1)
    assert( cube.position.head == (0,0))
  }

  it should " check if is free place down" in {
     val testCube = new TestCube( 0)
     assert( testCube.isDownFree)
     testCube.x = 1
     assert( !testCube.isDownFree)
  }

  it should " move down" in {
    val cube = new TestCube(0)
    assert( cube.moveDown)
    assert( cube.y == 6)
  }
}

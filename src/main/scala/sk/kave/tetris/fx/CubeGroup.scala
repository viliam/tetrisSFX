package sk.kave.tetris.fx

import scalafx.scene.Group
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import sk.kave.tetris.{Cube2x2, Board, Cube, Property}
import scalafx.animation.Timeline
import scalafx.Includes._
import javafx.event
import event.EventHandler
import Property._
import scalafx.beans.property.{DoubleProperty, IntegerProperty}
import sk.kave.MainTetris

/**
 * Responsibility:
 * -- hold the real position of cube on the boardStage (by binding)
 * -- create fx-rectangle, with are reprezenting cube on boardStage
 * -- handle time-line, for falling cube down
 * -- delegate moving event to cube
 *
**/

class CubeGroup(implicit val board : Board) extends Group {

  val startPosition = Cols /2

  val cube : Cube = new Cube2x2( DoubleProperty( startPosition) )

  val realX = IntegerProperty( 0)
  val realY = IntegerProperty( 0)
  realX <== cube.x * ItemSize
  realY <== cube.y * ItemSize

  var isMovingDown = false

  children = makeCubeBinding

  def makeCubeBinding =
    for ((xx,yy) <- cube.position)
      yield new Rectangle {
              width = ItemSize
              height =ItemSize

              x <== realX + (xx * Property.ItemSize)
              y <== realY + (yy * Property.ItemSize)

              fill = Color.BLUE
            }

  def position : List[ ( Int, Int)]
      = for ( (xx,yy) <- cube.position) yield ( cube.x().toInt + xx, cube.y().toInt + yy)

  def start() {
    new Timeline() {
      onFinished = new EventHandler[event.ActionEvent] {
        def handle(e : event.ActionEvent) {
          makeMove()
        }
      }

      var speedDelay = if (isMovingDown) 10 ms else 200 ms

      keyFrames = Seq(
        at(0 s)        { Set(cube.y -> cube.y() )},
        at(speedDelay) { Set(cube.y -> (cube.y() + 1) ) }
      )
    }.play
  }

  def makeMove() {
    if ( !board.isFree( for ( (xx,yy) <- position ) yield (xx, yy+1) ) )
      newCube()

    start()
  }

  def newCube() {
    board.freeze(cube )
    board.clear()                    //check if is full row

    children.removeAll()             //we need remove old bind cube position

    cube.y.value = 0
    children = makeCubeBinding
  }

  def moveDown() {
    isMovingDown = true
  }

  def stopMoveDown() {
    isMovingDown = false
  }

  def moveLeft() {
    cube.moveLeft()
  }

  def moveRight() {
    cube.moveRight()
  }

}
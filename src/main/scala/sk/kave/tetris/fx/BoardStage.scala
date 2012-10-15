package sk.kave.tetris.fx

import javafx.scene.paint.Color
import scalafx.Includes._
import scalafx.scene.{Group, Scene}
import scalafx.scene.shape.Rectangle
import scalafx.stage.Stage

import sk.kave.tetris.{Property, Cube2x2, Cube, Board}
import sk.kave.tetris.Property._
import scalafx.beans.property.{DoubleProperty, IntegerProperty}
import javafx.event.EventHandler
import javafx.scene.input.{KeyEvent, KeyCode}

/**
 * Responsibility:
 * -- create board, fx-group of (frozen) items
 * -- create cube
 * -- create fx-scene with board and cube
 * -- handle key-board event and delegate
 *
 * @param board
 */
class BoardStage(implicit val board : Board) extends Stage {

  title = "ScalaFX Tetris"

  val cubeGroup = new CubeGroup

  val boardGroup = new Group() {
    children =
      for (
        iCol  <- 0 until board.frozenItems.length;
        iRow <-  0 until board.frozenItems(iCol).length)
        yield
          new Rectangle() {
            width = ItemSize
            height =ItemSize
            x = iCol * Property.ItemSize
            y = iRow * Property.ItemSize

            fill <== when ( board.frozenItems(iCol)(iRow) ) then Color.BLUE otherwise Color.BLACK
          }
  }



  scene = new Scene(Cols * ItemSize , Rows * ItemSize) {
    fill = Color.BLACK
    content = List(
      boardGroup ,
      cubeGroup
    )
    cubeGroup.start()

    onKeyPressed = new EventHandler[KeyEvent] {
      def handle(e : KeyEvent) {
        if ( e.getCode() == KeyCode.LEFT)  cubeGroup.moveLeft()
        if ( e.getCode() == KeyCode.RIGHT) cubeGroup.moveRight()
        if ( e.getCode() == KeyCode.DOWN)  cubeGroup.moveDown()
      }
    }

    onKeyReleased = new EventHandler[KeyEvent] {
      def handle(e : KeyEvent) {
//        if ( e.getCode() == KeyCode.LEFT)  cubeGroup.stopMoveLeft()
//        if ( e.getCode() == KeyCode.RIGHT) cubeGroup.stopMoveRight()
        if ( e.getCode() == KeyCode.DOWN)  cubeGroup.stopMoveDown()
      }
    }


  }

}

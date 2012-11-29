/*
 * Copyright viliam.kois@gmail.com Kois Viliam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sk.kave.tetris.fx

import javafx.scene.paint.Color
import scalafx.Includes._
import scalafx.scene._
import scalafx.scene.shape.Rectangle
import scalafx.stage.Stage

import sk.kave.tetris._
import javafx.event.EventHandler
import javafx.stage.WindowEvent
import javafx.scene.input.{KeyEvent, KeyCode}

/**
 * Responsibility:
 * -- create board, fx-group of (frozen) items
 * -- create cube
 * -- create fx-scene with board and cube
 * -- handle key-board event and delegate
 *
 */
class BoardStage extends Stage {

  title = "ScalaFX Tetris"

  val boardGroup = new Group() {
    children =
      for (
        iCol  <- 0 until Board.frozenItems.length;
        iRow <-  0 until Board.frozenItems(iCol).length)
        yield
          new Rectangle() {
            width = ItemSize -2
            height =ItemSize -2
            x = iCol * ItemSize +1
            y = iRow * ItemSize +1

            fill <== when ( Board.frozenItems(iCol)(iRow) ) then Color.BLUE otherwise Color.BLACK
          }
  }

  val controlerActor = GameControlerActor.start()

  scene = new Scene(Cols * ItemSize , Rows * ItemSize) {
    fill = Color.BLACK
    content = List(
      boardGroup ,
      CubeGroup
    )

    controlerActor ! Action.DOWN

    onKeyPressed = new EventHandler[KeyEvent] {
      def handle(e : KeyEvent) {
        e.code match {
          case( KeyCode.UP)    => controlerActor ! Action.ROT
          case( KeyCode.LEFT)  => controlerActor ! Action.LEFT
          case( KeyCode.RIGHT) => controlerActor ! Action.RIGHT
          case( KeyCode.DOWN)  => controlerActor ! Action.SPEED
          case _ => ()
        }
      }
    }

    onKeyReleased = new EventHandler[KeyEvent] {
      def handle(e : KeyEvent) {
        if ( e.getCode() == KeyCode.DOWN)  controlerActor ! Action.STOP_SPEED
      }
    }
  }

  onHiding = new EventHandler[WindowEvent] {
    def handle( e: WindowEvent) {
      controlerActor ! Action.EXIT
    }
  }
}

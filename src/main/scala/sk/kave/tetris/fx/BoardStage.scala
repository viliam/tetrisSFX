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

import sk.kave.tetris._
import javafx.event.EventHandler
import javafx.stage.{WindowEvent, Stage}
import javafx.scene.{Scene, Group}
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.beans.binding.{Bindings, Binding}
import javafx.beans.binding.Bindings._
import scala.collection.JavaConversions._
import javafx.scene.layout.StackPane

/**
 * Responsibility:
 * -- create board, fx-group of (frozen) items
 * -- create cube
 * -- create fx-scene with board and cube
 * -- handle key-board event and delegate
 *
 */
class BoardStage(val stage: Stage) {

  stage.setTitle(  "ScalaFX Tetris")

  val boardGroup = new Group() {
    getChildren().addAll(
      for (
        iCol  <- 0 until Board.frozenItems.length;
        iRow <-  0 until Board.frozenItems(iCol).length)
        yield
          new Rectangle() {
            setWidth  (ItemSize -2)
            setHeight (ItemSize -2)
            setX (iCol * ItemSize +1)
            setY (iRow * ItemSize +1)
            fillProperty().bind(
              Bindings
                .when( Board.frozenItems(iCol)(iRow))
                .then( Color.BLUE)
                .otherwise( Color.BLACK) )
          }
    )
  }

  val controlerActor = GameControlerActor.start()

//  val root = new StackPane
//  root.getChildren.addAll( List(
//    boardGroup ,
//    CubeGroup
//  ))
  boardGroup.getChildren.add( CubeGroup)
  stage.setScene ( new Scene(boardGroup, Cols * ItemSize , Rows * ItemSize) {
      setFill ( Color.BLACK )


      controlerActor ! Action.DOWN

      setOnKeyPressed ( new EventHandler[KeyEvent] {
        def handle(e : KeyEvent) {
          e.getCode match {
            case( KeyCode.UP)    => controlerActor ! Action.ROT
            case( KeyCode.LEFT)  => controlerActor ! Action.LEFT
            case( KeyCode.RIGHT) => controlerActor ! Action.RIGHT
            case( KeyCode.DOWN)  => controlerActor ! Action.SPEED
            case _ => ()
          }
        }
      })

      setOnKeyReleased ( new EventHandler[KeyEvent] {
        def handle(e : KeyEvent) {
          if ( e.getCode() == KeyCode.DOWN)  controlerActor ! Action.STOP_SPEED
        }
      })
    }
  )

  stage.setOnHiding ( new EventHandler[WindowEvent] {
    def handle( e: WindowEvent) {
      controlerActor ! Action.EXIT
    }
  })
}

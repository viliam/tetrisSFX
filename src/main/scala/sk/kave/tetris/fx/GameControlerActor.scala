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

import actors.Actor
import scalafx.animation.Timeline
import javafx.event.EventHandler
import javafx.event.ActionEvent
import scalafx.Includes._
import sk.kave.tetris.Board

/*

This actor will be responsible for handling events. This asynchronized processing of events make processing events
synchronized.

 */

object Action extends Enumeration {
  val DOWN, LEFT, RIGHT, SPEED, STOP_SPEED, ROT, EXIT = Value
}

object GameControlerActor  extends Actor {
  self =>

  def act() {
    react {
        case ( Action.DOWN)   =>
          runInJFXthred( moveDown() )
          act()
        case ( Action.ROT)   =>
          runInJFXthred( CubeGroup.rotateCube() )
          act()
        case ( Action.LEFT)  =>
          runInJFXthred( moveHorizontal( -1) )
          act()
        case ( Action.RIGHT) =>
          runInJFXthred( moveHorizontal( 1) )
          act()
        case ( Action.SPEED)  =>
          isSpeed = true
          act()
        case ( Action.STOP_SPEED)  =>
          isSpeed = false
          act()
        case ( Action.EXIT)  => ()
    }
  }

  var isSpeed = false
  var isMovingHorizontal = false

  private def moveHorizontal( dx : Int) {
      import CubeGroup._

      if ( !isMovingHorizontal && cube.isXfree(dx) ) {
        isMovingHorizontal = true
        new Timeline() {
          onFinished = new EventHandler[ ActionEvent] {
            def handle(e : ActionEvent) {
              isMovingHorizontal = false
            }
          }

          keyFrames = Seq(
            at(0 ms)         { Set( cube.x ->  cube.x() )},
            at(50 ms)        { Set( cube.x -> ( cube.x() + dx) ) }
          )
        }.play
      }
  }


  private def moveDown() {
    if (!Board.isFree(for ((xx, yy) <- CubeGroup.position) yield (xx, yy + 1)))
      CubeGroup.newCube()

    new Timeline() {
      onFinished = new EventHandler[ActionEvent] {
        def handle(e: ActionEvent) {
          self ! Action.DOWN
        }
      }

      var speedDelay = if (isSpeed) 10 ms else 200 ms

      import CubeGroup._

      keyFrames = Seq(
        at(0 s) {
          Set(cube.y -> cube.y())
        },
        at(speedDelay) {
          Set(cube.y -> (cube.y() + 1))
        }
      )
    }.play
  }

  private def runInJFXthred( runThis : => Unit ) {
    javafx.application.Platform.runLater(new Runnable() { def run() = runThis } )
  }
}



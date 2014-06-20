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
import javafx.event.EventHandler
import scala.collection.JavaConversions._
import javafx.event.ActionEvent
import sk.kave.tetris.Board
import javafx.animation.{KeyValue, KeyFrame, Timeline}
import javafx.util.Duration
import javafx.beans.value.WritableValue

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
        val tl = new Timeline()
        tl.setOnFinished ( new EventHandler[ ActionEvent] {
          def handle(e : ActionEvent) {
            isMovingHorizontal = false
          }
        })

        tl.getKeyFrames.addAll( Seq(
        //compilator got problem with generic types, so I typed to what he want:  WritableValue[Any]
          new KeyFrame( Duration.seconds(0),new KeyValue(cube.x.asInstanceOf[WritableValue[Any]] , cube.x.get)),
          new KeyFrame( Duration.millis(50), new KeyValue(cube.x.asInstanceOf[WritableValue[Any]], cube.x.get+dx))
        ))
        tl.play
      }
  }


  private def moveDown() {
    if (!Board.isFree(for ((xx, yy) <- CubeGroup.position) yield (xx, yy + 1)))
      CubeGroup.newCube()

    val tl = new Timeline()
    tl.setOnFinished( new EventHandler[ActionEvent] {
        def handle(e: ActionEvent) {
          self ! Action.DOWN
        }
      })

    var speedDelay = if (isSpeed) Duration.millis(10) else Duration.millis(200)

    import CubeGroup._

    tl.getKeyFrames.addAll( Seq(
      //compilator got problem with generic types, so I typed to what he want:  WritableValue[Any]
        new KeyFrame( Duration.seconds(0),new KeyValue(cube.y.asInstanceOf[WritableValue[Any]], cube.y.get)),
        new KeyFrame( speedDelay, new KeyValue(cube.y.asInstanceOf[WritableValue[Any]], cube.y.get+1))
      ))

    tl.play
  }

  private def runInJFXthred( runThis : => Unit ) {
    javafx.application.Platform.runLater(new Runnable() { def run() = runThis } )
  }
}



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

class CubeGroup extends Group {

  var cube : Cube =  Cube.nextCube

  val realX = IntegerProperty( 0)
  val realY = IntegerProperty( 0)

  var isMovingDown = false

  children = makeCubeBinding

  def makeCubeBinding = {
    realX <== cube.x * ItemSize
    realY <== cube.y * ItemSize

    for ((xx,yy) <- cube.position)
      yield new Rectangle {
              width = ItemSize -2
              height =ItemSize -2

              x <== realX + (xx * Property.ItemSize) +1
              y <== realY + (yy * Property.ItemSize) +1

              fill = Color.BLUE
            }
  }

  def position : List[ ( Int, Int)]
      = for ( (xx,yy) <- cube.position) yield ( cube.x().toInt + xx, cube.y().toInt + yy)

  def makeMove() {
    if ( !Board.isFree( for ( (xx,yy) <- position ) yield (xx, yy+1) ) )
      newCube()

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


  def newCube() {
    Board.freeze(cube )
    Board.clear()                    //check if is full row

    children.removeAll()             //we need remove old bind cube position

    cube = Cube.nextCube

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

  def rotateCube() {
    children.removeAll()
    cube.rotate()
    children = makeCubeBinding
  }

}
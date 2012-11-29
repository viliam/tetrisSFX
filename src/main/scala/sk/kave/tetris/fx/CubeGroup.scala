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
import sk.kave.tetris.{Board, Cube}
import scalafx.beans.property.{IntegerProperty}
import sk.kave.tetris._

/**
 * Responsibility:
 * -- hold the real position of cube on the boardStage (by binding)
 * -- create fx-rectangle, which are representing cube on boardStage
 * -- handle time-line, for falling cube down
 * -- delegate moving event to cube
 *
**/

object CubeGroup extends Group {

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

              x <== realX + (xx * ItemSize) +1
              y <== realY + (yy * ItemSize) +1

              fill = Color.BLUE
            }
  }

  def position : List[ ( Int, Int)]
      = for ( (xx,yy) <- cube.position) yield ( cube.x().toInt + xx, cube.y().toInt + yy)

  def newCube() {
    Board.freeze(cube )
    Board.clear()                    //check if row is full

    children.removeAll()             //we need remove old bind cube position

    cube = Cube.nextCube

    children = makeCubeBinding
  }

  def rotateCube() {
    children.removeAll()
    cube.rotate()
    children = makeCubeBinding
  }

}
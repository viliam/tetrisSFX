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

import sk.kave.tetris.{Board, Cube}
import sk.kave.tetris._
import scala.collection.JavaConversions._
import javafx.scene.Group
import javafx.scene.shape.Rectangle
import javafx.scene.paint.Color
import javafx.beans.property.SimpleIntegerProperty

object CubeGroup extends Group {

  var cube : Cube =  Cube.nextCube

//  val realX = new SimpleIntegerProperty( 0)
//  val realY = new SimpleIntegerProperty( 0)

  var isMovingDown = false

  getChildren.addAll( makeCubeBinding)

  def makeCubeBinding = {
    for ((xx,yy) <- cube.position)
      yield {
            val r = new Rectangle() {
              setWidth ( ItemSize - 2)
              setHeight ( ItemSize - 2)
            }
            r.xProperty().bind( (cube.x multiply  ItemSize) .add (ItemSize*xx  ).add(1) )
            r.yProperty().bind( (cube.y multiply  ItemSize) .add (ItemSize*yy  ).add(1) )

            r.setFill ( Color.BLUE)
            r
          }
  }

  def position : List[ ( Int, Int)]
      = for ( (xx,yy) <- cube.position) yield ( cube.x.get.toInt + xx, cube.y.get.toInt + yy)

  def newCube() {
    Board.freeze(cube )
    Board.clear()                    //check if row is full

    getChildren.clear()             //we need remove old bind cube position

    cube = Cube.nextCube
    getChildren.addAll( makeCubeBinding)
  }

  def rotateCube() {
    getChildren.clear()
    cube.rotate()
    getChildren.addAll( makeCubeBinding)
  }

}

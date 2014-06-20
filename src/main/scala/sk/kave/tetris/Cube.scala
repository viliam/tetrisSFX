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

package sk.kave.tetris

import javafx.event.EventHandler
import javafx.event
import java.util.Random
import javafx.beans.property.{SimpleDoubleProperty, DoubleProperty}

object Cube {

  val cubes  = List[Cube]( Cube2x2, CubeL, CubeL2, CubeT,
                           CubeI, CubeS, CubeZ)

  def nextCube : Cube = {
    val rnd = new Random();
    val nextCube = cubes( rnd.nextInt( cubes.length ) )
    nextCube.init()
    nextCube
  }
}

abstract class Cube (
   val x : DoubleProperty = new SimpleDoubleProperty(0),
   val y : DoubleProperty = new SimpleDoubleProperty(0) )  {

  var isMoving = false
  var rotation =0

  def shapes : List[List[ (Int, Int)]]
  def position = shapes(rotation )

  def init() {
    rotation =0
    x.set( Cols /2)
    y.set( 0)
  }

  def rotate()   {
    val nextRot = (rotation + shapes.length - 1) % shapes.length
    if (isRotFree( nextRot ))
      rotation = nextRot
  }

  /**
   * Helpers checking methods for free position
   */

  def isRotFree(rotation : Int) : Boolean = {
    isFree(for ((xx, yy) <- shapes(rotation)) yield (x.get.toInt + xx , roundUp(y.get) + yy))
  }

  def isXfree(dx: Int) : Boolean = {
    isFree(for ((xx, yy) <- position) yield (x.get.toInt + xx + dx, roundUp(y.get) + yy))
  }

  def isDownFree : Boolean =
    isFree( for ( (xx,yy) <- position) yield ( x.get.toInt + xx, y.get.toInt + yy +1) )

  private def roundUp( x : Double) : Int =
     if ( x.toInt == x) x.toInt
     else x.toInt + 1

}

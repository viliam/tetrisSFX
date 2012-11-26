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

import scalafx.scene.{Node, Group}
import scalafx.beans.property.{DoubleProperty, IntegerProperty}
import scalafx.Includes._
import scalafx.beans.binding.{NumberExpression, NumberBinding}
import sk.kave.tetris.Utils._
import scalafx.animation.Timeline
import javafx.event.EventHandler
import javafx.event
import sk.kave.tetris.Property._
import java.util.Random

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
   val x : DoubleProperty = DoubleProperty(0),
   val y : DoubleProperty = DoubleProperty(0) )  {

  var isMoving = false
  var rotation =0

  def shapes : List[List[ (Int, Int)]]
  def position = shapes(rotation )

  def init() {
    rotation =0
    x.value = Cols /2
    y.value = 0
  }

  def rotate()   {
    val nextRot = (rotation + shapes.length - 1) % shapes.length
    if (isRotFree( nextRot ))
      rotation = nextRot
  }

  def moveLeft()  : Boolean = moveHorizontal( -1)
  def moveRight() : Boolean = moveHorizontal( 1)

  private def moveHorizontal( dx : Int) : Boolean =
    if ( !isMoving && isXfree(dx) ) {
      isMoving = true
      new Timeline() {
        onFinished = new EventHandler[event.ActionEvent] {
          def handle(e : event.ActionEvent) {
            isMoving = false
          }
        }
        keyFrames = Seq(
          at(0 ms)         { Set( x ->  x() )},
          at(50 ms)        { Set( x -> ( x() + dx) ) }
        )
      }.play
      true
    } else
      false

  def isRotFree(rotation : Int) : Boolean = {
    isFree(for ((xx, yy) <- shapes(rotation)) yield (x().toInt + xx , round(y()) + yy))
  }

  def isXfree(dx: Int) : Boolean = {
    isFree(for ((xx, yy) <- position) yield (x().toInt + xx + dx, round(y()) + yy))
  }

  def isDownFree : Boolean =
    isFree( for ( (xx,yy) <- position) yield ( x().toInt + xx, y().toInt + yy +1) )


  def round( x : Double) : Int =
     if ( x.toInt == x) x.toInt
     else x.toInt + 1

}

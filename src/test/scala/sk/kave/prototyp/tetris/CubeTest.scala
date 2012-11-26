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

package sk.kave.prototyp.tetris

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import sk.kave.tetris.{Cube, Board}
import scalafx.beans.property.{DoubleProperty, IntegerProperty}

@RunWith(classOf[JUnitRunner])
class CubeTest extends FlatSpec with ShouldMatchers {
//
//
//  class TestCube(x : Int)(implicit override val board : Board) extends Cube( IntegerProperty(x) ) {
//    override def position : List[ (Int, Int)] = List( (0,0), (0,1) )
//  }

//  val testFrozenItem = Array( Array(false, false, false, false),
//                              Array(false, true,  false, false),
//                              Array(false, true,  true,  false))
//
//
//  implicit val board = new Board( testFrozenItem )
//
//  "A Cube " should " initialized correctly" in {
//    val cube = new Cube( IntegerProperty(10), DoubleProperty(5) )
//    assert( cube.x() == 10)
//    assert( cube.y() == 5)
//    assert( cube.position.size == 1)
//    assert( cube.position.head == (0,0))
//  }
//
//  it should " check if is free place down" in {
//     val testCube = new TestCube( 0)
//     assert( testCube.isDownFree)
//     testCube.x.value = 1
//     assert( !testCube.isDownFree)
//  }
//
//  it should " move down" in {
//    val cube = new TestCube(0)
//    assert( cube.moveDown)
//    assert( cube.y() == 6)
//  }
}

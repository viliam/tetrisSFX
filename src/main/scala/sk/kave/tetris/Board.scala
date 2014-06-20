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

import javafx.beans.property.BooleanProperty

object Board {

  val frozenItems : Array[Array[BooleanProperty]] = makeFrozenItems

  def clear() {
    for (i <- 0 until Rows; if ( isFullRow(i)) ) {
        clearRow(i)
    }
  }

  /**
   * Recognize if given rull is full
   */
  def isFullRow(row: Int): Boolean =  frozenItems.forall{ c => c(row).get() == true}

  /**
   * Given row will be erased. Every row above given row, will be
   * shifting down, to fill empty place.
   */
  def clearRow(row : Int) {
    assert( isFullRow( row))

    for (
      iCol  <- 0 until frozenItems.length;
      iRow  <- List.range( row, 1, -1)) {
        frozenItems(iCol)(iRow).set( frozenItems(iCol)(iRow-1).get)
    }

    for (col <- frozenItems) {
      col( 0).set( false)
    }
  }

  def isFreeItem(item : (Int, Int) )  = !frozenItems(item._1)( item._2).get()
  def isFree(position : List [ (Int, Int) ]) =
    position.forall( i =>  !isOut (i) && isFreeItem (i) )

  def freeze(x: Int, y: Int)  { frozenItems(x)(y).set( true) }
  def freeze( cube : Cube ) {
     for ( (xx,yy) <- cube.position) freeze( cube.x.get.toInt + xx,  cube.y.get.toInt + yy)
  }

  def isOut(item : ( Int, Int) ) : Boolean  = {
    val (x, y) = item
    x < 0 || x == frozenItems.length || y >= frozenItems( x).length
  }

}

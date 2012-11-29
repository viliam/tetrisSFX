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

package sk.kave

import scalafx.beans.property.BooleanProperty

package object tetris {

  val Rows = 30
  val Cols = 10

  val ItemSize = 20


  final private[tetris] def isFree( positions : List[ (Int,Int)]) : Boolean = {
    for ( (x,y) <- positions
        if Board.isOut( x,y) || !Board.isFreeItem( x,y) ) {
      return false
    }
    true
  }

  def makeFrozenItems : Array[Array[BooleanProperty]]  = {
      val frozenItems = new Array[Array[BooleanProperty]] (Cols)
      for (i <- (0 until Cols) ) {
        frozenItems(i) = new Array[BooleanProperty](Rows)
        for ( j <- (0 until Rows))
          frozenItems(i)(j) = false
      }
      frozenItems
    }

  def rotateList( li : List[ (Int,Int)], size : Int ) : List[List[(Int, Int)]] ={

    def oneRotation( li : List[ (Int, Int)]) : List[ (Int, Int)] = {
      for ( (x,y) <- li) yield (size - y,x)
    }

    var nextLi = li
    var resultList : List[List[(Int, Int)]]  = Nil
    resultList = li :: resultList
    for ( i <- 1 until 4) {
      nextLi = oneRotation(nextLi)
      resultList = nextLi :: resultList
    }

    resultList
  }
}


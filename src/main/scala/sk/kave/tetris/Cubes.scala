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

import scalafx.beans.property.{DoubleProperty, IntegerProperty}
import scalafx.Includes._
import scalafx.beans.binding.{NumberExpression, NumberBinding}

object Cube2x2 extends Cube  {
  override def shapes = List( List( (0,0),( 1, 0 ), (0, 1), (1, 1) ) )
}

object CubeL extends Cube {
  override def shapes = rotateList( List( (0,0),( 1, 0 ), (2, 0), (2, 1) ), 2)
}

object CubeL2 extends Cube {
  override def shapes = rotateList( List( (0,0),( 1, 0 ), (2, 0), (0, 1) ), 2)
}

object CubeT extends Cube {
  override def shapes = rotateList( List( (0,0),( 1, 0 ), (2, 0), (1, 1) ), 2)
}

object CubeI extends Cube {
  override def shapes =  List(
    List( (0,0),( 1, 0 ), (2, 0), (3, 0) ),
    List( (0,0),( 0, 1 ), (0, 2), (0, 3) )
  )
}

object CubeS extends Cube {
  override def shapes =  List(
    List( (1,0),( 2, 0 ), (0, 1), (1, 1) ),
    List( (1,0),( 1, 1 ), (2, 1), (2, 2) )
  )
}

object CubeZ extends Cube {
  override def shapes =  List(
    List( (0,0),( 1, 0 ), (1, 1), (2, 1) ),
    List( (1,2),( 1, 1 ), (2, 1), (2, 0) )
  )
}

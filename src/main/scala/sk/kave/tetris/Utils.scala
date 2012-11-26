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

import scalafx.beans.binding.{NumberExpression, NumberBinding}
import scalafx.beans.property.IntegerProperty

object Utils {

  implicit def numberBiding2int( n : NumberBinding) : Int = n.value.intValue()
  implicit def numberExpression2int( n : NumberExpression) : Int = n.getValue.intValue()

  implicit def bindAndGet( bindingX : NumberExpression) : NumberExpression= {
    val ip = new IntegerProperty()
    ip <== bindingX
    ip
  }
}

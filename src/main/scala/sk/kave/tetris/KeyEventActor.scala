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

import scala.actors._
import javafx.scene.input.{KeyEvent, KeyCode}

/*

This actor will be responsible for handling key event, make queue of next move for cube.
A cube will be actor too, every time cube finsh move, ask KeyEventActor if handle next move event.

 */


object KeyEventActor extends Actor {

   def act() {
     react {
       case (e : KeyEvent ) =>
         println( "hura actujem" )
         act()
     }
   }
}

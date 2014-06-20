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

import tetris.Board
import tetris.fx.BoardStage
import javafx.application.Application
import javafx.stage.Stage

object MainTetris { //extends Application {

//  setStage ( new BoardStage)

  def main(args: Array[String]) {
    Application.launch(classOf[AppHelper], args: _*)
  }


}

class AppHelper extends javafx.application.Application {
  def start(stage: javafx.stage.Stage) {
    new BoardStage(stage)
    stage.show()
  }

  override def stop() {
  }
}
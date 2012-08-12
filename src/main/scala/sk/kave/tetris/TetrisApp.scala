package sk.kave.tetris

import scalafx.application.JFXApp
import Property._
import scalafx.stage.Stage
import scalafx.scene.Scene

object TetrisApp extends JFXApp {

  stage = new Stage {
    scene = new Scene(Rows * ItemSize, Cols * ItemSize)

  }

}

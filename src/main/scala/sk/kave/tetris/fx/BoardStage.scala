package sk.kave.tetris.fx

import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.stage.Stage
import sk.kave.tetris.{Cube2x2, Cube, Board}

import sk.kave.tetris.Property._

class BoardStage(implicit val board : Board) extends Stage {

  title = "ScalaFX Tetris"

  def startPosition = Cols /2

  implicit val cube : Cube = new Cube2x2( startPosition)
  val cubeGroup = new CubeGroup

  scene = new Scene(Cols * ItemSize , Rows * ItemSize) {
    fill = Color.BLACK
    content = List(
      cubeGroup
    )
    cubeGroup.start()
  }

}

package sk.kave.tetris.fx

import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.stage.Stage
import scalafx.Includes._
import sk.kave.tetris.{Cube, Board}

import sk.kave.tetris.Property._
import scalafx.animation.Timeline.INDEFINITE
import scalafx.animation.Tweenable.tweenable2KeyFrame
import scalafx.animation.Timeline

class BoardStage(implicit val board : Board) extends Stage {

  title = "ScalaFX Tetris"

  def startPosition = (Rows /2)

  implicit val cube : Cube = Cube( startPosition)
  val cubeGroup = new CubeGroup

  scene = new Scene(500, 500) {
    fill = Color.BLACK
    content = List(
      cubeGroup
    )
  }

  new Timeline {
    cycleCount = INDEFINITE
    autoReverse = true
    keyFrames = Seq(
        at(0 s) { Set(cube.y -> cube.y.value)},
        at(10 s) { Set(cube.y -> (cube.y.value + 500) )}
    ).flatten
  }.play

  new Timeline {
    cycleCount = INDEFINITE
    autoReverse = true
    keyFrames = Seq(
        at(0 s) { Set(cube.x -> cube.x.value)},
        at(3 s) { Set(cube.x -> (cube.x.value + 100) )}
    ).flatten
  }.play
}

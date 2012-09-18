package sk.kave

import scalafx.application.JFXApp
import tetris.Board
import tetris.fx.BoardStage

object MainTetris extends JFXApp {

  implicit val board = new Board

  stage = new BoardStage

}

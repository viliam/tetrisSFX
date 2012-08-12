package sk.kave.tetris.fx

import scalafx.scene.Group
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import sk.kave.tetris.{Cube, Property}

class CubeGroup (implicit cube : Cube) extends Group {

  val rec = new Rectangle {
    import Property._
    width = ItemSize
    height =ItemSize
    x <== cube.x
    y <== cube.y
    fill = Color.BLUE
  }

  children = Seq (
    rec
  )

}

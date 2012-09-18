package sk.kave.tetris.fx

import scalafx.scene.Group
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import sk.kave.tetris.{Board, Cube, Property}
import scalafx.animation.Timeline
import scalafx.animation.Timeline._
import scalafx.Includes._
import javafx.event
import event.EventHandler
import scalafx.event.ActionEvent
import scalafx.event.EventHandler._
import Property._
import sk.kave.tetris.Utils._
import scalafx.beans.property.IntegerProperty
import sk.kave.MainTetris
import scalafx.scene.control._ListView._EditEvent

class CubeGroup (implicit cube : Cube) extends Group {

  val board = MainTetris.board;

  val realX = IntegerProperty( cube.x * ItemSize)
  val realY = IntegerProperty( cube.y * ItemSize)

  var bindCube = makeCubeBinding

  children = bindCube

  def makeCubeBinding =
    for ((xx,yy) <- cube.position) yield makeRec( xx, yy, true)

  def makeRec( xx: Int, yy: Int, binding : Boolean) =
    new Rectangle {
      width = ItemSize
      height =ItemSize
      if ( binding) {
        x <== realX + (xx * Property.ItemSize)
        y <== realY + (yy * Property.ItemSize)
      } else {
        x =  xx * Property.ItemSize
        y =  yy * Property.ItemSize
      }

      fill = Color.BLUE
    }

  def makeRec( cube : Cube) : List[scalafx.scene.shape.Rectangle] = {  //todo: make Node by result type
    for ( (xx,yy) <- cube.position)
      yield makeRec( cube.x + xx, cube.y + yy, false)
  }

  def position : List[ ( Int, Int)]
                   = for ( (xx,yy) <- cube.position) yield ( cube.x + xx, cube.y + yy)

  def start() {
    new Timeline() {
      //cycleCount = INDEFINITE
      //autoReverse = true

      onFinished = new EventHandler[event.ActionEvent] {
        def handle(e : event.ActionEvent) {
          println("[" + cube.x  + ", " +cube.y + "]")
          cube.y += 1

          if ( !board.isFree( for ( (xx,yy) <- position ) yield (xx, yy+1) ) )  {
            board.freeze(cube )
            println( "freezee  : [" + cube.x + " , " + cube.y + "]" )

            for ( node <- makeRec( cube) )   //at frist make new rectangle on freez item
              children.add( node  )
            for ( node <- bindCube)   //we need remove old bind cube position
              children.remove( node  )

            cube.y = 0
            realX.value = cube.x * ItemSize
            realY.value = cube.y * ItemSize
            bindCube = makeCubeBinding  //make new cube binding
            for ( node <- bindCube)
              children.add( node)
          }

          start()
        }
      }


      keyFrames = Seq(
        at(0 s)    { Set(realY -> realY.intValue() )},
        at(200 ms) { Set(realY -> (realY.intValue() + ItemSize) ) }
      )
    }.play
  }

}

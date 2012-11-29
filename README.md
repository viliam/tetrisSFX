TetrisSFX - are you ready for playing game?

------ABOUT
This project is a show case of using scala and scalaFx. Scala is very useful to define and use DSL, I tried show it
in this simple example.

------ARCHITECTURE
package sk.kave.tetris
... object Board - two dimensional array of boolean represent "frozen" item on game board
... object Cube  - cube is represent like List of item position (x,y).
... package scala - global property and utils method

package sk.kave.tetris.fx
... contains fx-wrapped objects: BoardStage, CubeGroup. This layer bind tetris data-structures to javaFx graphic object
... class GameControlActor controls event and timeLines for moving cube

------BUILD

use MAVEN for building project

ScalaFX project (http://code.google.com/p/scalafx/) don't have maven artifact today.
To use it, you must add scalafx-1.0-SNAPSHOT.jar file manually to yours local repository, see:

http://code.google.com/p/scalafx/issues/detail?id=4

mvn install:install-file -DartifactId=scalafx \
  -DgroupId=org.scalafx \
  -Dpackaging=jar \
  -DpomFile=scalafx-1.0-SNAPSHOT.pom \
  -Dfile=scalafx-1.0-SNAPSHOT.jar \
  -Dversion=1.0-SNAPSHOT \
  -Dsources=scalafx-1.0-SNAPSHOT-sources.jar
TetrisSFX - are you ready for playing game?

------ABOUT
Simple engine for game tetris.

------ARCHITECTURE
package sk.kave.tetris
... object Board - two dimensional array of boolean represent "frozen" items on game board
... object Cube  - cube is representing like List of item position (x,y).
... package scala - global property and util methods

package sk.kave.tetris.fx
... contains fx-wrapped objects: BoardStage, CubeGroup. This layer binds tetris data-structures to javaFx graphic object
... class GameControlActor controls events and timeLines for moving cube

-----TECHNOLOGIES
Scala & JavaFX

------BUILD
Maven
don't forget to edit on pom.xml the javafx path, for system dependency
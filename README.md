TetrisSFX - are you read for playing game?

use MAVEN for building project

we are experimenting with ScalaFX project (http://code.google.com/p/scalafx/)
which don't have maven artifact today. To use it, you must add scalafx-1.0-SNAPSHOT.jar
file manually to yours local repository, see:

http://code.google.com/p/scalafx/issues/detail?id=4

mvn install:install-file -DartifactId=scalafx \
  -DgroupId=org.scalafx \
  -Dpackaging=jar \
  -DpomFile=scalafx-1.0-SNAPSHOT.pom \
  -Dfile=scalafx-1.0-SNAPSHOT.jar \
  -Dversion=1.0-SNAPSHOT \
  -Dsources=scalafx-1.0-SNAPSHOT-sources.jar
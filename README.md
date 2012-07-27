TANK - are you read for playing game?

use MAVEN for building project

we are experimenting with ScalaFX project (http://code.google.com/p/scalafx/)
which don't have maven artifact today. To use it, you must add scalafx-1.0-SNAPSHOT.jar
file manually to yours local repository, see:

http://code.google.com/p/scalafx/issues/detail?id=4
http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html

mvn install:install-file -Dfile=scalafx-1.0-SNAPSHOT.jar -DgroupId=ScalaFX \
    -DartifactId=scalafx -Dversion=1.0-SNAPSHOT -Dpackaging=scalafx

I used this command:
C:\$PROJECT_DIR$\tank>mvn install:install-file -Dfile=c:\$JAR_DIR$\scalafx.jar \
    -DgroupId=ScalaFX  -Dpackaging=scalafx -DartifactId=scalafx -Dversion=1.0-SNAPSHOT
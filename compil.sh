cd src
javac -d ../bin Modele/*.java
javac -d ../bin Vue/*.java
javac -d ../bin Labyrinthe.java
cd ..
java -cp bin Labyrinthe
jar cmf bin/MANIFEST.mf Labyrinthe.jar -C bin/ .
# On compile le projet
cd src
javac -d ../bin Modele/*.java
javac -d ../bin Vue/*.java
javac -d ../bin Labyrinthe.java

# On crée le .jar
cd ../bin
jar cmf MANIFEST.mf ../JeuLabyrinthe.jar Modele/*.class Vue/*.class Labyrinthe.class img
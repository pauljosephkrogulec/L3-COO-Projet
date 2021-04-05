# On compile le projet
cd src
javac -d ../bin modele/*.java
javac -d ../bin vue/*.java
javac -d ../bin Labyrinthe.java

# On crée le .jar
cd ../bin
jar cmf MANIFEST.mf ../JeuLabyrinthe.jar modele/*.class vue/*.class Labyrinthe.class img
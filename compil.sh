cd src
javac -d ../bin Modele/*.java
javac -d ../bin Vue/*.java
javac -d ../bin Labyrinthe.java
cd ..
java -cp bin Labyrinthe
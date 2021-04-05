# Le jeu du labyrinthe

Vous trouvez dans cette archive :
- Un dossier bin : contenant l'ensemble des images du jeu ainsi que les fichiers .class.
(les images ont été réalisés par nous-même exepté les couloirs et les objectifs qui sont des images libre de droits provenant d'internet).
- Un dossier doc : qui contient l'ensemble de la documentation détaillé  (javadoc).
- Un dossier src : qui contient l'ensemble des fichiers .java découpés en deux dossiers, le dossier Vue qui contient la partie graphique du jeu et Modele qui contient la partie applicative.
- Un fichier .jar à la racine pour lancer le jeu.

## Compiler le programme

Pour compiler le jeu, il vous suffit d'exécuter depuis le terminal la commande :
> bash compil.sh

## Lancer le programme

Pour lancer le programme depuis le terminal, vous pouvez entrer la ligne suivante qui executera le jeu.
> java -cp bin Labyrinthe

Sinon, vous pouvez double cliquer sur JeuLabyrinthe.jar ou encore le lancer manuellement depuis le terminal avec la commande :
> java -jar JeuLabyrinthe.jar

*(! le .jar ne reconnait pas le chemin d'accès au image malgrès l'utilisation de getRessources et d'un chemin valide..)*

***Réalisé par Quentin Carpentier & Paul-Joseph Krogulec,***
**Bon jeu !**
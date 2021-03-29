package Modele;

import Vue.VLabyrinthe;

/** Constructeur de la classe JeuFactory qui lance le jeu
 */
public class JeuFactory {
    public static Jeu creeJeu(VLabyrinthe vLabyrinthe) {
        return new JeuImpl(vLabyrinthe);
    }
}

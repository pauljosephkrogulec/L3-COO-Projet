package Modele;

import Vue.VJeu;

/** Constructeur de la classe JeuFactory qui lance le jeu
 */
public class JeuFactory {
    public static Jeu creeJeu(VJeu jeuVue) {
        return new JeuImpl(jeuVue);
    }
}

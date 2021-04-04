package Modele;

// On importe la vue du Jeu.
import Vue.VJeu;

/** Constructeur de la classe JeuFactory qui lance le jeu
 */
public class JeuFactory {

    /** Méthode static qui prend en paramètre la vue du jeu et créer le modèle du jeu.
     * @param jeuVue : la fenêtre du jeu.
     * @return : le modèle du jeu.
     */
    public static Jeu creeJeu(VJeu jeuVue) {
        return new JeuImpl(jeuVue);
    }
}

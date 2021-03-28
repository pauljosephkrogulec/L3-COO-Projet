package Modele;
/** Constructeur de la classe JeuFactory qui lance le jeu
 */
public class JeuFactory {
    public static Jeu creeJeu() {
        return new JeuImpl();
    }
}

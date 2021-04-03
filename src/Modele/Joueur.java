package Modele;
// On importe les librairies.
import java.util.Stack;

/** Interface modélisant un joueur.
 */
public interface Joueur {

    // Déclarations des méthodes essentielles...
    int getAge();
    Pion getPion();
    boolean getFiniTour();
    Stack<Objectif> getObjectifs();
    void joue(Position pos);
    void fixerObjectifs(Stack<Objectif> objectifs);
    void recevoirPion(Pion p);
    boolean objectifsFinis();
}

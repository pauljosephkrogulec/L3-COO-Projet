package modele;

/** Interface modélisant le pion.
 */
public interface Pion {
    
    // Déclarations des méthodes essentielles...
    Objectif deplacer(Position pos);
    Position getPositionInitiale();
    Position getPositionCourante();
    Couleur getCouleur();
}

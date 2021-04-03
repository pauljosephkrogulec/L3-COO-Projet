package Modele;
/** Classe qui modélise un pion en implémentant l'interface Pion..
 */
public class PionImpl implements Pion {

    // Déclaration des variables...
    private Plateau plateau;
    private Position positionInitiale, positionCourante;
    private Couleur couleur;

    /** Constructeur de la classe PionImpl.
	 * @param plateau > le plateau du jeu.
     * @param positionInitiale > la position de départ.
     * @param positionCourante > la position actuelle.
     * @param couleur > la couleur du pion.
	 */
    public PionImpl(Plateau plateau, Position positionInitiale, Position positionCourante, Couleur couleur) {
        this.plateau = plateau;
        this.positionInitiale = positionInitiale;
        this.positionCourante = positionCourante;
        this.couleur = couleur;
    }

    /** Méthode qui renvoie la position initiale.
     * @return : la potition de départ.
     */
    @Override 
    public Position getPositionInitiale() {
        return this.positionInitiale;
    }

    /** Méthode qui renvoie la position courante.
     * @return : la potition actuelle.
     */    
    @Override 
    public Position getPositionCourante() {
        return this.positionCourante;
    }

    @Override
    public Couleur getCouleur() {
        return this.couleur;
    }

    /** Méthode qui déplace le pion à la position donnée.
     * @param pos > la nouvelle position.
     * @return : l'objectif à la nouvelle position.
     */
    @Override 
    public Objectif deplacer(Position pos) {
        if(plateau.estAtteignable(positionCourante, pos)){
            positionCourante = pos;
            return plateau.deplacer(pos, this);
        }
        return null;
    }
}

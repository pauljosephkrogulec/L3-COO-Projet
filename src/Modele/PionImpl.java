package Modele;
/** Classe qui modélise un pion en implémentant l'interface Pion..
 */
public class PionImpl implements Pion {

    // Déclaration des variables...
    private Plateau plateau;
    private Position positionInitiale, positionCourante;

    /** Constructeur de la classe PionImpl.
	 * @param plateau > le plateau du jeu.
     * @param positionInitiale > la position de départ.
     * @param positionCourante > la position actuelle.
	 */
    public PionImpl(Plateau plateau, Position positionInitiale, Position positionCourante) {
        this.plateau = plateau;
        this.positionInitiale = positionInitiale;
        this.positionCourante = positionCourante;
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

    /** Méthode qui pose le pion à la position donnée.
     * @param pos > la nouvelle position.
     */
    public void poserA(Position pos) {
        if(this.plateau.estAtteignable(this.positionCourante, pos)) {
            this.plateau.deplacer(pos, this);
            this.positionCourante = pos;
        }        
    }
}

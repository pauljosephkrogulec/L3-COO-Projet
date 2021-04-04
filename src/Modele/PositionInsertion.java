package Modele;

/** Classe qui énumére les positions d'insertion possibles dans le jeu..
 */
public enum PositionInsertion {

    // Enumérations des positions possibles.
    N1(1,0,1), N2(2,0,3), N3(3,0,5), E1(4,1,0), E2(5,3,0), E3(6,5,0),O3(7,5,6), O2(8,3,6), O1(9,1,6), S3(10,6,5), S2(11,6,3), S1(12,6,1);

    // Déclaration des variables...
    private int place;
    private Position p;

    /** Constructeur des positions d'insertions.
     * @param place : la place selon le plateau.
     * @param x : la coordonées x.
     * @param y : la coordonée y.
     */
    private PositionInsertion(int place,int x,int y) {
        this.place = place;
        this.p = new Position(x, y);
    }
    
    /** Méthode qui renvoie la position d'insertion.
     * @return : la position
     */
    public Position getPos(){
        return this.p;
    }
    
    /** Méthode qui renvoie la position opposée.
     * @return : la position opposée.
     */
    public PositionInsertion oppose() {
        return PositionInsertion.values()[(12 - this.place)]; 
    }
}
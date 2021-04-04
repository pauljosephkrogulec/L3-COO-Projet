package Modele;

/** Classe qui modélise une position..
 */
public class Position {

    // Déclaration des variables...
    private int x,y;

    /** Constructeur de la classe Position.
     * @param x > la coordonnée x.
     * @param y > la coordonnée y.
     */
    public Position(int x,int  y){
        this.x = x;
        this.y = y;
    }

    /** Méthode qui renvoie la coordonée x de la position.
     * @return : la coordonée x.
     */
    public int getX() {
        return x;
    }

    /** Méthode qui renvoie la coordonée y de la position.
     * @return : la coordonée y.
     */
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        Position p = (Position) obj;
        return this.getX() == p.getX() && this.getY() == p.getY();
    }
    
}

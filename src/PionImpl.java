public class PionImpl implements Pion {

    private Plateau plateau;
    private Position positionInitiale, positionCourante;

    public PionImpl(Plateau plateau, Position positionInitiale, Position positionCourante) {
        this.plateau = plateau;
        this.positionInitiale = positionInitiale;
        this.positionCourante = positionCourante;
    }

    @Override 
    public Objectif deplacer(Position pos) {
        if(plateau.estAtteignable(positionCourante, pos)){
            positionCourante = pos;
            return plateau.deplacer(pos, this);
        }
        return plateau.deplacer(positionCourante, this);
    }

    public void poserA(Position pos) {
        if(this.plateau.estAtteignable(this.positionCourante, pos)) {
            this.plateau.deplacer(pos, this);
            this.positionCourante = pos;
        }        
    }
    
}

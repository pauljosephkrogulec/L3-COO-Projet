public class Plateau {
    private Couloir[][] couloirs;
    public Plateau(Couloir[] couloirs) {
        this.couloirs = new Couloir[7][7];
        int x =0;
        int objs = 0;
        for(int i = 0;i<7;i++){
            for(int j = 0;j<7;i++){
                if(i%2==0 && j%2==0){
                    this.couloirs[i][j] = new CouloirFixe(Orientation.SUD, Forme.TE , Objectif.values()[objs++]);
                } else{
                    this.couloirs[i][j] = couloirs[x++];
                }
            }
        }

    }

    public CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c) {
        return null;
    }

    public boolean estAtteignable(Position orig, Position dest) {
        return true;
    }

    public Objectif deplacer(Position pos, Pion pion) {
        this.couloirs[pos.getX()][pos.getY()].setPions(pion);
        return this.couloirs[pos.getX()][pos.getY()].getObjectif();
    }
    
}

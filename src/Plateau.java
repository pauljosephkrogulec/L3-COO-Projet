import java.util.Stack;

public class Plateau {
    private Couloir[][] couloirs;
    public Plateau(Couloir[] couloirs) {
        this.couloirs=new Couloir[7][7];
        int x =0;
        int objs=0;
        for(int i=0;i<7;i++){
            for(int j=0;j<7;i++){
                if(i%2==0 && j%2==0){
                    this.couloirs[i][j]=new CouloirFixe(Orientation.SUD, Forme.TE , Objectif.values()[objs++]);
                } else{
                    this.couloirs[i][j]=couloirs[x++];
                }
            }
        }
    }

    public CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c) {
        Position position = pos.getPos();
        Position oPosition = pos.oppose().getPos();
        CouloirMobile opo = (CouloirMobile) this.couloirs[oPosition.getX()][oPosition.getY()];
        int x;
        if (position.getX() == 0) for(x=0;x<6;x++) this.couloirs[position.getX()+x][position.getY()] =  this.couloirs[position.getX()+x+1][position.getY()];
        else if (position.getY() == 0) for(x=0;x<6;x++) this.couloirs[position.getX()][position.getY()+x] =  this.couloirs[position.getX()][position.getY()+x+1];
        else if (position.getX() == 6) for(x=0;x<6;x++) this.couloirs[position.getX()-x][position.getY()] =  this.couloirs[position.getX()-(x+1)][position.getY()];
        else for(x=0;x<6;x++) this.couloirs[position.getX()][position.getY()-x] =  this.couloirs[position.getX()][position.getY()-(x+1)];
        this.couloirs[position.getX()][position.getY()] = c;
        return opo;
    }

    private boolean estAdjacent(int a,int b) {
        if(a > b) return estAdjacent(b, a);
        Couloir ca,cb;
        ca = this.couloirs[a/7][a%7];
        cb = this.couloirs[b/7][b%7];
        if((b%7) - (a%7) == 1) return (((ca.getForme() == Forme.COUDE && (ca.getOrientation() == Orientation.EST || ca.getOrientation() == Orientation.NORD)) || (ca.getForme() == Forme.DROIT && (ca.getOrientation() == Orientation.EST || ca.getOrientation() == Orientation.OUEST)) || (ca.getForme() == Forme.TE && (ca.getOrientation() == Orientation.EST || ca.getOrientation() == Orientation.NORD || ca.getOrientation() == Orientation.SUD))) && (cb.getForme() == Forme.COUDE && (cb.getOrientation() == Orientation.OUEST && cb.getOrientation() == Orientation.SUD)) || (cb.getForme() == Forme.DROIT && (cb.getOrientation() == Orientation.EST || cb.getOrientation() == Orientation.OUEST)) || (cb.getForme() == Forme.TE && (cb.getOrientation() == Orientation.OUEST || cb.getOrientation() == Orientation.NORD || cb.getOrientation() == Orientation.SUD)));
        else if(b-7==a) return (((ca.getForme() == Forme.COUDE && (ca.getOrientation() == Orientation.EST && ca.getOrientation() == Orientation.SUD)) || (ca.getForme() == Forme.DROIT && (ca.getOrientation() == Orientation.SUD || ca.getOrientation() == Orientation.NORD)) || (ca.getForme() == Forme.TE && (ca.getOrientation() == Orientation.EST || ca.getOrientation() == Orientation.OUEST || ca.getOrientation() == Orientation.SUD))) && (cb.getForme() == Forme.COUDE && (cb.getOrientation() == Orientation.OUEST || cb.getOrientation() == Orientation.NORD)) || (cb.getForme() == Forme.DROIT && (cb.getOrientation() == Orientation.SUD || cb.getOrientation() == Orientation.NORD)) || (cb.getForme() == Forme.TE && (cb.getOrientation() == Orientation.EST || cb.getOrientation() == Orientation.OUEST || cb.getOrientation() == Orientation.NORD))));
        return false;
    }
    private boolean[][] matriceAdjacence(){
        boolean[][] m = new boolean[49][49];
        for(int a = 0;a < 49; a++)
            for(int b = 0;b < 49; b++)
                m[a][b] = estAdjacent(a, b);
        return m;
    }
    private boolean parcoursLargeur(int ox,int dx,int dy){
        boolean[][] m = this.matriceAdjacence();
        for(int y=0;y < 49; y++){
            if(m[ox][y]){
                if(ox==dx && y==dy) return true;
                if(parcoursLargeur(y, dx, dy)) return true;
            }
        }
        return false;
    } 
    public boolean estAtteignable(Position orig, Position dest) {
        return parcoursLargeur(orig.getX(), dest.getX(), dest.getY());
    }

    public Objectif deplacer(Position pos, Pion pion) {
        this.couloirs[pos.getX()][pos.getY()].setPions(pion);
        return this.couloirs[pos.getX()][pos.getY()].getObjectif();
    }
    
}

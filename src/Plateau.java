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

    public boolean estAtteignable(Position orig, Position dest) {
        boolean result =false;
        Couloir c,c1;
        Position p;
        Stack<Position> ps = new Stack<Position>();
        Stack<Couloir> vCouloirs = new Stack<Couloir>();
        ps.push(orig);
        while (!ps.empty() && !result) {
            p = ps.pop();
            result = p.equals(dest);
            if (!result){
                c = this.couloirs[p.getX()][p.getY()];
                vCouloirs.push(c);
                // pour le Nord
                if ((c.getForme() == Forme.COUDE && (c.getOrientation() == Orientation.OUEST || c.getOrientation() == Orientation.NORD)) || (c.getForme() == Forme.DROIT && (c.getOrientation() == Orientation.SUD || c.getOrientation() == Orientation.NORD)) || (c.getForme() == Forme.TE && (c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST || c.getOrientation() == Orientation.NORD))){
                    if (p.getY()-1 >= 0){
                        c1 = this.couloirs[p.getX()][p.getY()-1];
                        if (vCouloirs.search(c1) == -1 && (c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.EST && c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.SUD || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.TE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.SUD))){
                            ps.push(new Position(p.getX(), p.getY()-1));
                        }
                    }
                }
 
                // pour le sud
                if ((c.getForme() == Forme.COUDE && (c.getOrientation() == Orientation.EST && c.getOrientation() == Orientation.SUD)) || (c.getForme() == Forme.DROIT && (c.getOrientation() == Orientation.SUD || c.getOrientation() == Orientation.NORD)) || (c.getForme() == Forme.TE && (c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST || c.getOrientation() == Orientation.SUD))){
                    if(p.getY()+1 < 7){
                        c1 = this.couloirs[p.getX()][p.getY()+1];
                        if (vCouloirs.search(c1) == -1 && (c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.SUD || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.TE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.NORD))){
                            ps.push(new Position(p.getX(), p.getY()+1));
                        }
                    }
                }
                
                // pour l'est
                if ((c.getForme() == Forme.COUDE && (c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.NORD)) || (c.getForme() == Forme.DROIT && (c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST)) || (c.getForme() == Forme.TE && (c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.NORD || c.getOrientation() == Orientation.SUD))){
                    if(p.getX()-1 >=0){
                        c1 = this.couloirs[p.getX()-1][p.getY()];
                        if (vCouloirs.search(c1) == -1 && (c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST && c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.SUD))){
                            ps.push(new Position(p.getX()-1 , p.getY()));
                        }
                    }
                }

                // pour l'ouest
                if ((c.getForme() == Forme.COUDE && (c.getOrientation() == Orientation.OUEST && c.getOrientation() == Orientation.SUD)) || (c.getForme() == Forme.DROIT && (c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST)) || (c.getForme() == Forme.TE && (c.getOrientation() == Orientation.OUEST || c.getOrientation() == Orientation.NORD || c.getOrientation() == Orientation.SUD))){
                    if(p.getX()+1 < 7){
                        c1 = this.couloirs[p.getX()+1][p.getY()];
                        if (vCouloirs.search(c1) == -1 && (c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.SUD))){
                            ps.push(new Position(p.getX()+1 , p.getY()));
                        }
                    }
                }
            }
        }
        return result;
    }

    public Objectif deplacer(Position pos, Pion pion) {
        this.couloirs[pos.getX()][pos.getY()].setPions(pion);
        return this.couloirs[pos.getX()][pos.getY()].getObjectif();
    }
    
}

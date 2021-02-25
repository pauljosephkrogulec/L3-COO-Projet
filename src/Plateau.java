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
        boolean result =false;
        Couloir c = this.couloirs[orig.getX()][orig.getY()],c1,c2,c3;
        boolean sens1,sens2,sens3;
        Pile p = new Pile();
        if (c.getForme() == Forme.COUDE){
            if( c.getOrientation() == Orientation.EST){
                p.empiler(this.couloirs[orig.getX()+1][orig.getY()]);
                p.empiler(this.couloirs[orig.getX()][orig.getY()+1]);
                
            } else if (c.getOrientation() == Orientation.OUEST){
                p.empiler(this.couloirs[orig.getX()-1][orig.getY()]);
                p.empiler(this.couloirs[orig.getX()][orig.getY()-1]);
                
            } else if (c.getOrientation() == Orientation.SUD){
                p.empiler(this.couloirs[orig.getX()][orig.getY()+1]);
                p.empiler(this.couloirs[orig.getX()-1][orig.getY()]);
                
            } else {
                p.empiler(this.couloirs[orig.getX()][orig.getY()-1]);
                p.empiler(this.couloirs[orig.getX()+1][orig.getY()]);
                
            }
        } else if (c.getForme() == Forme.DROIT){
            if (c.getOrientation() == Orientation.EST){
                p.empiler(this.couloirs[orig.getX()+1][orig.getY()]);
                p.empiler(this.couloirs[orig.getX()-1][orig.getY()]);
                
            } else if (c.getOrientation() == Orientation.OUEST){
                p.empiler(this.couloirs[orig.getX()+1][orig.getY()]);
                p.empiler(this.couloirs[orig.getX()-1][orig.getY()]);
            } else if (c.getOrientation() == Orientation.SUD) {
                p.empiler(this.couloirs[orig.getX()][orig.getY()+1]);
                p.empiler(this.couloirs[orig.getX()][orig.getY()-1]);
            } else {
                p.empiler(this.couloirs[orig.getX()][orig.getY()+1]);
                p.empiler(this.couloirs[orig.getX()][orig.getY()-1]);
            }
        } else {
            if (c.getOrientation() == Orientation.EST){
                p.empiler(this.couloirs[orig.getX()][orig.getY()-1]);
                p.empiler(this.couloirs[orig.getX()+1][orig.getY()]);
                p.empiler(this.couloirs[orig.getX()][orig.getY()+1]);
                
            } else if (c.getOrientation() == Orientation.OUEST){
                p.empiler(this.couloirs[orig.getX()-1][orig.getY()]);
                p.empiler(this.couloirs[orig.getX()][orig.getY()-1]);
                p.empiler(this.couloirs[orig.getX()][orig.getY()+1]);
            
            } else if (c.getOrientation() == Orientation.SUD){
                p.empiler(this.couloirs[orig.getX()+1][orig.getY()]);
                p.empiler(this.couloirs[orig.getX()][orig.getY()+1]);
                p.empiler(this.couloirs[orig.getX()-1][orig.getY()]);
                

            } else {
                p.empiler(this.couloirs[orig.getX()-1][orig.getY()]);
                p.empiler(this.couloirs[orig.getX()][orig.getY()-1]);
                p.empiler(this.couloirs[orig.getX()+1][orig.getY()]);
            }
        }
        
        while (!result && p.estVide()){
            c = p.dépiler();
            if (c.getForme() == Forme.COUDE){
                if( c.getOrientation() == Orientation.EST){
                    c1 = this.couloirs[orig.getX()+1][orig.getY()];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.EST));
                    c2 = this.couloirs[orig.getX()][orig.getY()+1];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.OUEST || c2.getOrientation() == Orientation.NORD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.NORD || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.SUD));                
                } else if (c.getOrientation() == Orientation.OUEST){
                    c1 = this.couloirs[orig.getX()-1][orig.getY()];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.OUEST));
                    c2 = this.couloirs[orig.getX()][orig.getY()-1];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.NORD || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.NORD));                
                } else if (c.getOrientation() == Orientation.SUD){
                    c1 = this.couloirs[orig.getX()][orig.getY()+1];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.SUD));
                    c2 = this.couloirs[orig.getX()-1][orig.getY()];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.NORD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.OUEST)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.OUEST));                
                } else {
                    c1 = this.couloirs[orig.getX()][orig.getY()-1];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.NORD));
                    c2 = this.couloirs[orig.getX()+1][orig.getY()];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.OUEST || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.OUEST || c2.getOrientation() == Orientation.EST)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.EST));                
                }
                if (sens1) p.empiler(c1);
                if (sens2) p.empiler(c2);
            } else if (c.getForme() == Forme.DROIT){
                if (c.getOrientation() == Orientation.EST){
                    c1 = this.couloirs[orig.getX()+1][orig.getY()];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.EST));
                    c2 = this.couloirs[orig.getX()-1][orig.getY()];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.NORD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.OUEST)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.OUEST));
                } else if (c.getOrientation() == Orientation.OUEST){
                    c1 = this.couloirs[orig.getX()+1][orig.getY()];
                    sens2 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.EST));
                    c2 = this.couloirs[orig.getX()-1][orig.getY()];
                    sens1 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.NORD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.OUEST)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.OUEST));
                } else if (c.getOrientation() == Orientation.SUD) {
                    c1 = this.couloirs[orig.getX()][orig.getY()+1];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.NORD));
                    c2 = this.couloirs[orig.getX()][orig.getY()-1];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.NORD || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.SUD));
                } else {
                    c1 = this.couloirs[orig.getX()][orig.getY()+1];
                    sens2 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.NORD));
                    c2 = this.couloirs[orig.getX()][orig.getY()-1];
                    sens1 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.NORD || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.SUD));
                }
                if (sens1) p.empiler(c1);
                if (sens2) p.empiler(c2);
            } else {
                if (c.getOrientation() == Orientation.EST){
                    c1 = this.couloirs[orig.getX()][orig.getY()-1];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.NORD));
                    c2 = this.couloirs[orig.getX()+1][orig.getY()];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.OUEST || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.OUEST || c2.getOrientation() == Orientation.EST)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.EST));                
                    c3 = this.couloirs[orig.getX()][orig.getY()+1];
                    sens3 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.SUD));
                } else if (c.getOrientation() == Orientation.OUEST){
                    c1 = this.couloirs[orig.getX()-1][orig.getY()];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.OUEST));
                    c2 = this.couloirs[orig.getX()][orig.getY()-1];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.NORD || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.NORD));                
                    c3 = this.couloirs[orig.getX()][orig.getY()+1];
                    sens3 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.SUD));
                } else if (c.getOrientation() == Orientation.SUD){
                    c1 = this.couloirs[orig.getX()+1][orig.getY()];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.SUD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.EST));
                    c2 = this.couloirs[orig.getX()][orig.getY()+1];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.OUEST || c2.getOrientation() == Orientation.NORD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.NORD || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.SUD));                
                    c3 = this.couloirs[orig.getX()-1][orig.getY()];
                    sens3 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.NORD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.OUEST)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.OUEST));                
                } else {
                    c1 = this.couloirs[orig.getX()-1][orig.getY()];
                    sens1 = ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && c1.getOrientation() != Orientation.OUEST));
                    c2 = this.couloirs[orig.getX()][orig.getY()-1];
                    sens2 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.EST || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.NORD || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.NORD));                
                    c3 = this.couloirs[orig.getX()+1][orig.getY()];
                    sens3 = ((c2.getForme() == Forme.COUDE && (c2.getOrientation() == Orientation.OUEST || c2.getOrientation() == Orientation.SUD)) || (c2.getForme() == Forme.DROIT && (c2.getOrientation() == Orientation.OUEST || c2.getOrientation() == Orientation.EST)) || (c2.getForme() == Forme.TE && c2.getOrientation() != Orientation.EST));                
                }
                if (sens1) p.empiler(c1);
                if (sens2) p.empiler(c2);
                if (sens3) p.empiler(c3);}
        }
        return result;
    }

    public Objectif deplacer(Position pos, Pion pion) {
        this.couloirs[pos.getX()][pos.getY()].setPions(pion);
        return this.couloirs[pos.getX()][pos.getY()].getObjectif();
    }
    
}

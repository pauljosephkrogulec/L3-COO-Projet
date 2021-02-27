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
        Couloir c=this.couloirs[orig.getX()][orig.getY()],c1,c2,c3;
        boolean sens1,sens2,sens3;
        Pile p=new Pile();
        if (c.getForme()==Forme.COUDE){
            if( c.getOrientation()==Orientation.EST){
                p.empiler(new Position(orig.getX()+1,orig.getY()));
                p.empiler(new Position(orig.getX(),orig.getY()+1));
            } else if (c.getOrientation()==Orientation.OUEST){
                p.empiler(new Position(orig.getX()-1,orig.getY()));
                p.empiler(new Position(orig.getX(),orig.getY()-1));
            } else if (c.getOrientation()==Orientation.SUD){
                p.empiler(new Position(orig.getX(),orig.getY()+1));
                p.empiler(new Position(orig.getX()-1,orig.getY()));
            } else {
                p.empiler(new Position(orig.getX(),orig.getY()-1));
                p.empiler(new Position(orig.getX()+1,orig.getY()));
            }
        } else if (c.getForme()==Forme.DROIT){
            if (c.getOrientation()==Orientation.EST){
                p.empiler(new Position(orig.getX()+1,orig.getY()));
                p.empiler(new Position(orig.getX()-1,orig.getY()));
            } else if (c.getOrientation()==Orientation.OUEST){
                p.empiler(new Position(orig.getX()+1,orig.getY()));
                p.empiler(new Position(orig.getX()-1,orig.getY()));
            } else if (c.getOrientation()==Orientation.SUD) {
                p.empiler(new Position(orig.getX(),orig.getY()+1));
                p.empiler(new Position(orig.getX(),orig.getY()-1));
            } else {
                p.empiler(new Position(orig.getX(),orig.getY()+1));
                p.empiler(new Position(orig.getX(),orig.getY()-1));
            }
        } else {
            if (c.getOrientation()==Orientation.EST){
                p.empiler(new Position(orig.getX(),orig.getY()-1));
                p.empiler(new Position(orig.getX()+1,orig.getY()));
                p.empiler(new Position(orig.getX(),orig.getY()+1));
            } else if (c.getOrientation()==Orientation.OUEST){
                p.empiler(new Position(orig.getX()-1,orig.getY()));
                p.empiler(new Position(orig.getX(),orig.getY()-1));
                p.empiler(new Position(orig.getX(),orig.getY()+1));
            } else if (c.getOrientation()==Orientation.SUD){
                p.empiler(new Position(orig.getX()+1,orig.getY()));
                p.empiler(new Position(orig.getX(),orig.getY()+1));
                p.empiler(new Position(orig.getX()-1,orig.getY()));
            } else {
                p.empiler(new Position(orig.getX()-1,orig.getY()));
                p.empiler(new Position(orig.getX(),orig.getY()-1));
                p.empiler(new Position(orig.getX()+1,orig.getY()));
            }
        }
        int x1,y1,x2,y2,x3,y3;
        while (!result && p.estVide()){
            Position pos=p.dépiler();
            if (pos.getX()==dest.getX() && pos.getY()==dest.getY()) result =true;
            else {
                c=couloirs[pos.getX()][pos.getY()];
                if (c.getForme()==Forme.COUDE){
                    if( c.getOrientation()==Orientation.EST){
                        x1=1;y1=0;x2=0;y2=1;
                        c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.OUEST || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.OUEST)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.EST));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.OUEST || c2.getOrientation()==Orientation.NORD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.NORD || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.SUD));                
                    } else if (c.getOrientation()==Orientation.OUEST){
                        x1=-1;y1=0;x2=0;y2=-1;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.NORD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.OUEST)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.OUEST));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.NORD || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.NORD));                
                    } else if (c.getOrientation()==Orientation.SUD){
                        x1=0;y1=1;x2=-1;y2=0;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.OUEST || c1.getOrientation()==Orientation.NORD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.NORD || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.SUD));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.NORD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.OUEST)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.OUEST));                
                    } else {
                        x1=0;y1=-1;x2=1;y2=0;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.NORD || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.NORD));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.OUEST || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.OUEST || c2.getOrientation()==Orientation.EST)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.EST));                
                    }
                    if (sens1) p.empiler(new Position(pos.getX()+x1, pos.getY()+y1));
                    if (sens2) p.empiler(new Position(pos.getX()+x2, pos.getY()+y2));
                } else if (c.getForme()==Forme.DROIT){
                    if (c.getOrientation()==Orientation.EST){
                        x1=1;y1=0;x2=-1;y2=0;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.OUEST || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.OUEST)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.EST));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.NORD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.OUEST)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.OUEST));
                    } else if (c.getOrientation()==Orientation.OUEST){
                        x1=-1;y1=0;x2=1;y2=1;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens2=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.OUEST || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.OUEST)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.EST));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens1=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.NORD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.OUEST)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.OUEST));
                    } else if (c.getOrientation()==Orientation.SUD) {
                        x1=0;y1=1;x2=0;y2=-1;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.OUEST || c1.getOrientation()==Orientation.NORD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.NORD || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.NORD));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.NORD || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.SUD));
                    } else {
                        x1=0;y1=-1;x2=0;y2=1;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens2=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.OUEST || c1.getOrientation()==Orientation.NORD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.NORD || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.NORD));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens1=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.NORD || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.SUD));
                    }
                    if (sens1) p.empiler(new Position(pos.getX()+x1, pos.getY()+y1));
                    if (sens2) p.empiler(new Position(pos.getX()+x2, pos.getY()+y2));
                } else {
                    if (c.getOrientation()==Orientation.EST){
                        x1=0;y1=-1;x2=1;y2=0;x3=0;y3=1;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.NORD || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.NORD));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.OUEST || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.OUEST || c2.getOrientation()==Orientation.EST)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.EST));                
                        c3=this.couloirs[pos.getX()+x3][pos.getY()+y3];                          
                        sens3=((c3.getForme()==Forme.COUDE && (c3.getOrientation()==Orientation.OUEST || c3.getOrientation()==Orientation.NORD)) || (c3.getForme()==Forme.DROIT && (c3.getOrientation()==Orientation.NORD || c3.getOrientation()==Orientation.SUD)) || (c3.getForme()==Forme.TE && c3.getOrientation() != Orientation.SUD));
                    } else if (c.getOrientation()==Orientation.OUEST){
                        x1=-1;y1=0;x2=0;y2=-1;x3=0;y3=1;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.NORD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.OUEST)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.OUEST));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.NORD || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.NORD));                
                        c3=this.couloirs[pos.getX()+x3][pos.getY()+y3];
                        sens3=((c3.getForme()==Forme.COUDE && (c3.getOrientation()==Orientation.OUEST || c3.getOrientation()==Orientation.NORD)) || (c3.getForme()==Forme.DROIT && (c3.getOrientation()==Orientation.NORD || c3.getOrientation()==Orientation.SUD)) || (c3.getForme()==Forme.TE && c3.getOrientation() != Orientation.SUD));
                    } else if (c.getOrientation()==Orientation.SUD){
                        x1=1;y1=0;x2=0;y2=1;x3=-1;y3=0;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.OUEST || c1.getOrientation()==Orientation.SUD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.OUEST)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.EST));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.OUEST || c2.getOrientation()==Orientation.NORD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.NORD || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.SUD));                
                        c3=this.couloirs[pos.getX()+x3][pos.getY()+y3];
                        sens3=((c3.getForme()==Forme.COUDE && (c3.getOrientation()==Orientation.EST || c3.getOrientation()==Orientation.NORD)) || (c3.getForme()==Forme.DROIT && (c3.getOrientation()==Orientation.EST || c3.getOrientation()==Orientation.OUEST)) || (c3.getForme()==Forme.TE && c3.getOrientation() != Orientation.OUEST));                
                    } else {
                        x1=-1;y1=0;x2=0;y2=1;x3=1;y3=0;c1=this.couloirs[pos.getX()+x1][pos.getY()+y1];
                        sens1=((c1.getForme()==Forme.COUDE && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.NORD)) || (c1.getForme()==Forme.DROIT && (c1.getOrientation()==Orientation.EST || c1.getOrientation()==Orientation.OUEST)) || (c1.getForme()==Forme.TE && c1.getOrientation() != Orientation.OUEST));
                        c2=this.couloirs[pos.getX()+x2][pos.getY()+y2];
                        sens2=((c2.getForme()==Forme.COUDE && (c2.getOrientation()==Orientation.EST || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.DROIT && (c2.getOrientation()==Orientation.NORD || c2.getOrientation()==Orientation.SUD)) || (c2.getForme()==Forme.TE && c2.getOrientation() != Orientation.NORD));                
                        c3=this.couloirs[pos.getX()+x3][pos.getY()+y3];
                        sens3=((c3.getForme()==Forme.COUDE && (c3.getOrientation()==Orientation.OUEST || c3.getOrientation()==Orientation.SUD)) || (c3.getForme()==Forme.DROIT && (c3.getOrientation()==Orientation.OUEST || c3.getOrientation()==Orientation.EST)) || (c3.getForme()==Forme.TE && c3.getOrientation() != Orientation.EST));                
                    }
                    if (sens1) p.empiler(new Position(pos.getX()+x1, pos.getY()+y1));
                    if (sens2) p.empiler(new Position(pos.getX()+x2, pos.getY()+y2));
                    if (sens3) p.empiler(new Position(pos.getX()+x3, pos.getY()+y3));
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

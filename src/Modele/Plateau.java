package Modele;
import java.util.Random;
import java.util.Stack;

public class Plateau {
    private Couloir[][] couloirs;

    public Plateau(Couloir[] CouloirMobile) {
        CouloirMobile = RandomizeArray((CouloirMobile));
        this.couloirs = new Couloir[7][7];
        int x=0;
        Random r = new Random();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    if(i == 0 && j == 0)  this.couloirs[i][j] = new CouloirFixe(Orientation.EST, Forme.COUDE, null,new Position(i,j));
                    else if(i == 0 && j == 6) this.couloirs[i][j] = new CouloirFixe(Orientation.SUD, Forme.COUDE, null,new Position(i,j));
                    else if (i == 6 && j == 0)  this.couloirs[i][j] = new CouloirFixe(Orientation.NORD, Forme.COUDE, null,new Position(i,j));
                    else if (i == 6 && j == 6)  this.couloirs[i][j] = new CouloirFixe(Orientation.OUEST, Forme.COUDE, null,new Position(i,j));
                    else if (i == 0)  this.couloirs[i][j] = new CouloirFixe(Orientation.SUD, Forme.TE, null,new Position(i,j));
                    else if (j == 0)  this.couloirs[i][j] = new CouloirFixe(Orientation.EST, Forme.TE, null,new Position(i,j));
                    else if (i == 6)  this.couloirs[i][j] = new CouloirFixe(Orientation.NORD, Forme.TE, null,new Position(i,j));
                    else if (j == 6)  this.couloirs[i][j] = new CouloirFixe(Orientation.OUEST, Forme.TE, null,new Position(i,j));
                    else this.couloirs[i][j] = new CouloirFixe(Orientation.values()[r.nextInt(4)], Forme.TE, null,new Position(i,j));
                } else {
                    this.couloirs[i][j] = CouloirMobile[x++];
                }
            }
        }
    }

    public Couloir[][] getCouloirs(){
        return this.couloirs;
    }
    
    public CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c) {
        Position position = pos.getPos();
        Position oPosition = pos.oppose().getPos();
        CouloirMobile SUP = (CouloirMobile) this.couloirs[oPosition.getX()][oPosition.getY()];
        int x;
        for (Pion p : SUP.getPions()) {
            PionImpl pi = (PionImpl) p;
            pi.setPosX(position.getX());
            pi.setPosY(position.getY());
        }
        if (position.getX() == 0)
            for (x = 6; x > 0; x--){
                this.couloirs[position.getX() + x][position.getY()] = this.couloirs[position.getX() + (x-1)][position.getY()];
                for (Pion p : this.couloirs[position.getX() + (x-1)][position.getY()].getPions()) {
                    PionImpl pi = (PionImpl) p;
                    pi.setPosX(position.getX() + x);
                }
            }
        else if (position.getY() == 0)
            for (x = 6; x > 0; x--){
                this.couloirs[position.getX()][position.getY() + x] = this.couloirs[position.getX()][position.getY() + (x-1)];
                for (Pion p : this.couloirs[position.getX()][position.getY() + (x-1)].getPions()) {
                    PionImpl pi = (PionImpl) p;
                    pi.setPosY(position.getY() + x);
                }
            }
        else if (position.getX() == 6)
            for (x = 6; x > 0; x--){
                this.couloirs[position.getX() - x][position.getY()] = this.couloirs[position.getX() - (x-1)][position.getY()];
                for (Pion p : this.couloirs[position.getX() - (x-1)][position.getY()].getPions()) {
                    PionImpl pi = (PionImpl) p;
                    pi.setPosX(position.getX() - x);
                }
            }
        else
            for (x = 6; x > 0; x--){
                this.couloirs[position.getX()][position.getY() - x] = this.couloirs[position.getX()][position.getY() - (x-1)];
                for (Pion p : this.couloirs[position.getX()][position.getY() - (x-1)].getPions()) {
                    PionImpl pi = (PionImpl) p;
                    pi.setPosX(position.getX() - x);
                }
            }
        this.couloirs[position.getX()][position.getY()] = c;
        return SUP;
    }


    public boolean estAtteignable(Position orig, Position dest) {
        Couloir c,c1; 
        Position p; 
        Stack<Position> ps = new Stack<Position>(); 
        Stack<Position> vPos = new Stack<Position>(); 
        boolean result = false;
        ps.push(orig); 
        while (!ps.empty() && !result) { 
            p = ps.pop(); 
            result = p.equals(dest); 
            if (!result){ 
                if (!vPos.contains(p)){
                    vPos.push(p); 
                    c = couloirs[p.getX()][p.getY()];
                    if((c.getForme() == Forme.COUDE && ( c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.SUD))  || (c.getForme() == Forme.DROIT && ( c.getOrientation() == Orientation.SUD || c.getOrientation() == Orientation.NORD)) || (c.getForme() == Forme.TE && ( c.getOrientation() == Orientation.SUD || c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST))){
                        c1 = p.getX()+1 < 7 ? couloirs[p.getX()+1][p.getY()] : null;             
                        if(c1 != null && ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.NORD))  || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.SUD || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.TE && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST))))
                           ps.push(new Position(p.getX()+1, p.getY()));
                    } 
                    if((c.getForme() == Forme.COUDE && ( c.getOrientation() == Orientation.OUEST || c.getOrientation() == Orientation.NORD))  || (c.getForme() == Forme.DROIT && ( c.getOrientation() == Orientation.SUD || c.getOrientation() == Orientation.NORD)) || (c.getForme() == Forme.TE && ( c.getOrientation() == Orientation.NORD || c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST))){
                        c1 = p.getX()-1 >= 0 ? couloirs[p.getX()-1][p.getY()] : null;
                        if(c1 != null && ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.SUD))  || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.SUD || c1.getOrientation() == Orientation.NORD)) || (c1.getForme() == Forme.TE && (c1.getOrientation() == Orientation.SUD || c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST))))
                            ps.push(new Position(p.getX()-1, p.getY()));
                    } 
                    if((c.getForme() == Forme.COUDE && ( c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.NORD))  || (c.getForme() == Forme.DROIT && ( c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST)) || (c.getForme() == Forme.TE && ( c.getOrientation() == Orientation.NORD || c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.SUD))){
                        c1 = p.getY()+1 < 7 ? couloirs[p.getX()][p.getY()+1] : null;
                        if(c1 != null && ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.SUD || c1.getOrientation() == Orientation.OUEST))  || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.OUEST || c1.getOrientation() == Orientation.SUD))))
                            ps.push(new Position(p.getX(), p.getY()+1));
                    } 
                    if((c.getForme() == Forme.COUDE && ( c.getOrientation() == Orientation.SUD || c.getOrientation() == Orientation.OUEST))  || (c.getForme() == Forme.DROIT && ( c.getOrientation() == Orientation.EST || c.getOrientation() == Orientation.OUEST)) || (c.getForme() == Forme.TE && ( c.getOrientation() == Orientation.NORD || c.getOrientation() == Orientation.OUEST || c.getOrientation() == Orientation.SUD))){
                        c1 = p.getY()-1 >= 0 ? couloirs[p.getX()][p.getY()-1] : null;
                        if(c1 != null && ((c1.getForme() == Forme.COUDE && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.NORD))  || (c1.getForme() == Forme.DROIT && (c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.OUEST)) || (c1.getForme() == Forme.TE && (c1.getOrientation() == Orientation.NORD || c1.getOrientation() == Orientation.EST || c1.getOrientation() == Orientation.SUD))))
                            ps.push(new Position(p.getX(), p.getY()-1));
                    }
                }
            }
            c = null;
            c1 = null;
        }
        return result;
    }

    public Objectif deplacer(Position orig,Position pos, Pion pion) {
        this.couloirs[orig.getX()][orig.getY()].delPion(pion);
        this.couloirs[pos.getX()][pos.getY()].setPion(pion);
        return this.couloirs[pos.getX()][pos.getY()].getObjectif();
    }

    public static Couloir[] RandomizeArray(Couloir[] couloirMobile) {
        for(int x = 0;x < 10;x++){
            Random rgen = new Random();
            for (int i = 0; i < couloirMobile.length; i++) {
                int randomPosition = rgen.nextInt(couloirMobile.length);
                Couloir temp = couloirMobile[i];
                couloirMobile[i] = couloirMobile[randomPosition];
                couloirMobile[randomPosition] = temp;
            }
        }

        return couloirMobile;
    }
}


package Modele;
import java.util.ArrayDeque;
import java.util.Random;

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
        if (position.getX() == 0)
            for (x = 6; x > 0; x--)
                this.couloirs[position.getX() + x][position.getY()] = this.couloirs[position.getX() + (x-1)][position.getY()];
        else if (position.getY() == 0)
            for (x = 6; x > 0; x--)
                this.couloirs[position.getX()][position.getY() + x] = this.couloirs[position.getX()][position.getY() + (x-1)];
        else if (position.getX() == 6)
            for (x = 6; x > 0; x--)
                this.couloirs[position.getX() - x][position.getY()] = this.couloirs[position.getX() - (x-1)][position.getY()];
        else
            for (x = 6; x > 0; x--)
                this.couloirs[position.getX()][position.getY() - x] = this.couloirs[position.getX()][position.getY() - (x-1)];
        this.couloirs[position.getX()][position.getY()] = c;
        return SUP;
    }

    private boolean estAdjacent(int a, int b) {
        if (a > b)
            return estAdjacent(b, a);
        Couloir ca, cb;
        if (b - a == 1) {
            ca = this.couloirs[a / 7][a % 7];
            cb = this.couloirs[b / 7][b % 7];
            return (((ca.getForme() == Forme.COUDE
                    && (ca.getOrientation() == Orientation.EST || ca.getOrientation() == Orientation.NORD))
                    || (ca.getForme() == Forme.DROIT
                            && (ca.getOrientation() == Orientation.EST || ca.getOrientation() == Orientation.OUEST))
                    || (ca.getForme() == Forme.TE && (ca.getOrientation() == Orientation.EST
                            || ca.getOrientation() == Orientation.NORD || ca.getOrientation() == Orientation.SUD)))
                    && (cb.getForme() == Forme.COUDE
                            && (cb.getOrientation() == Orientation.OUEST && cb.getOrientation() == Orientation.SUD))
                    || (cb.getForme() == Forme.DROIT
                            && (cb.getOrientation() == Orientation.EST || cb.getOrientation() == Orientation.OUEST))
                    || (cb.getForme() == Forme.TE && (cb.getOrientation() == Orientation.OUEST
                            || cb.getOrientation() == Orientation.NORD || cb.getOrientation() == Orientation.SUD)));
        }
        else if (b - 7 == a) {
            ca = this.couloirs[a / 7][a % 7];
            cb = this.couloirs[b / 7][b % 7];
            return (((ca.getForme() == Forme.COUDE && (ca.getOrientation() == Orientation.EST && ca.getOrientation() == Orientation.SUD))
                    || (ca.getForme() == Forme.DROIT && (ca.getOrientation() == Orientation.SUD || ca.getOrientation() == Orientation.NORD))
                    || (ca.getForme() == Forme.TE && (ca.getOrientation() == Orientation.EST || ca.getOrientation() == Orientation.OUEST || ca.getOrientation() == Orientation.SUD)))
                    && 
                    (cb.getForme() == Forme.COUDE && (cb.getOrientation() == Orientation.OUEST || cb.getOrientation() == Orientation.NORD))
                    || (cb.getForme() == Forme.DROIT && (cb.getOrientation() == Orientation.SUD || cb.getOrientation() == Orientation.NORD))
                    || (cb.getForme() == Forme.TE && (cb.getOrientation() == Orientation.EST || cb.getOrientation() == Orientation.OUEST || cb.getOrientation() == Orientation.NORD)));
        }
        return false;
    }

    public boolean[][] matriceAdjacence() {
        boolean[][] m = new boolean[49][49];
        for (int a = 0; a < 49; a++)
            for (int b = 0; b < 49; b++)
                m[a][b] = estAdjacent(a, b);
        return m;
    }

    public boolean estAtteignable(Position orig, Position dest) {
        ArrayDeque<Integer> pos = new ArrayDeque<>();
        ArrayDeque<Integer> vPos = new ArrayDeque<>();
        int x;
        pos.add(orig.getX());
        boolean[][] m = this.matriceAdjacence();
        while(pos.size() != 0){
            x = pos.pollFirst();
            vPos.add(x);
            for(int y = 0; y<49;y+=1){
                if(m[x][y]){
                    if (!vPos.contains(y)) pos.add(y);
                    if (y == (dest.getY() + (dest.getX() * 7))) return true; 
                }
            }
        }
        return false;
    }

    public Objectif deplacer(Position pos, Pion pion) {
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


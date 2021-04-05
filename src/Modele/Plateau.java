package modele;

// On importe les librairies..
import java.util.Random;
import java.util.Stack;

/** Classe qui modélise un plateau du jeu..
 */
public class Plateau {

    // On définie la variable contenant la matrice du couloir.
    private Couloir[][] couloirs;

    /** Constructeur de la classe Plateau qui prend en paramètre un tableau de couloir mobile,
     * et crée la matrice du plateau.
     * @param CouloirMobile : tableau contenant les couloirs à placer.
     */
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

    /** Méthode qui renvoie la matrice des couloirs.
     * @return : les couloirs.
     */
    public Couloir[][] getCouloirs() {
        return this.couloirs;
    }
    
    /** Méthode qui prenc en paramètre une position d'insertion et un couloir,
     * et va insérer ce couloir à la position correspondante.
     * @param pos : la position où insérer le couloir.
     * @param c : le couloir à insérer.
     * @return : le nouveau supplémentaire.
     */
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
        for(Pion p : SUP.getPions()){
            c.setPion(p);
            PionImpl pi = (PionImpl) p;
            pi.setPosX(position.getX());
            pi.setPosY(position.getY());
        }
        SUP.videPion();
        this.couloirs[position.getX()][position.getY()] = c;
        return SUP;
    }


    /** Méthode qui prend en paramètre deux positions et vérifie si la position voulu est atteignable depuis celle d'origine.
     * @param orig : la position d'origine.
     * @param dest : la position à atteindre.
     * @return : Vrai si elle est atteigable, faux sinon.
     */
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

    /** Méthode qui prend en paramètre deux positions et un pion,
     * et va déplacer le pion de la position d'origine, à la position à atteindre.
     * @param orig : la position d'origine.
     * @param pos : la position finale.
     * @param pion : le pion à déplacer.
     * @return : l'objectif présent sur le couloir.
     */
    public Objectif deplacer(Position orig,Position pos, Pion pion) {
        this.couloirs[orig.getX()][orig.getY()].delPion(pion);
        this.couloirs[pos.getX()][pos.getY()].setPion(pion);
        return this.couloirs[pos.getX()][pos.getY()].getObjectif();
    }

    /** Méthode qui va placer de manière aléatoire les couloirs.
     * @param couloirMobile : le tableau contenant les couloirs à mélanger.
     * @return : le nouveau tableau avec les couloirs mélangés.
     */
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
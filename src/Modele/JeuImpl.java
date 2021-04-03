package Modele;
// On importe les librairies.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import Vue.VJeu;

public class JeuImpl implements Jeu {

    // Déclaration des variables...
    private CouloirMobile supplementaire;
    private PositionInsertion positionOrigine;
    private List<Joueur> joueurs;
    private Map<Couleur, Pion> pions;
    private Objectif[] objectifs;
    private CouloirMobile[] couloirsMobiles;
    private Plateau plateau;
    private int i;
    private VJeu vLabyrinthe;

    public JeuImpl(VJeu vLabyrinthe) {
        this.vLabyrinthe = vLabyrinthe;
        preparer();
        this.i = 0;
    }

    @Override
    public void modifierCouloir(PositionInsertion pos, Orientation orientation) {
        assert pos != positionOrigine;
        supplementaire = plateau.modifierCouloirs(pos, supplementaire);
        positionOrigine = pos.oppose();
    }

    @Override
    public void enregistrer(Joueur joueur, Couleur couleur) {
        joueur.recevoirPion(pions.get(couleur));
    }

    @Override
    public List<Couloir> couloirs() {
        List<Couloir> cs = new ArrayList<>();
        for (Couloir[] couloirs : plateau.getCouloirs()) {
            for (Couloir c : couloirs) {
                cs.add(c);
            }
        }
        return cs;
    }

    public void preparer() {
        Random r = new Random();
        this.joueurs = new ArrayList<>();
        this.pions = new HashMap<>();
        this.objectifs = JeuImpl.randomizeArrayObjectifs(Objectif.values());
        this.supplementaire = new CouloirMobile(Orientation.SUD, Forme.DROIT, null, false,null);
        this.couloirsMobiles = new CouloirMobile[34];
        Stack<Objectif> objs;
        int i, obj = 0,cpt = 0;
        
        this.objectifs = Objectif.values();
        for (i = 0; i < 12; i++)
            this.couloirsMobiles[i] = new CouloirMobile(Orientation.values()[r.nextInt(4)], Forme.DROIT, objectifs[obj++], false,new Position(i/7,i%7));
        for (; i < 20; i++) {
            this.couloirsMobiles[i] = new CouloirMobile(Orientation.values()[r.nextInt(4)], Forme.TE,
                    objectifs[obj++], false,new Position(i/7,i%7));
        }
        for (; i < 34; i++) {
            if (i < 24)
                this.couloirsMobiles[i] = new CouloirMobile(Orientation.values()[r.nextInt(4)], Forme.COUDE,
                        objectifs[obj++], false,new Position(i/7,i%7));
            else
                this.couloirsMobiles[i] = new CouloirMobile(Orientation.values()[r.nextInt(4)], Forme.COUDE, null,
                        false,new Position(i/7,i%7));
        }

        this.plateau = new Plateau(this.couloirsMobiles);
        for (i = 0; i < 2; i++) {
            objs = new Stack<>();
            Joueur j = new JoueurImpl(14, this,vLabyrinthe);
            int x = 0, y = 0;
            if (i == 1)
                y = 6;
            else if (i == 2)
                x = 6;
            else if (i == 3) {
                x = 6;
                y = 6;
            }
            for(int c = 0;c < 5;c++){
                objs.add(Objectif.values()[cpt++]);
            }
            Pion p = new PionImpl(this.plateau, new Position(x, y), new Position(x, y), Couleur.values()[i]);
            this.pions.put(Couleur.values()[i], p);
            j.fixerObjectifs(objs);
            j.recevoirPion(p);
            this.joueurs.add(j);
            this.plateau.getCouloirs()[x][y].setPion(p);
        }
    }

    public static CouloirMobile[] RandomizeArray(CouloirMobile[] array) {
        Random rgen = new Random(); // Random number generator

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            CouloirMobile temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
    public static Objectif[] randomizeArrayObjectifs(Objectif[] array) {
        Random rgen = new Random(); // Random number generator

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            Objectif temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    public Couloir getSupplementaire() {
        return this.supplementaire;
    }

    public Joueur getJoueurs(int ind) {
        return this.joueurs.get(ind);
    }

    public Joueur prochainJoueur() {
        return joueurs.get((this.i)++ % joueurs.toArray().length);
    }

    public boolean aGagne(Joueur joueur) {
        return joueur.getPion().getPositionInitiale() == joueur.getPion().getPositionCourante()
                && joueur.objectifsFinis();
    }

}

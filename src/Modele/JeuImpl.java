package Modele;

// On importe les librairies..
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

// On importe la classe depuis la vue du jeu.
import Vue.VJeu;

/** Classe qui implémente l'interface du jeu pour modéliser le modèle du labyrinthe.
 */
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
    private VJeu jeuVue;

    /** Constructeur de la classe JeuImpl qui prend en paramètre un vue du jeu et modélise le modèle.
     * @param jeuVue > la fenêtre du jeu.
     */
    public JeuImpl(VJeu jeuVue) {
        this.jeuVue = jeuVue;
        this.i = 0;
    }

    /** Méthode qui prend en paramètre une position d'insertion et une orientation,
     * et va insérer le couloir à cette position selon l'orientation.
     * @param pos > la position où insérer le couloir.
     * @param orientation > l'orientation du supplémentaire.
     */
    @Override
    public void modifierCouloir(PositionInsertion pos, Orientation orientation) {
        assert pos != positionOrigine;
        supplementaire = plateau.modifierCouloirs(pos, supplementaire);
        positionOrigine = pos.oppose();
    }

    /** Méthode qui prend en paramètre un joueur et une couleur, et l'enregistre pour la partie.
     * @param joueur > le joueur à ajouter.
     * @param couleur > sa couleur.
     */
    @Override
    public void enregistrer(Joueur joueur, Couleur couleur) {
        joueur.recevoirPion(pions.get(couleur));
    }

    /** Méthode qui ajoute et renvoie la liste des couloirs du jeu.
     * @return : les couloirs.
     */
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

    /** Méthode qui prenc en paramètre un nombre de joueur, et prépare l'ensemble du jeu.
     * @param nbJoueurs > le nombre de joueur présent dans la partie.
     */
    public void preparer(int nbJoueurs) {
        Random r = new Random();
        this.joueurs = new ArrayList<>();
        this.pions = new HashMap<>();
        this.objectifs = JeuImpl.malangeTabbleauObjectif(Objectif.values());
        this.supplementaire = new CouloirMobile(Orientation.SUD, Forme.DROIT, null, false,null);
        this.couloirsMobiles = new CouloirMobile[34];
        Stack<Objectif> objs;
        int i, obj = 0,cpt = 0;
        
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
        for (i = 0; i < nbJoueurs; i++) {
            objs = new Stack<>();
            Joueur j = new JoueurImpl(14, jeuVue);
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
                objs.add(this.objectifs[cpt++]);
            }
            Pion p = new PionImpl(this.plateau, new Position(x, y), new Position(x, y), Couleur.values()[i]);
            this.pions.put(Couleur.values()[i], p);
            j.fixerObjectifs(objs);
            j.recevoirPion(p);
            this.joueurs.add(j);
            this.plateau.getCouloirs()[x][y].setPion(p);
        }
    }

    /** Méthode qui prend en paramètres les objectifs et les renvoies mélangés.
     * @param array > le tableau contenant les objectifs.
     * @return : le nouveau tableau d'objectifs.
     */
    private static Objectif[] malangeTabbleauObjectif(Objectif[] array) {
        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rand.nextInt(array.length);
            Objectif temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    /** Méthode qui renvoie le couloir supplémentaire.
     * @return : le supplémentaire.
     */
    public Couloir getSupplementaire() {
        return this.supplementaire;
    }

    /** Méthode qui renvoie le prochain joueur qui devra jouer.
     * @return : le joueur suivant.
     */
    public Joueur prochainJoueur() {
        return joueurs.get((this.i)++ % joueurs.toArray().length);
    }

    /** Méthode qui prend en paramètre un joueur et vérifie si il a gagner la partie.
     * @param joueur > le joueur.
     * @return : Vrai si il a gagné, faux sinon.
     */
    public boolean aGagne(Joueur joueur) {
        return joueur.objectifsFinis();
    }

}

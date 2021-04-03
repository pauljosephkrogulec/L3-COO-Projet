package Modele;
// On importe les librairies.
import java.util.Stack;

import Vue.VJeu;

/** Classe qui modélise un joueur en implémentant l'interface Joueur..
 */
public class JoueurImpl implements Joueur {

    // Déclaration des variables...
    private int age;
    private Stack<Objectif> objectifs;
    private Jeu jeu;
    private Pion pion;
    private VJeu vjeu;
    private boolean finiTour;

    /** Constructeur de la classe JoueurImpl.
     * @param age > l'âge du joueur.
     * @param jeu > le jeu.
     */
    public JoueurImpl(int age, Jeu jeu, VJeu vjeu) {
        this.age = age;
        this.objectifs = new Stack<>();
        this.jeu = jeu;
        this.pion = null;
        this.vjeu = vjeu;
        this.finiTour = false;
    }

    /** Méthode qui renvoie l'âge du joueur.
     * @return : l'âge.
     */
    @Override
    public int getAge() {
        return this.age;
    }

    /** Méthode qui renvoie le pion du joueur.
     * @return : son pion.
     */
    @Override
    public Pion getPion() {
        return this.pion;
    }

    @Override
    public boolean getFiniTour() {
        return this.finiTour;
    }
    
    @Override
    public Stack<Objectif> getObjectifs() {
        return this.objectifs;
    }

    /** Méthode permettant au joueur de jouer un tour.
     */
    @Override
    public void joue(Position pos) {
        Objectif objectif = pion.deplacer(pos);
        vjeu.refresh();
        if(objectif == this.objectifs.peek()) {
            objectifs.pop();
        }
    }

    /** Méthode qui affecte des objectifs au joueur.
     * @param objectifs > une pile d'objectifs à remplir.
     */
    @Override
    public void fixerObjectifs(Stack<Objectif> objectifs) {
        this.objectifs = objectifs;
    }

    /** Méthode qui affecte un pion à un joueur.
     * @param p > le pion.
     */
    @Override
    public void recevoirPion(Pion p) {
        this.pion = p;
    }

    /** Méthode qui renvoie un boolean si les objectifs du joueur son terminés ou non.
     * @return : Vrai si les objectifs sont remplis, faux sinon.
     */
    @Override
    public boolean objectifsFinis() {
        return this.objectifs.empty();
    }
}

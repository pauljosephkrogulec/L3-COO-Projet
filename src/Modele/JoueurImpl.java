package modele;

// On importe les librairies.
import java.util.Stack;

import vue.VJeu;

/** Classe qui modélise un joueur en implémentant l'interface Joueur..
 */
public class JoueurImpl implements Joueur {

    // Déclaration des variables...
    private int age;
    private Stack<Objectif> objectifs;
    private Pion pion;
    private VJeu jeuVue;

    /** Constructeur de la classe JoueurImpl.
     * @param age : l'âge du joueur.
     * @param jeuVue : le jeu.
     */
    public JoueurImpl(int age, VJeu jeuVue) {
        this.age = age;
        this.objectifs = new Stack<>();
        this.pion = null;
        this.jeuVue = jeuVue;
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

    /** Méthode qui renvoie la pile d'objectifs du joueur.
     * @return : la pile contenant ses objectifs.
     */    
    @Override
    public Stack<Objectif> getObjectifs() {
        return this.objectifs;
    }

    /** Méthode qui prend en paramètre une position et déplace le pion du joueur.
     * On vérifie également que l'objectif sur le couloir est celui rechercher.
     * @param pos : la position à atteindre.
     */
    @Override
    public void deplacePion(Position pos) {
        Objectif objectif = pion.deplacer(pos);
        jeuVue.refresh(true);
        if(objectif == this.objectifs.peek()) {
            objectifs.pop();
        }
    }

    /** Méthode qui affecte des objectifs au joueur.
     * @param objectifs : une pile d'objectifs à remplir.
     */
    @Override
    public void fixerObjectifs(Stack<Objectif> objectifs) {
        this.objectifs = objectifs;
    }

    /** Méthode qui affecte un pion à un joueur.
     * @param p : le pion.
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

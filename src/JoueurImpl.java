// On importe les librairies.
import java.util.Scanner;
import java.util.Stack;

/** Classe qui modélise un joueur en implémentant l'interface Joueur..
 */
public class JoueurImpl implements Joueur {

    // Déclaration des variables...
    private int age;
    private Stack<Objectif> objectifs;
    private Jeu jeu;
    private Pion pion;

    /** Constructeur de la classe JoueurImpl.
     * @param age > l'âge du joueur.
     * @param jeu > le jeu.
     */
    public JoueurImpl(int age, Jeu jeu) {
        this.age = age;
        this.objectifs = new Stack<>();
        this.jeu = jeu;
        this.pion = null;
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

    /** Méthode permettant au joueur de jouer un tour.
     */
    @Override
    public void joue() {
        this.jeu.modifierCouloir(choisirPositionInsertionCouloir(), choisirOrientationCouloir());
        Objectif objectif = pion.deplacer(choisirPositionPion());
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

    /** Méthode qui permet au joueur de choisir une position pour insérer un couloir.
     * @return : la position où le couloir est inséré.
     */    
    private PositionInsertion choisirPositionInsertionCouloir() {
        return PositionInsertion.N1;
    }

    /** Méthode qui permet au joueur de choisir une position de son pion.
     * @return : la position choisie du pion.
     */ 
    private Position choisirPositionPion() {
        String x,y;
        Scanner sc = new Scanner(System.in);
        x = sc.next();
        y = sc.next();
        Position p = new Position(Integer.parseInt(x), Integer.parseInt(y));
        return p;
    }

    /** Méthode qui permet au joueur de choisir l'orientation de son couloir.
     * @return : l'orientation du couloir.
     */ 
    private Orientation choisirOrientationCouloir() {
        return Orientation.SUD;
    }
}

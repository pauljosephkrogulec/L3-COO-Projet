// On importe les librairies.
import java.util.List;

/** Classe qui modélise un couloir en implémentant l'interface Couloir..
 */
public class CouloirImpl implements Couloir {

    // Déclaration des variables...
    protected Orientation orientation;
    protected Forme forme;
    protected Objectif objectif;
    protected List<Pion> pions;

    /** Constructeur de la classe CouloirImpl.
     * @param orientation > l'orientation du couloir.
     * @param forme > sa forme.
     * @param objectif > l'objectif représenté dans le couloir.
     */
    public CouloirImpl(Orientation orientation, Forme forme, Objectif objectif) {
        this.orientation = orientation;
        this.forme = forme;
        this.objectif = objectif;
        this.pions = null;
    }

    /** Méthode qui renvoie l'orientation du couloir.
     * @return : son orientation.
     */
    @Override
    public Orientation getOrientation() {
        return this.orientation;
    }

    /** Méthode qui renvoie la forme du couloir.
     * @return : sa forme.
     */
    @Override
    public Forme getForme() {
        return this.forme;
    }

    /** Méthode qui renvoie l'objectif du couloir.
     * @return : son objectif.
     */
    @Override
    public Objectif getObjectif() {
        return this.objectif;
    }

    /** Méthode qui renvoie les pions présents sur le couloir.
     * @return : une liste de pions présents.
     */
    @Override
    public List<Pion> getPions() {
        return this.pions;
    }

    /** Méthode qui ajout un pion dans le couloir.
     * @param p > le pion d'un joueur.
     */    
    @Override
    public void setPions(Pion p){
        pions.add(p);
    }
    
}

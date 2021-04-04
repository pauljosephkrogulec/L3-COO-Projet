package Modele;

/** Classe qui modélise un couloir fixe en héritant des propriétés de CouloirImpl..
 */
public class CouloirFixe extends CouloirImpl {

    /** Constructeur de la classe CouloirFixe.
     * @param orientation > l'orientation du couloir.
     * @param forme > sa forme.
     * @param objectif > l'objectif représenté dans le couloir.
     */
    public CouloirFixe(Orientation orientation, Forme forme, Objectif objectif,Position pos) {
        super(orientation, forme, objectif,pos);
    }
    
}

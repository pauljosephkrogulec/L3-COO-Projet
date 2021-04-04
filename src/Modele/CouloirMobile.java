package Modele;

/** Classe qui modélise un couloir mobile en héritant des propriétés de CouloirImpl..
 */
public class CouloirMobile extends CouloirImpl {

    // Déclaration des variables...
    private boolean posee;

    /** Constructeur de la classe CouloirMobile.
     * @param orientation : l'orientation du couloir.
     * @param forme : sa forme.
     * @param objectif : l'objectif représenté dans le couloir.
     * @param posee : si le couloir est posé ou non.
     * @param pos : la position du couloir.
     */
    public CouloirMobile(Orientation orientation, Forme forme, Objectif objectif, boolean posee,Position pos) {
        super(orientation, forme, objectif,pos);
        this.posee = posee;
    }

    /** Méthode qui change l'orientation du couloir et renvoie un boolean si le changement a bien été effectué.
     * @param orientation : la nouvelle orientation
     * @return : Vrai si l'orientation a été changé, faux sinon
     */
    public boolean changeOrientation(Orientation orientation) {
        if(!this.posee) {
            this.orientation = orientation;
            return true;
        }
        return false;
    }
}

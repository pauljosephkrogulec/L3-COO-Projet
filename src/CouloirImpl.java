
// On importe les librairies.
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/** Classe qui modélise un couloir en implémentant l'interface Couloir..
 */
public class CouloirImpl extends JButton implements Couloir {
    private static final ImageIcon[] imgTab = {
        new ImageIcon(JeuFactory.class.getResource("img/CASE0.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE1.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE2.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE3.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE4.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE5.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE6.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE7.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE8.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE9.gif"))
    };
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
        super();
        int f,o;
        if(forme == Forme.DROIT){
            f = 0;
            if(orientation == Orientation.NORD || orientation == Orientation.SUD) o = 1;
            else o = 0;
        } else if (forme == Forme.COUDE){
            f = 2;
            if(orientation == Orientation.EST) o = 0;
            else if(orientation == Orientation.NORD) o = 1;
            else if(orientation == Orientation.OUEST) o = 2;
            else o = 3;
        } else {
            f = 6;
            if (orientation == Orientation.SUD) o = 0;
            else if(orientation == Orientation.EST) o = 1;
            else if(orientation == Orientation.NORD) o = 2;
            else o = 3;
        }
        this.setIcon(imgTab[f+o]);
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

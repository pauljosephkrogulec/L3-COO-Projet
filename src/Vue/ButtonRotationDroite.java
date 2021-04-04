package Vue;

// On importe la librairie ImageIcon..
import javax.swing.ImageIcon;

/** Classe qui herite de ButtonRotation et actualise l'image du bouton de rotation d'un couloir vers la droite.
 */
public class ButtonRotationDroite extends ButtonRotation {

    /** Constructeur de la classe ButtonRotationDroite qui met à jour l'image du bouton.
     */
    public ButtonRotationDroite() {
        this.setIcon(new ImageIcon(InsertionLeft.class.getResource("../img/rotationDroite.gif")));
    } 
}

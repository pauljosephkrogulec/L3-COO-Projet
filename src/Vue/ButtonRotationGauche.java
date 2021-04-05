package vue;

// On importe la librairie ImageIcon..
import javax.swing.ImageIcon;

/** Classe qui herite de ButtonRotation et actualise l'image du bouton de rotation d'un couloir vers la gauche.
 */
public class ButtonRotationGauche extends ButtonRotation {
    
    /** Constructeur de la classe ButtonRotationGauche qui met à jour l'image du bouton.
     */
    public ButtonRotationGauche() { 
        this.setIcon(new ImageIcon(InsertionLeft.class.getResource("/img/rotationGauche.png")));
    }
}

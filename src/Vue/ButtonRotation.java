package Vue;

// On importe la librairie JButton..
import javax.swing.JButton;

/** Classe abstraite qui herite de JButton et modélise le bouton de rotation du couloir à insérer.
 */
abstract public class ButtonRotation extends JButton {

    /** Constructeur de la class ButtonRotation qui modélise le bouton de rotation du couloir à insérer.
    */
    public ButtonRotation() {
        // On paramètre les préférences du bouton.
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }
}
package vue;

// On importe les librairies..
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

/** Classe qui crée un ButtonRadio héritant des propriétés d'un JRadioButton..
 */
public class ButtonRadio extends JRadioButton {

    /** Constructeur de la classe ButtonRadio qui permet de re-définir le JRadioButton.
     */
    public ButtonRadio() {
        // On paramètre les préférences du bouton.
        this.setFocusable(false);
        this.setSize(50, 20);
        this.setOpaque(false);
        this.setIcon(new ImageIcon(ButtonRadio.class.getResource("/img/radioIcon.png")));
        this.setSelectedIcon(new ImageIcon(ButtonRadio.class.getResource("/img/radioSelect.png")));
    }
}
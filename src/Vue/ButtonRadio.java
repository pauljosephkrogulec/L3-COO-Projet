package Vue;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

/** Classe qui crée un RadioButton héritant des propriétés d'un JRadioButton..
 */
public class ButtonRadio extends JRadioButton{

    /** Constructeur de la classe RadioButton qui permet de redéfinir le JRadioButton.
     */
    public ButtonRadio() {
        super();
        this.setFocusable(false);
        this.setSize(50, 20);
        this.setOpaque(false);
        this.setIcon(new ImageIcon(ButtonRadio.class.getResource("../img/radioIcon.png")));
        this.setSelectedIcon(new ImageIcon(ButtonRadio.class.getResource("../img/radioSelect.png")));
    }
}
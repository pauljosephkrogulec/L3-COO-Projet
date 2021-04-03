package Vue;
import javax.swing.JButton;

/** Classe qui herite de JButton et modélise le bouton Jouer avec son événement.
 */
abstract public class ButtonRotation extends JButton {

    /** Constructeur de la class btnQuitter qui prend en paramètre l'interface graphique (JFrame).
     * @param jeu : JeuMemory
    */
    public ButtonRotation() {
        super();
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }
}
package Vue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import Modele.Couloir;

/** Classe qui herite de JButton et modélise le bouton Jouer avec son événement.
 */
abstract public class ButtonRotation extends JButton {

    // On déclare la variable du JeuMemory.
    protected VJeu jeu;
    protected Couloir supp;

    /** Constructeur de la class btnQuitter qui prend en paramètre l'interface graphique (JFrame).
     * @param jeu : JeuMemory
    */
    public ButtonRotation(VJeu jeu, Couloir supp) {
        super();
        this.jeu = jeu;
        this.supp = supp;
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }
}
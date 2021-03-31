package Vue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/** Classe qui herite de JButton et modélise le bouton Quitter avec son événement..
 */
public class ButtonReduire extends JButton implements ActionListener {

    // On déclare le jeu.
    private VJeu jeu;
    /** Constructeur de la class btnQuitter.
    */
    public ButtonReduire(VJeu jeu) {
        super();
        this.jeu = jeu;
        this.setFocusable(false);
        this.setOpaque(true);
        this.setIcon(new ImageIcon(ButtonReduire.class.getResource("../img/reduce.png")));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addActionListener(this);
    }

    /** Méthode de ActionEvent qui quitte la fenêtre. 
     * @param e : l'événement.
     */
    public void actionPerformed(ActionEvent e) {
        this.jeu.setState(Frame.ICONIFIED);
    }
}

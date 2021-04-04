package Vue;

// On importe les librairies..
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/** Classe qui herite de JButton et modélise le bouton pour réduire la fenêtre avec son événement..
 */
public class ButtonReduire extends JButton implements ActionListener {

    // On déclare la variable contenant la fenêtre du jeu.
    private VJeu jeuVue;

    /** Constructeur de la class ButtonReduire qui prend en paramètre la fenêtre et met à jour les préférences de celui-ci.
     * @param jeuVue > la fenêtre du jeu.
    */
    public ButtonReduire(VJeu jeuVue) {
        // On paramètre les préférences du bouton.
        this.setFocusable(false);
        this.setOpaque(true);
        this.setIcon(new ImageIcon(ButtonReduire.class.getResource("../img/reduce.png")));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addActionListener(this);

        this.jeuVue = jeuVue;
    }

    /** Méthode de ActionEvent qui réduit la fenêtre. 
     * @param e > l'événement.
     */
    public void actionPerformed(ActionEvent e) {
        this.jeuVue.setState(Frame.ICONIFIED);
    }
}

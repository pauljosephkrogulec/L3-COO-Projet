package Vue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/** Classe qui herite de JButton et modélise le bouton Jouer avec son événement.
 */
public class ButtonJouer extends JButton implements ActionListener {

    // On déclare la variable du JeuMemory.
    private VJeu jeu;
    private boolean active;

    /** Constructeur de la class btnQuitter qui prend en paramètre l'interface graphique (JFrame).
     * @param jeu : JeuMemory
    */
    public ButtonJouer(VJeu jeu) {
        super();
        this.jeu = jeu;
        this.setFocusable(false);
        this.setIcon(new ImageIcon(ButtonJouer.class.getResource("../img/jouer.png")));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addActionListener(this);
        this.active = false;
    }

    /** Méthode qui fait varier l'image du bouton si il est actif ou non.
     * @param b : Vrai : le bouton est actif, faux sinon (boolean).
     */
    public void setEnabled(boolean b) {
        if(b) {
            this.setIcon(new ImageIcon(ButtonJouer.class.getResource("../img/jouer.png")));
            this.active = false;
        } else {
            this.setIcon(new ImageIcon(ButtonJouer.class.getResource("../img/jouerDisabled.png")));
            this.active = true;
        }
    }

    /** Méthode de ActionEvent qui appelle la méthode initPartie 
     * pour recommencer une nouvelle partie. 
     * @param e : l'événement.
     */
    public void actionPerformed(ActionEvent e) {
        if(this.active == false) {
            this.setEnabled(false);
            this.jeu.initPartie();
            this.jeu.afficheTour();
        }
    }
}
package Vue;

// On importe les librairies..
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/** Classe qui herite de JButton et modélise le bouton de fin du tour d'un joueur avec son événement..
 */
public class ButtonFinTour extends JButton implements ActionListener {

    // On déclare quelques variables..
    private VJeu jeuVue;
    private boolean active;

    /** Constructeur de la class ButtonFinTour qui prend en paramètre l'interface graphique (JFrame).
     * @param jeuVue : la vue du jeu du labyrinthe.
    */
    public ButtonFinTour(VJeu jeuVue) {
        super();
        this.jeuVue = jeuVue;
        this.setFocusable(false);
        this.setIcon(new ImageIcon(ButtonJouer.class.getResource("../img/terminerTour.png")));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addActionListener(this);
        this.active = false;
    }

    /** Méthode qui fait varier l'image du bouton si il est actif ou non.
     * @param b : Vrai : le bouton est actif, faux sinon (boolean).
     */
    public void setEnabled(boolean etat) {
        if(etat) {
            this.setIcon(new ImageIcon(ButtonJouer.class.getResource("../img/terminerTour.png")));
            this.active = false;
        } else {
            this.setIcon(new ImageIcon(ButtonJouer.class.getResource("../img/terminerTourDisabled.png")));
            this.active = true;
        }
    }

    /** Méthode de ActionEvent qui appelle la méthode initPartie 
     * pour recommencer une nouvelle partie. 
     * @param e : l'événement.
     */
    public void actionPerformed(ActionEvent e) {
        if(this.active == false) {
            this.jeuVue.joueurSuivant();
            this.jeuVue.setEtatBtnFiniTour(false);
            this.jeuVue.refresh(false);
        }
    }
}
package Vue;

// On importe les librairies..
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/** Classe qui herite de JButton et modélise le bouton Jouer avec son événement..
 */
public class ButtonJouer extends JButton implements ActionListener {

    // On déclare la variable du JeuMemory.
    private VJeu jeuVue;
    private VMenu menu;
    private boolean active;

    /** Constructeur de la class ButtonJouer qui prend en paramètre la vue du jeu et son menu, et paramètre le bouton.
     * @param jeuVue : la fenêtre du jeu du labyrinthe.
     * @param menu : le menu de la fenêtre.
    */
    public ButtonJouer(VJeu jeuVue, VMenu menu) {
        // On paramètre les préférences du bouton.
        this.setFocusable(false);
        this.setIcon(new ImageIcon(ButtonJouer.class.getResource("../img/jouer.png")));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addActionListener(this);

        this.jeuVue = jeuVue;
        this.menu = menu;
        this.active = false;
    }

    /** Méthode qui fait varier l'image du bouton si il est actif ou non.
     * @param etat : Vrai, le bouton est actif, faux sinon (boolean).
     */
    public void setEnabled(boolean etat) {
        if(etat) {
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
            this.jeuVue.initPartie();
        }
    }
}
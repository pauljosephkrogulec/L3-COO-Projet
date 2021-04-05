
package vue;

// On importe les librairies..
import javax.swing.ImageIcon;

import modele.JeuImpl;

import java.awt.event.MouseEvent;

public class InsertionTop extends InsertionPlateau {
    
    /** Constructeur de la classe InsertionTop qui s'occupe de récupérer les valeurs et actualise l'image du bouton.
     * @param jeuVue : la fenêtre du jeu.
     * @param jeuModele : le modèle du jeu.
     * @param menu menu : le composant graphique du menu.
     * @param values : la valeur correspondant au couloir d'insertion.
     * @param orientation : l'orientation du supplémentaire.
     */
    public InsertionTop(VJeu jeuVue, JeuImpl jeuModele, VMenu menu, int values, int orientation) {
        super(jeuVue, jeuModele, menu, values, orientation);
        this.setIcon(new ImageIcon(InsertionTop.class.getResource("/img/CURSOR4.png")));
    }

    /** Méthode de MouseListener qui prend en paramètre un événement de sourie et actualise l'image du bouton, lorsque la sourie passe sur celui-ci.
     * @param e : l'événement de la sourie.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionTop.class.getResource("/img/CURSOR4_hover.png")));
    }

    /** Méthode de MouseListener qui prend en paramètre un événement de sourie et actualise l'image du bouton, lorsque la sourie sort de celui-ci.
     * @param e : l'événement de la sourie.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionTop.class.getResource("/img/CURSOR4.png")));
    }

    // Autres méthodes de MouseListener non-utilisées.
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
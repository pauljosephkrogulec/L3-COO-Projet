package Vue;

// On importe les librairies..
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;

// On importe la classe JeuImpl depuis le modèle..
import Modele.JeuImpl;

/** Classe qui hérite de InsertionBottom et s'occupe de mettre à jour l'image du boutons avec ses événements..
 */
public class InsertionBottom extends InsertionPlateau {
    
    /** Constructeur de la classe InsertionBottom qui s'occupe de récupérer les valeurs et actualise l'image du bouton.
     * @param jeuVue > la fenêtre du jeu.
     * @param jeuModele > le modèle du jeu.
     * @param values > la valeur correspondant au couloir d'insertion.
     * @param orientation > l'orientation du supplémentaire.
     */
    public InsertionBottom(VJeu jeuVue, JeuImpl jeuModele, int values, int orientation) {
        super(jeuVue, jeuModele, values, orientation);
        this.setIcon(new ImageIcon(InsertionBottom.class.getResource("../img/CURSOR3.gif")));
    }

    /** Méthode de MouseListener qui prend en paramètre un événement de sourie et actualise l'image du bouton, lorsque la sourie passe sur celui-ci.
     * @param e > l'événement de la sourie.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionBottom.class.getResource("../img/CURSOR3_hover.gif")));
    }

    /** Méthode de MouseListener qui prend en paramètre un événement de sourie et actualise l'image du bouton, lorsque la sourie sort de celui-ci.
     * @param e > l'événement de la sourie.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionBottom.class.getResource("../img/CURSOR3.gif")));
    }

    // Autres méthodes de MouseListener non-utilisées.
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}

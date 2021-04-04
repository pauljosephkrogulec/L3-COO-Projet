package Vue;

// On importe les librairies..
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;

// On importe quelques classes du modèle..
import Modele.PositionInsertion;
import Modele.JeuImpl;
import Modele.Orientation;

/** Classe abstraite qui hérite des propriétés d'un JButton,
 * et modélise les boutons d'insertions d'un couloir dans le plateau avec leurs événements..
 */
abstract class InsertionPlateau extends JButton implements ActionListener, MouseListener {

    // On déclare quelques variables...
    protected VJeu jeuVue;
    protected JeuImpl jeuModele;
    protected VMenu menu;
    protected int values, orientation;

    /** Constructeur de la classe InsertionPlateau qui prend en paramètre la vue et le modèle du jeu,
     * la valeur du couloir et l'orientation du supplémentaire, et s'occupe de l'insérer au plateau du jeu.
     * @param jeuVue : la fenêtre du jeu.
     * @param jeuModele : le modèle du jeu.
     * @param menu menu : le composant graphique du menu.
     * @param values : la valeur correspondant au couloir d'insertion.
     * @param orientation : l'orientation du supplémentaire.
     */
    public InsertionPlateau(VJeu jeuVue, JeuImpl jeuModele, VMenu menu, int values, int orientation){
        // On paramètre les préférences des boutons.
        this.setFocusable(false);
        this.setOpaque(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addActionListener(this);
        this.addMouseListener(this);

        // On stocke les paramètres.
        this.jeuVue = jeuVue;
        this.jeuModele = jeuModele;
        this.menu = menu;
        this.values = values;
        this.orientation = orientation;
    }
    
    /** Méthode de ActionListener qui prend en paramètre un événement et modifie le couloir et actualise la fenêtre.
     * @param e : l'événement.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.jeuModele.modifierCouloir(PositionInsertion.values()[this.values], Orientation.values()[this.orientation]);
        this.menu.setEnableButtonTour(true);
        this.jeuVue.refresh(true);
    }
}
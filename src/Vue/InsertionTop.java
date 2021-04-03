package Vue;

// On importe les librairies..
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;

// On importe la classe JeuImpl depuis le modèle..
import Modele.JeuImpl;

public class InsertionTop extends InsertionPlateau {
    
    public InsertionTop(VJeu jeuVue, JeuImpl jeuModele, int values, int orientation) {
        super(jeuVue, jeuModele, values, orientation);
        this.setIcon(new ImageIcon(InsertionTop.class.getResource("../img/CURSOR4.gif")));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionTop.class.getResource("../img/CURSOR4_hover.gif")));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionTop.class.getResource("../img/CURSOR4.gif")));
    }

    // Autres méthodes de MouseListener non-utilisées.
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}

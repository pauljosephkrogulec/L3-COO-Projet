package Vue;

// On importe les librairies..
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;

// On importe la clase JeuImpl depuis le modèle..
import Modele.JeuImpl;

public class InsertionRight extends InsertionPlateau {
    
    public InsertionRight(VJeu jeuVue, JeuImpl jeuModele, int values, int orientation) {
        super(jeuVue, jeuModele, values, orientation);
        this.setIcon(new ImageIcon(InsertionRight.class.getResource("../img/CURSOR2.gif")));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionRight.class.getResource("../img/CURSOR2_hover.gif")));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionRight.class.getResource("../img/CURSOR2.gif")));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

}

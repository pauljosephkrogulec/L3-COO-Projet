package Vue;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import Modele.*;

public class InsertionLeft extends InsertionPlateau {
    
    public InsertionLeft(VJeu vjeu, Jeu jeu, int values, int orientation) {
        super(vjeu, jeu, values, orientation);
        this.setIcon(new ImageIcon(InsertionLeft.class.getResource("../img/CURSOR1.gif")));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionLeft.class.getResource("../img/CURSOR1_hover.gif")));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setIcon(new ImageIcon(InsertionLeft.class.getResource("../img/CURSOR1.gif")));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}

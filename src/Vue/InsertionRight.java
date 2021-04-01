package Vue;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;

public class InsertionRight extends InsertionPlateau {
    
    public InsertionRight(int values) {
        super(values);
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

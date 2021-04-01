package Vue;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;

public class InsertionTop extends InsertionPlateau {
    
    public InsertionTop(int values) {
        super(values);
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

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}

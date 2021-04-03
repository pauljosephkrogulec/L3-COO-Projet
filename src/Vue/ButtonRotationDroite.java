package Vue;
import javax.swing.ImageIcon;
import Modele.Couloir;

public class ButtonRotationDroite extends ButtonRotation {

    public ButtonRotationDroite(VJeu vjeu, Couloir supp) {
        super(vjeu, supp);        
        this.setIcon(new ImageIcon(InsertionLeft.class.getResource("../img/rotationDroite.gif")));
    }
    
}

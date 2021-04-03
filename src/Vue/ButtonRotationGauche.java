package Vue;
import javax.swing.ImageIcon;
import Modele.Couloir;

public class ButtonRotationGauche extends ButtonRotation {
    
    public ButtonRotationGauche(VJeu vjeu, Couloir supp) {
        super(vjeu, supp);        
        this.setIcon(new ImageIcon(InsertionLeft.class.getResource("../img/rotationGauche.gif")));
    }
}

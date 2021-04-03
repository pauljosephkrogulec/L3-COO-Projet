package Vue;

// On importe les librairies..
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import Modele.PositionInsertion;
import Modele.JeuImpl;
import Modele.Orientation;

abstract class InsertionPlateau extends JButton implements ActionListener, MouseListener {

    protected VJeu jeuVue;
    protected JeuImpl jeuModele;
    protected int values, orientation;

    public InsertionPlateau(VJeu jeuVue, JeuImpl jeuModele, int values, int orientation){
        super();
        this.jeuVue = jeuVue;
        this.jeuModele = jeuModele;
        this.values = values;
        this.orientation = orientation;
        this.setFocusable(false);
        this.setOpaque(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addActionListener(this);
        this.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.jeuModele.modifierCouloir(PositionInsertion.values()[this.values], Orientation.values()[this.orientation]);
        this.jeuVue.setEtatBtnFiniTour(true);
        this.jeuVue.refresh(true);
    }
}
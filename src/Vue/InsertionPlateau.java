package Vue;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import Modele.PositionInsertion;
import Modele.*;

abstract class InsertionPlateau extends JButton implements ActionListener, MouseListener {

    protected VJeu vjeu;
    protected Jeu jeu;
    protected int values, orientation;

    public InsertionPlateau(VJeu vjeu, Jeu jeu, int values, int orientation){
        super();
        this.vjeu = vjeu;
        this.jeu = jeu;
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
        this.jeu.modifierCouloir(PositionInsertion.values()[this.values], Orientation.values()[this.orientation]);
        this.vjeu.setEtatBtnFiniTour(true);
        this.vjeu.refresh(true);
    }
}
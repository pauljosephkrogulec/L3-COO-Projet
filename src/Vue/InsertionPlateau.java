package Vue;
import javax.swing.JButton;

import Modele.PositionInsertion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

abstract class InsertionPlateau extends JButton implements ActionListener, MouseListener {

    private int values;
    public InsertionPlateau(int values){
        super();
        this.values = values;
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
        this.gPositionInsertion();
    }

    public PositionInsertion gPositionInsertion(){
        return PositionInsertion.values()[this.values];
    }
}
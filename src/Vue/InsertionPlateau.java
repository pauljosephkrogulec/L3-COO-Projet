package Vue;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Modele.PositionInsertion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class InsertionPlateau extends JButton implements ActionListener {

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
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.gPositionInsertion();        
    }

    public abstract void setImg();

    public PositionInsertion gPositionInsertion(){
        return PositionInsertion.values()[this.values];
    }
}
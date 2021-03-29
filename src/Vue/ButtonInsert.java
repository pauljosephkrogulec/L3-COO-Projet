package Vue;
import javax.swing.JButton;

import Modele.PositionInsertion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonInsert extends JButton implements ActionListener {

    private int values;
    public ButtonInsert(int values){
        super();
        this.values = values;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.gPositionInsertion();        
    }

    public PositionInsertion gPositionInsertion(){
        return PositionInsertion.values()[this.values];
    }

}
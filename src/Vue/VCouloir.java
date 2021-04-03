package Vue;

// On importe les librairies..
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;

// On importe quelques classes du modèle..
import Modele.Couloir;
import Modele.Pion;

public class VCouloir extends JButton implements MouseListener {

    private Couloir couloir;
    private int cordX, cordY;

    public VCouloir(Couloir couloir, int x, int y) {
        super();
        this.setBackground(new Color(69, 46, 43));
        this.setBorder(BorderFactory.createBevelBorder(0,new Color(69, 46, 43), new Color(69, 46, 43)));
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setLayout(null);

        this.couloir = couloir;
        this.cordX = x;
        this.cordY = y;     
        this.addMouseListener(this);
    }
    
    public int getcordX() {
        return this.cordX;
    }

    public int getcordY() {
        return this.cordY;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        String forme = "";
        switch(couloir.getForme()) {
            case TE :
                forme = "TE";
                break;
            case COUDE :
                forme = "COUDE";
                break;
            default :
                forme = "DROIT";
                break;
        }
        switch(couloir.getOrientation()) {
            case NORD :
                forme += "_NORD";
                break;
            case EST :
                forme += "_EST";
                break;
            case SUD :
                forme += "_SUD";
                break;
            default :
                forme += "_OUEST";
                break;
        }

        g.drawImage(new ImageIcon(VCouloir.class.getResource("../img/couloirs/" + forme + ".gif")).getImage(), 3, 3, 50, 50, this);

        if(couloir.getObjectif() != null) {
            g.drawImage(new ImageIcon(VCouloir.class.getResource("../img/objectifs/" + couloir.getObjectif() + ".png")).getImage(), 18, 18, 20, 20, this);
        }

        for(Pion pion : couloir.getPions()) {
            g.drawImage(new ImageIcon(VCouloir.class.getResource("../img/" + pion.getCouleur() + ".png")).getImage(), 20, 20, 16, 16, this);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBorder(BorderFactory.createBevelBorder(0,new Color(236, 192, 111), new Color(236, 192, 111)));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBorder(BorderFactory.createBevelBorder(0,new Color(69, 46, 43), new Color(69, 46, 43)));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
    
}

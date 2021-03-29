package Vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VMenu extends JPanel {

    private JPanel header;
    private JButton btnQuitter;

    public VMenu(int h) {
        super();
        // On paramètre le Menu.
        this.setPreferredSize(new Dimension(300, h));
        this.setBackground(new Color(52, 61, 88));
        this.setLayout(null);

        creerHeader();
    }

    private void creerHeader() {
        // On crée/paramètre le header.
        this.header = new JPanel();
        this.header.setBackground(new Color(52, 61, 88));
        this.header.setLayout(null);

        // On crée le bouton pour quitter la fenêtre
        this.btnQuitter = new ButtonQuitter();
        this.header.add(this.btnQuitter);
        this.btnQuitter.setBounds(262, 20, 18, 18);

        // On crée le logo.
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(VJeu.class.getResource("../img/logo.png")));
        this.header.add(logo);
        logo.setBounds(20, 60, 260, 70);

        // On ajoute l'entête au menu et on le positionne.
        this.header.setBounds(0, 0, 300, 150);
        this.add(this.header);
    }
    
}

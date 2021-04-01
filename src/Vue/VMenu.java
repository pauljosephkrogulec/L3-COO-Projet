package Vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VMenu extends JPanel {

    private VJeu jeu;
    private JPanel header, accueil;
    private JButton btnJouer, btnQuitter, btnReduire;
    private ButtonRadio joueurNb2, joueurNb3, joueurNb4;

    public VMenu(VJeu jeu, int HAUTEUR) {
        super();
        this.jeu = jeu;
        // On paramètre le Menu.
        this.setPreferredSize(new Dimension(300, HAUTEUR));
        this.setBackground(new Color(77, 51, 48));
        this.setLayout(null);

        creerHeader();

        this.accueil = new JPanel();
        this.accueil.setBackground(new Color(77, 51, 48));
        this.accueil.setLayout(null);
        
        // On déclare des label.
        JLabel regles;

        // On crée un label qui affiche les règles.
        regles = new JLabel();
        regles.setIcon(new ImageIcon(VJeu.class.getResource("../img/regles.png")));
        this.accueil.add(regles);
        regles.setBounds(0, 0, 250, 191);

        // Panel qui contiendra les choix du terrain.
        JPanel choixNbJoueurs = new JPanel(new GridLayout(1, 2));
        choixNbJoueurs.setPreferredSize(new Dimension(202, 20));
        choixNbJoueurs.setOpaque(false);

        ButtonGroup btnNbJoueurs = new ButtonGroup();
        this.joueurNb2 = new ButtonRadio();
        this.joueurNb3 = new ButtonRadio();
        this.joueurNb4 = new ButtonRadio();

        this.joueurNb2.setSelected(true);

        // On les RadioButton dans le groupe de boutons et dans le panel.
        choixNbJoueurs.add(joueurNb2);
        btnNbJoueurs.add(joueurNb2);
        choixNbJoueurs.add(joueurNb3);
        btnNbJoueurs.add(joueurNb3);
        choixNbJoueurs.add(joueurNb4);
        btnNbJoueurs.add(joueurNb4);

        // On ajoute dans le panel option, le panel des terrains.
        this.accueil.add(choixNbJoueurs);
        choixNbJoueurs.setBounds(0, 173, 250, 20);

        // On crée le bouton Jouer qui lance la partie.
        this.btnJouer = new ButtonJouer(this.jeu);
        this.accueil.add(this.btnJouer);
        this.btnJouer.setBounds(0, 250, 250, 42);
        
        this.add(this.accueil);
        this.accueil.setBounds(25, 150, 250, 350);
    }

    private void creerHeader() {
        // On crée/paramètre le header.
        this.header = new JPanel();
        this.header.setBackground(new Color(77, 51, 48));
        this.header.setLayout(null);

        // On crée le bouton pour quitter la fenêtre
        this.btnQuitter = new ButtonQuitter();
        this.header.add(this.btnQuitter);
        this.btnQuitter.setBounds(262, 20, 18, 18);

        // On crée le bouton pour réduire la fenêtre.
        this.btnReduire = new ButtonReduire(this.jeu);
        this.header.add(this.btnReduire);
        this.btnReduire.setBounds(235, 20, 18, 18);

        // On crée le logo.
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(VJeu.class.getResource("../img/logo.png")));
        this.header.add(logo);
        logo.setBounds(20, 60, 260, 70);

        // On ajoute l'entête au menu et on le positionne.
        this.header.setBounds(0, 0, 300, 150);
        this.add(this.header);
    }

    public void setBtnJouer(boolean b) {
        this.btnJouer.setEnabled(b);
    }
    
}

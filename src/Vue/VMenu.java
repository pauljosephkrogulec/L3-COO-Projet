package Vue;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VMenu extends JPanel {

    private VJeu jeu;
    private JPanel header, accueil;
    private JButton btnJouer, btnQuitter, btnReduire;

    public VMenu(VJeu jeu, int HAUTEUR) {
        super();
        this.jeu = jeu;
        // On paramètre le Menu.
        this.setPreferredSize(new Dimension(300, HAUTEUR));
        this.setBackground(new Color(52, 61, 88));
        this.setLayout(null);

        creerHeader();

        this.accueil = new JPanel();
        this.accueil.setBackground(new Color(52, 61, 88));
        this.accueil.setLayout(null);

        // On crée le bouton Jouer qui lance la partie.
        this.btnJouer = new ButtonJouer(this.jeu);
        this.accueil.add(this.btnJouer);
        this.btnJouer.setBounds(0, 235, 202, 42);

        this.add(this.accueil);
        this.accueil.setBounds(50, 175, 202, 300);
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

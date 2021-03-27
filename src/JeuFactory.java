
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Constructeur de la classe JeuFactory qui paramètre la fenêtre et ses
 * fonctionnalités.
 */
public class JeuFactory extends JFrame {

    // On déclare quelques variables...
    private static int LONGUEUR = 800;
    private static int HAUTEUR = 500;
    private JPanel menu, labyrinthe;
    private static List<Couloir> listBtn;
    private JeuImpl jeu;

    public JeuFactory() {
        // On initialise la fenêtre.
        super("Jeu du Labyrinthe");
        this.jeu = (JeuImpl) creeJeu();
        this.setSize(LONGUEUR, HAUTEUR);

        this.menu = creerMenu();
        this.getContentPane().add(this.menu, "East");

        this.labyrinthe = creerPanel();
        createJbutton(this.labyrinthe);
        this.getContentPane().add(this.labyrinthe, "West");
        // On paramètre la fenêtre.
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        game();

    }
    
    private void game(){
        
        Joueur joueur;
        do {
            joueur = jeu.prochainJoueur();

            joueur.joue();
            this.labyrinthe.removeAll();
            this.labyrinthe.revalidate();   
            this.labyrinthe.repaint();
            createJbutton(this.labyrinthe);
            this.labyrinthe.revalidate();   
            this.labyrinthe.repaint();

        } while (!jeu.aGagne(joueur));
    }   

    /**
     * Méthode qui créer le Menu du jeu.
     */
    public JPanel creerMenu() {

        JPanel m = new JPanel();
        // On paramètre le Menu.
        m.setPreferredSize(new Dimension(300, HAUTEUR));
        m.setBackground(new Color(29, 30, 33));
        m.setLayout(null);

        return m;
    }

    /**
     * Méthode qui créer le Menu du jeu.
     */
    public JPanel creerPanel() {

        JPanel l = new JPanel();
        // On paramètre le Menu.
        l.setPreferredSize(new Dimension(LONGUEUR - 350, HAUTEUR));
        l.setLayout(null);

        GridLayout grid = new GridLayout(7,7);
        l.setLayout(grid);

        return l;
    }
    private void createJbutton(JPanel JpBtn){
        

        listBtn = jeu.couloirs();
        // add JButtons dynamically
        for (int i = 0; i < listBtn.size(); i++) {
            JpBtn.add((JButton) listBtn.get(i));
        }
    }

    private Jeu creeJeu() {
        return new JeuImpl();
    }

}

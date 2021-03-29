package Vue;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Modele.*;
/**
 * Constructeur de la classe JeuFactory qui paramètre la fenêtre et ses
 * fonctionnalités.
 */
public class VLabyrinthe extends JFrame {

    // On déclare quelques variables...
    private static int LONGUEUR = 800;
    private static int HAUTEUR = 500;
    private JPanel menu, labyrinthe, header;
    private JButton btnQuitter;
    private static List<Couloir> listBtn;
    private JeuImpl jeu;

    public VLabyrinthe() {
        // On initialise la fenêtre.
        super("Jeu du Labyrinthe");
        this.setSize(LONGUEUR, HAUTEUR);
        this.setResizable(false); // On fixe la taille de la fenêtre.
        this.setUndecorated(true); // On supprime la topbar (que je vais créer moi-même).
        
        this.menu = new JPanel();
        this.jeu = (JeuImpl) JeuFactory.creeJeu(this);
        
        this.creerMenu();
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
        } while (!jeu.aGagne(joueur));
    }   

    /**
     * Méthode qui créer le Menu du jeu.
     */
    public void creerMenu() {

        // On paramètre le Menu.
        this.menu.setPreferredSize(new Dimension(300, HAUTEUR));
        this.menu.setBackground(new Color(147, 81, 18));
        this.menu.setLayout(null);

        creerHeader();
    }

    /** Méthode qui crée le header (l'entête) du menu.
     */
    public void creerHeader() {
        // On crée/paramètre le header.
        this.header = new JPanel();
        this.header.setBackground(new Color(147, 81, 18));
        this.header.setLayout(null);

        // On crée le bouton pour quitter la fenêtre
        this.btnQuitter = new ButtonQuitter();
        this.header.add(this.btnQuitter);
        this.btnQuitter.setBounds(262, 20, 18, 18);

        // On ajoute l'entête au menu et on le positionne.
        this.menu.add(this.header);
        this.header.setBounds(0, 0, 300, 150);
    }

    /** Méthode qui créer le Menu du jeu.
     */
    public JPanel creerPanel() {

        JPanel l = new JPanel();
        l.setBackground(new Color(201, 127, 27));        
        l.setPreferredSize(new Dimension(500, HAUTEUR));
        // On paramètre le Menu.

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
    public void refresh(){
        this.labyrinthe.removeAll();
        this.labyrinthe.revalidate();
        this.labyrinthe.repaint();
        createJbutton(this.labyrinthe);
        this.labyrinthe.revalidate();   
        this.labyrinthe.repaint();
    }
}

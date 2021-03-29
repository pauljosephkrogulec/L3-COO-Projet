package Vue;
import java.util.List;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Modele.*;
/**
 * Constructeur de la classe JeuFactory qui paramètre la fenêtre et ses
 * fonctionnalités.
 */
public class VJeu extends JFrame {

    // On déclare quelques variables...
    private static int LONGUEUR = 800;
    private static int HAUTEUR = 500;
    private VMenu menu;
    private VLabyrinthe labyrinthe;
    private JPanel plateau;
    private static List<Couloir> listBtn;
    private JeuImpl jeu;

    public VJeu() {
        // On initialise la fenêtre.
        super("Jeu du Labyrinthe");
        this.setSize(LONGUEUR, HAUTEUR);
        this.setResizable(false); // On fixe la taille de la fenêtre.
        this.setUndecorated(true); // On supprime la topbar (que je vais créer moi-même).
        
        this.menu = new VMenu(HAUTEUR);
        this.getContentPane().add(this.menu, "East");

        this.jeu = (JeuImpl) JeuFactory.creeJeu(this);
        
        this.labyrinthe = new VLabyrinthe(HAUTEUR);
        this.getContentPane().add(this.labyrinthe, "West");
        this.plateau = creerPlateau();

        // On paramètre la fenêtre.
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.jouer();
    }
    
    private void jouer() {
        
        Joueur joueur;
        do {
            joueur = jeu.prochainJoueur();
            joueur.joue();
        } while (!jeu.aGagne(joueur));
    }

    /** Méthode qui créer le Menu du jeu.
     */
    public JPanel creerPlateau() {
                       
        JPanel p = new JPanel();
        p.setBackground(new Color(68, 79, 116));
        p.setLayout(null);
        this.rempliLabyrinthe(p);
        GridLayout grid = new GridLayout(7,7);
        p.setLayout(grid);

        this.labyrinthe.add(p, BorderLayout.CENTER);

        return p;
    }

    private void rempliLabyrinthe(JPanel p) {

        listBtn = jeu.couloirs();
        for (int i = 0; i < listBtn.size(); i++) {
            p.add((JButton) listBtn.get(i));
        }
    }

    public void refresh() {
        this.plateau.removeAll();
        this.plateau.revalidate();
        this.plateau.repaint();
        this.rempliLabyrinthe(this.plateau);
        this.plateau.revalidate();   
        this.plateau.repaint();
    }
}

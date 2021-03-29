package Vue;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
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
    private JPanel labyrinthe, plateau;
    private VMenu menu;
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
        
        this.labyrinthe = new JPanel();
        this.creerLabyrinthe();
        this.getContentPane().add(this.labyrinthe, "West");

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
    public void creerLabyrinthe() {

        this.labyrinthe.setBackground(new Color(68, 79, 116));
        this.labyrinthe.setPreferredSize(new Dimension(500, HAUTEUR));
        this.labyrinthe.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setBackground(new Color(68, 79, 116));
        top.setPreferredSize(new Dimension(500, 50));
        top.setLayout(null);

        JButton btn0 = new InsertionTop(0);
        btn0.setBounds(120, 10, 32, 32);
        top.add(btn0);

        JButton btn1 = new InsertionTop(0);
        btn1.setBounds(235, 10, 32, 32);
        top.add(btn1);

        JButton btn2 = new InsertionTop(0);
        btn2.setBounds(348, 10, 32, 32);
        top.add(btn2);

        this.labyrinthe.add(top, BorderLayout.NORTH);
        
        JPanel left = new JPanel();
        left.setBackground(new Color(68, 79, 116));
        left.setPreferredSize(new Dimension(50, 500));
        left.setLayout(null);

        JButton btn3 = new InsertionLeft(0);
        btn3.setBounds(10, 71, 32, 32);
        left.add(btn3);

        JButton btn4 = new InsertionLeft(0);
        btn4.setBounds(10, 185, 32, 32);
        left.add(btn4);

        JButton btn5 = new InsertionLeft(0);
        btn5.setBounds(10, 300, 32, 32);
        left.add(btn5);
        
        this.labyrinthe.add(left, BorderLayout.WEST);
        
        JPanel right = new JPanel();
        right.setBackground(new Color(68, 79, 116));
        right.setPreferredSize(new Dimension(50, 500));
        right.setLayout(null);

        JButton btn6 = new InsertionRight(0);
        btn6.setBounds(10, 71, 32, 32);
        right.add(btn6);

        JButton btn7 = new InsertionRight(0);
        btn7.setBounds(10, 185, 32, 32);
        right.add(btn7);

        JButton btn8 = new InsertionRight(0);
        btn8.setBounds(10, 300, 32, 32);
        right.add(btn8);

        this.labyrinthe.add(right, BorderLayout.EAST);
        
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(68, 79, 116));
        bottom.setPreferredSize(new Dimension(500, 50));
        bottom.setLayout(null);

        JButton btn9 = new InsertionBottom(0);
        btn9.setBounds(120, 10, 32, 32);
        bottom.add(btn9);

        JButton btn10 = new InsertionBottom(0);
        btn10.setBounds(235, 10, 32, 32);
        bottom.add(btn10);

        JButton btn11 = new InsertionBottom(0);
        btn11.setBounds(348, 10, 32, 32);
        bottom.add(btn11);

        this.labyrinthe.add(bottom, BorderLayout.SOUTH);
               
        this.plateau = new JPanel();
        this.plateau.setBackground(new Color(68, 79, 116));
        this.plateau.setLayout(null);
        this.createJbutton();
        GridLayout grid = new GridLayout(7,7);
        this.plateau.setLayout(grid);
        
        this.labyrinthe.add(this.plateau, BorderLayout.CENTER);

    }

    private void createJbutton() {

        listBtn = jeu.couloirs();
        // add JButtons dynamically
        for (int i = 0; i < listBtn.size(); i++) {
            this.plateau.add((JButton) listBtn.get(i));
        }
    }

    public void refresh() {
        this.plateau.removeAll();
        this.plateau.revalidate();
        this.plateau.repaint();
        this.createJbutton();
        this.plateau.revalidate();   
        this.plateau.repaint();
    }
}

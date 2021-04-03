package Vue;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    private JPanel menu, header, accueil, tour, plateau;
    private JButton btnJouer, btnQuitter, btnReduire, btnFiniTour;
    private ButtonRadio joueurNb2, joueurNb3, joueurNb4;
    
    private JeuImpl jeu;

    private VLabyrinthe labyrinthe;
    private static List<Couloir> listBtn;

    private Joueur currentJoueur;

    public VJeu() {
        // On initialise la fenêtre.
        super("Jeu du Labyrinthe");
        this.setSize(LONGUEUR, HAUTEUR);
        this.setResizable(false); // On fixe la taille de la fenêtre.
        this.setUndecorated(true); // On supprime la topbar (que je vais créer moi-même).

        this.creerMenu();
        this.getContentPane().add(this.menu, "East");
        
        this.jeu = new JeuImpl(this);
         
        this.labyrinthe = new VLabyrinthe(this, HAUTEUR);
        this.getContentPane().add(this.labyrinthe, "West");
        
        // On paramètre la fenêtre.
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void creerMenu() {

        this.menu = new JPanel();
        this.menu.setPreferredSize(new Dimension(300, HAUTEUR));
        this.menu.setBackground(new Color(77, 51, 48));
        this.menu.setLayout(null);

        // On ajoute l'entête au menu (+ logo).
        creerHeader();
        // On ajoute le panel d'accueil qui contient les règles, le score et le bouton jouer.
        creerPanelAccueil();
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
        this.btnReduire = new ButtonReduire(this);
        this.header.add(this.btnReduire);
        this.btnReduire.setBounds(235, 20, 18, 18);

        // On crée le logo.
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(VJeu.class.getResource("../img/logo.png")));
        this.header.add(logo);
        logo.setBounds(20, 60, 260, 70);

        // On ajoute l'entête au menu et on le positionne.
        this.header.setBounds(0, 0, 300, 150);
        this.menu.add(this.header);

    }

    private void creerPanelAccueil() {
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
        this.btnJouer = new ButtonJouer(this);
        this.accueil.add(this.btnJouer);
        this.btnJouer.setBounds(0, 250, 250, 42);
        
        this.menu.add(this.accueil);
        this.accueil.setBounds(25, 150, 250, 350);

    }

    public void creerPanelTour() {

        this.tour = new JPanel();
        this.tour.setBackground(new Color(77, 51, 48));
        this.tour.setLayout(null);
        
        // On déclare des label.
        JLabel img_tour, label_joueur, label_objectif;

        // On crée un label qui affiche les règles.
        img_tour = new JLabel();
        img_tour.setIcon(new ImageIcon(VJeu.class.getResource("../img/tour.png")));
        this.tour.add(img_tour);
        img_tour.setBounds(0, 0, 250, 191);

        // On crée un label qui affiche les règles.
        label_joueur = new JLabel();
        ImageIcon pion = new ImageIcon(VJeu.class.getResource("../img/" + this.currentJoueur.getPion().getCouleur() + ".png"));
        Image img_pion = pion.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        label_joueur.setIcon(new ImageIcon(img_pion));
        this.tour.add(label_joueur);
        label_joueur.setBounds(135, -6, 25, 25);

        // On crée un label qui affiche les règles.
        label_objectif = new JLabel();
        ImageIcon objectif = new ImageIcon(VJeu.class.getResource("../img/objectifs/"+ this.currentJoueur.getObjectifs().peek() + ".png"));
        Image img_objectif = objectif.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        label_objectif.setIcon(new ImageIcon(img_objectif));
        this.tour.add(label_objectif);
        label_objectif.setBounds(80, 25, 30, 30);

        Orientation tabOrientation[] = {Orientation.EST, Orientation.SUD, Orientation.OUEST, Orientation.NORD};

        ButtonRotation rotationDroite = new ButtonRotationDroite(this, this.jeu.getSupplementaire());
        this.tour.add(rotationDroite);
        rotationDroite.setBounds(40, 115, 35, 35);
        rotationDroite.addActionListener((ActionEvent evt) -> {
            this.jeu.getSupplementaire().setOrientation(tabOrientation[(Arrays.asList(tabOrientation).indexOf(this.jeu.getSupplementaire().getOrientation()) + 1) % 4]);
            this.refreshTour();
        });

        VCouloir supp = new VCouloir(this.jeu, this.jeu.getSupplementaire(), -1, -1);
        supp.setEnabled(false);
        this.tour.add(supp);
        supp.setBounds(95, 105, 57, 57);

        ButtonRotation rotationGauche = new ButtonRotationGauche(this, this.jeu.getSupplementaire());
        this.tour.add(rotationGauche);
        rotationGauche.setBounds(172, 115, 35, 35);
        rotationGauche.addActionListener((ActionEvent evt) -> {
            if(this.jeu.getSupplementaire().getOrientation() == Orientation.EST) {
                this.jeu.getSupplementaire().setOrientation(Orientation.NORD);
            } else {
                this.jeu.getSupplementaire().setOrientation(tabOrientation[Arrays.asList(tabOrientation).indexOf(this.jeu.getSupplementaire().getOrientation()) - 1]);
            }
            this.refreshTour();
        });


        // On crée le bouton Jouer qui lance la partie.
        this.btnFiniTour = new ButtonFinTour(this);
        this.tour.add(this.btnFiniTour);
        this.btnFiniTour.setBounds(0, 250, 250, 42);

        
        this.menu.add(this.tour);
        this.tour.setBounds(25, 150, 250, 350);
    }

    /** Méthode qui cache le panel des options et affiche celui de l'accueil.
     */
    public void afficheAccueil() {
        this.tour.setVisible(false);
        this.accueil.setVisible(true);
    }

    /** Méthode qui cache le panel des options et affiche celui de l'accueil.
     */
    public void afficheTour() {
        this.accueil.setVisible(false);
        this.tour.setVisible(true);
    }
    
    public void initPartie() {

        this.jeu.preparer();
        this.joueurSuivant();
        this.labyrinthe.creerLabyrinthe(this.jeu);
        this.plateau = creerPlateau();
    }

    public void joueurSuivant() {
        this.currentJoueur = this.jeu.prochainJoueur();
    }

    public void jouePartie() {

        boolean terminer = false;

        while(!terminer) {
            if(this.jeu.aGagne(this.currentJoueur)) {
                terminer = true;
            }
            this.currentJoueur = this.jeu.prochainJoueur();
            this.labyrinthe.creerLabyrinthe(this.jeu);
            this.plateau = creerPlateau();
            refresh();
        }
    }

    public void PartieFinie() {
        this.btnJouer.setEnabled(true);
    }

    /** Méthode qui créer le Menu du jeu.
     */
    public JPanel creerPlateau() {
                       
        JPanel p = new JPanel();
        p.setBackground(new Color(69, 46, 43));
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
            VCouloir vcouloir = new VCouloir(this.jeu, listBtn.get(i), i / 7, i % 7);
            vcouloir.addActionListener((ActionEvent evt) -> {
                this.currentJoueur.joue(new Position(vcouloir.getcordX(), vcouloir.getcordY()));
            });

            p.add(vcouloir);
        }
    }

    public void refresh() {
        this.plateau.removeAll();
        this.rempliLabyrinthe(this.plateau);
        this.plateau.revalidate();
        this.plateau.repaint();
        this.refreshTour();
    }

    public void refreshTour() {
        this.tour.removeAll();
        this.creerPanelTour();
        this.tour.revalidate();        
        this.tour.repaint();
    }
}

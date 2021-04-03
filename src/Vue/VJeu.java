package Vue;

// On importe les librairies..
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

// On importe quelques classes du modèle..
import Modele.JeuImpl;
import Modele.Couloir;
import Modele.Joueur;
import Modele.Position;

/** Classe qui implémente les actions et modélise l'interface graphique du jeu du Labyrinthe.
 */
public class VJeu extends JFrame {

    // On déclare quelques variables...
    private static int LONGUEUR = 800;
    private static int HAUTEUR = 500;

    private JeuImpl jeu;
    
    private VMenu menu;
    private VLabyrinthe labyrinthe;
    private JPanel plateau;    

    private static List<Couloir> listBtn;
    private ArrayList<VCouloir> vCouloirs;

    private Joueur joueurCourant;

    /** Constructeur de la classe VJeu qui paramètre la fenêtre et ses fonctionnalités.
     */
    public VJeu() {
        // On initialise la fenêtre.
        super("Jeu du Labyrinthe");
        this.setSize(LONGUEUR, HAUTEUR);
        this.setResizable(false); // On fixe la taille de la fenêtre.
        this.setUndecorated(true); // On supprime la topbar (que je vais créer moi-même).
        
        this.jeu = new JeuImpl(this);

        this.menu = new VMenu(this, this.jeu);
        this.getContentPane().add(this.menu, "East");
         
        this.labyrinthe = new VLabyrinthe(this, HAUTEUR);
        this.getContentPane().add(this.labyrinthe, "West");
        
        // On paramètre la fenêtre.
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void initPartie() {
        
        this.jeu.preparer(this.menu.getNbJoueurs());
        this.joueurSuivant();
        this.labyrinthe.creerButtonInsertion(this.jeu);
        this.plateau = creerPlateau();
        this.setEtatVue(false);
    }

    public void joueurSuivant() {
        this.joueurCourant = this.jeu.prochainJoueur();
    }

    public Joueur getJoueurCourant() {
        return this.joueurCourant;
    }

    public void PartieFinie() {
        this.menu.setEnableButtonJouer(true);
    }

    /** Méthode qui créer le plateau du jeu.
     */
    public JPanel creerPlateau() {
                       
        JPanel p = new JPanel();
        p.setBackground(new Color(69, 46, 43));
        p.setLayout(null);

        this.remplirPlateau(p);

        GridLayout grid = new GridLayout(7,7);
        p.setLayout(grid);
        this.labyrinthe.add(p, BorderLayout.CENTER);
        return p;
    }

    private void remplirPlateau(JPanel p) {

        listBtn = jeu.couloirs();
        vCouloirs = new ArrayList<VCouloir>();
        for (int i = 0; i < listBtn.size(); i++) {
            VCouloir vcouloir = new VCouloir(listBtn.get(i), i / 7, i % 7);
            vcouloir.addActionListener((ActionEvent evt) -> {
                this.joueurCourant.joue(new Position(vcouloir.getcordX(), vcouloir.getcordY()));
            });

            p.add(vcouloir);
            vCouloirs.add(vcouloir);
        }
        this.setEtatVue(false);
    }

    public void setEtatBtnFiniTour(boolean b) {
        this.menu.setEnableButtonTour(b);
    }

    public void setEtatVue(boolean b) {
        this.labyrinthe.setButton(!b);
        for(int i = 0; i < vCouloirs.size();i++) {
            vCouloirs.get(i).setEnabled(b);
        }
    }

    public void refresh(boolean etat) {
        this.plateau.removeAll();
        this.remplirPlateau(this.plateau);
        this.plateau.revalidate();
        this.plateau.repaint();
        this.menu.refreshTour(etat);
        this.setEtatVue(etat);
    }
}

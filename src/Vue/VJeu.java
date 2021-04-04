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
import Modele.JeuFactory;
import Modele.Joueur;
import Modele.Position;

/** Classe qui implémente les actions et modélise l'interface graphique du jeu du Labyrinthe..
 */
public class VJeu extends JFrame {

    // On déclare quelques variables...
    private static int LONGUEUR = 800;
    private static int HAUTEUR = 500;

    private JeuImpl jeu;
    
    private VMenu menu;
    private VPlateau plateau;
    private JPanel couloirs;    

    private static List<Couloir> couloirsModele;
    private ArrayList<VCouloir> couloirsVue;

    private Joueur joueurCourant;

    /** Constructeur de la classe VJeu qui paramètre la fenêtre et ses fonctionnalités.
     */
    public VJeu() {
        // On initialise la fenêtre.
        super("Jeu du Labyrinthe");
        this.setSize(LONGUEUR, HAUTEUR);
        this.setResizable(false); // On fixe la taille de la fenêtre.
        this.setUndecorated(true); // On supprime la topbar (que je vais créer moi-même).
        
        // On crée le jeu.
        this.jeu = (JeuImpl) JeuFactory.creeJeu(this);
        
        // On crée et on positionne à gauche le labyrinthe vide par défaut.
        this.plateau = new VPlateau(this, HAUTEUR);
        this.getContentPane().add(this.plateau, "West");

        // On crée le menu et on le positionne à droite.
        this.menu = new VMenu(this, this.jeu);
        this.getContentPane().add(this.menu, "East");
        
        // On paramètre la fenêtre.
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /** Méthode qui initialise une partie.
     */
    public void initPartie() {        
        this.jeu.preparer(this.menu.getNbJoueurs());
        this.joueurSuivant();
        this.plateau.creerButtonInsertion(this.jeu);
        this.creerPlateau();
        this.setEtatVue(false);
    }

    /** Méthode qui associe à la variable global du joueur courant, le prochain joueur.
     */
    public void joueurSuivant() {
        this.joueurCourant = this.jeu.prochainJoueur();
    }

    /** Méthode qui renvoie le joueur courant.
     * @return : le joueur à qui c'est le tour de jouer.
     */
    public Joueur getJoueurCourant() {
        return this.joueurCourant;
    }

    /** Méthode qui s'occupe de terminer une partie.
     */
    public void PartieFinie() {
        this.plateau.removeAll();
        this.menu.afficheAccueil();
        this.menu.setEnableButtonJouer(true);
    }

    /** Méthode qui créer le plateau du jeu.
     */
    public void creerPlateau() {

        // On crée un plateau et on le paramètre.
        this.couloirs = new JPanel();
        this.couloirs.setBackground(new Color(69, 46, 43));
        this.couloirs.setLayout(null);

        // On le rempli.
        this.remplirPlateau();

        // On s'occupe des placements des couloirs.
        GridLayout grid = new GridLayout(7,7);
        this.couloirs.setLayout(grid);

        // On l'ajoute au labyrinthe et on retourne ce plateau rempli
        this.plateau.add(this.couloirs, BorderLayout.CENTER);
    }

    /** Méthode qui ajoute les couloirs avec leurs événements lorsque l'on clique sur eux.
     * A chaque clique, on déplace le pion du joueur courant vers ce couloir et on regarde si la partie est finie.
     */    
    private void remplirPlateau() {

        couloirsModele = jeu.couloirs();
        couloirsVue = new ArrayList<VCouloir>();
        for (int i = 0; i < couloirsModele.size(); i++) {
            VCouloir vcouloir = new VCouloir(couloirsModele.get(i), i / 7, i % 7);
            vcouloir.addActionListener((ActionEvent evt) -> {
                this.joueurCourant.deplacePion(new Position(vcouloir.getcordX(), vcouloir.getcordY()));
                if(this.jeu.aGagne(this.joueurCourant)) {
                    this.PartieFinie();
                }
            });

            this.couloirs.add(vcouloir);
            couloirsVue.add(vcouloir);
        }
        this.setEtatVue(false);
    }

    /** Méthode qui prend en paramètre un etat et modifie l'état du bouton "Terminer mon tour".
     * @param etat > Vrai, le bouton est activé. Faux, il est désactivé.
     */
    public void setEtatBtnFiniTour(boolean etat) {
        this.menu.setEnableButtonTour(etat);
    }

    /** Méthode qui prend en paramètre un etat et actualise l'état des couloirs et des boutons d'insertions d'un couloir.
     * @param etat > Vrai, les boutons d'insertions sont désactivé, et les couloirs actifs. Inversement si c'est faux.
     */
    public void setEtatVue(boolean etat) {
        this.plateau.setEtatButton(!etat);
        for(int i = 0; i < couloirsVue.size();i++) {
            couloirsVue.get(i).setEnabled(etat);
        }
    }

    /** Méthode qui prend en paramètre un état et actualise la fenêtre.
     * @param etat > Boolean pour actualiser les etats des différentes composants (boutons, plateau, etc...)
     */
    public void refresh(boolean etat) {
        this.couloirs.removeAll();
        this.remplirPlateau();
        this.couloirs.revalidate();
        this.couloirs.repaint();
        this.menu.refreshTour(etat);
        this.setEtatVue(etat);
    }
}

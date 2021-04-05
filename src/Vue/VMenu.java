package vue;

// On importe les librairies..
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.JeuImpl;
import modele.Orientation;

/** Classe qui hérite des propriétés d'un JPanel et modélise le menu du jeu..
 */
public class VMenu extends JPanel {

    // On déclare quelques variables...
    private VJeu jeuVue;
    private JeuImpl jeuModele;
    private JPanel header, accueil, tour;
    private JButton btnQuitter, btnReduire, btnJouer, btnTour, btnRotationGauche, btnRotationDroite;
    private ButtonRadio joueurNb2, joueurNb3, joueurNb4;

    /** Constructeur de la classe VMenu qui modélise le menu du jeu.
     * @param jeuVue : la fenêtre du jeu.
     * @param jeuModele : le modèle du jeu.
     */
    public VMenu(VJeu jeuVue, JeuImpl jeuModele) {
        // On paramètre le Menu.
        this.setPreferredSize(new Dimension(300, 500));
        this.setBackground(new Color(77, 51, 48));
        this.setLayout(null);

        this.jeuVue = jeuVue;
        this.jeuModele = jeuModele;

        creerHeader();
        creerAccueil();
    }

    /** Méthode qui créer le header du jeu contenant les boutons de la fenêtre et le logo.
     */
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
        this.btnReduire = new ButtonReduire(this.jeuVue);
        this.header.add(this.btnReduire);
        this.btnReduire.setBounds(235, 20, 18, 18);

        // On crée le logo.
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(VJeu.class.getResource("/img/logo.png")));
        this.header.add(logo);
        logo.setBounds(20, 60, 260, 70);

        // On ajoute l'entête au menu et on le positionne.
        this.header.setBounds(0, 0, 300, 150);
        this.add(this.header);
    }

    /** Méthode qui va créer le panel d'accueil du jeu avec les règles et le choix du nombre de joueurs dans la partie.
     * On y ajoute également le bouton "Jouer" pour commencer la partie.
     */
    public void creerAccueil() {
        this.accueil = new JPanel();
        this.accueil.setBackground(new Color(77, 51, 48));
        this.accueil.setLayout(null);
        
        // On déclare des label.
        JLabel regles;

        // On crée un label qui affiche les règles.
        regles = new JLabel();
        regles.setIcon(new ImageIcon(VJeu.class.getResource("/img/regles.png")));
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
        this.btnJouer = new ButtonJouer(this.jeuVue, this);
        this.accueil.add(this.btnJouer);
        this.btnJouer.setBounds(0, 250, 250, 42);
        
        this.add(this.accueil);
        this.accueil.setBounds(25, 150, 250, 350);
    }

    /** Méthode qui va créer le panel s'occupant du tour d'un joueur avec les informations
     * de savoir qui doit jouer, l'objectif à chercher pour le joueur ou encore la rotation du couloir supplémentaire.
     * On y ajoute également un bouton "Terminer mon tour" qui s'occupe de terminer le tour du joueur si jamais il ne veut pas se déplacer.
     */
    public void creerPanelTour() {

        this.tour = new JPanel();
        this.tour.setBackground(new Color(77, 51, 48));
        this.tour.setLayout(null);
        
        // On déclare des label.
        JLabel img_tour, label_joueur, label_objectif;

        // On crée un label qui affiche les règles.
        img_tour = new JLabel();
        img_tour.setIcon(new ImageIcon(VJeu.class.getResource("/img/tour.png")));
        this.tour.add(img_tour);
        img_tour.setBounds(0, 0, 250, 191);

        // On crée un label qui affiche les règles.
        label_joueur = new JLabel();
        ImageIcon pion = new ImageIcon(VJeu.class.getResource("/img/" + this.jeuVue.getJoueurCourant().getPion().getCouleur() + ".png"));
        Image img_pion = pion.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        label_joueur.setIcon(new ImageIcon(img_pion));
        this.tour.add(label_joueur);
        label_joueur.setBounds(120, -6, 25, 25);

        // On crée un label qui affiche l'objectif à chercher.
        if(!this.jeuVue.getJoueurCourant().getObjectifs().empty()) {
            label_objectif = new JLabel();
            ImageIcon objectif = new ImageIcon(VJeu.class.getResource("/img/objectifs/"+ this.jeuVue.getJoueurCourant().getObjectifs().peek() + ".png"));
            Image img_objectif = objectif.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
            label_objectif.setIcon(new ImageIcon(img_objectif));
            this.tour.add(label_objectif);
            label_objectif.setBounds(110, 28, 25, 25);
        }

        Orientation tabOrientation[] = {Orientation.EST, Orientation.SUD, Orientation.OUEST, Orientation.NORD};

        ButtonRotation rotationDroite = new ButtonRotationDroite();
        this.tour.add(rotationDroite);
        rotationDroite.setBounds(40, 145, 35, 35);
        rotationDroite.addActionListener((ActionEvent evt) -> {
            this.jeuModele.getSupplementaire().setOrientation(tabOrientation[(Arrays.asList(tabOrientation).indexOf(this.jeuModele.getSupplementaire().getOrientation()) + 1) % 4]);
            this.refreshTour(false);
        });
        this.btnRotationDroite = rotationDroite;

        VCouloir supp = new VCouloir(this.jeuModele.getSupplementaire(), -1, -1);
        supp.setEnabled(false);
        this.tour.add(supp);
        supp.setBounds(95, 135, 57, 57);

        ButtonRotation rotationGauche = new ButtonRotationGauche();
        this.tour.add(rotationGauche);
        rotationGauche.setBounds(172, 145, 35, 35);
        rotationGauche.addActionListener((ActionEvent evt) -> {
            if(this.jeuModele.getSupplementaire().getOrientation() == Orientation.EST) {
                this.jeuModele.getSupplementaire().setOrientation(Orientation.NORD);
            } else {
                this.jeuModele.getSupplementaire().setOrientation(tabOrientation[Arrays.asList(tabOrientation).indexOf(this.jeuModele.getSupplementaire().getOrientation()) - 1]);
            }
            this.refreshTour(false);
        });
        this.btnRotationGauche = rotationGauche;

        // On crée le bouton Jouer qui lance la partie.
        this.btnTour = new ButtonFinTour(this.jeuVue, this);
        this.tour.add(this.btnTour);
        this.btnTour.setBounds(0, 250, 250, 42);

        this.add(this.tour);
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

    /** Méthode qui renvoie le nombre de joueur séléctionner pour configurer la partie.
     * @return : le nombre de joueurs.
     */
    public int getNbJoueurs() {
        if(this.joueurNb2.isSelected()) {
            return 2;
        } else if(this.joueurNb3.isSelected()) {
            return 3;
        } else {
            return 4;
        }
    }

    /** Méthode qui prend en paramètre un état et actualise l'état du bouton "Jouer".
     * @param etat : Vrai, le bouton est activé, faux il ne l'est pas.
     */
    public void setEnableButtonJouer(boolean etat) {
        this.btnJouer.setEnabled(etat);
    }

    /** Méthode qui prend en paramètre un état et actualise l'état du bouton "Terminer mon tour".
     * @param etat : Vrai, le bouton est activé, faux il ne l'est pas.
     */
    public void setEnableButtonTour(boolean etat) {
        this.btnTour.setEnabled(etat);
    }

    /** Méthode qui s'occupe d'actualiser le menu avec les informations de l'accueil.
     */
    public void refreshMenuAccueil() {
        this.removeAll();
        this.creerHeader();
        this.creerAccueil();
        this.setEnableButtonJouer(true);
        this.revalidate();        
        this.repaint();
    }

    /** Méthode qui s'occupe d'actualiser le menu avec le panel des tours d'un joueur.
     */
    public void refreshMenuTour() {
        this.removeAll();
        this.creerHeader();
        this.creerPanelTour();
        this.setEnableButtonTour(false);
        this.revalidate();        
        this.repaint();
    }  

    /** Méthode qui s'occupe d'actualiser les composants du panel de tour.
     * @param etat : Etat pour mettre à jour le bouton du tour.
     */
    public void refreshTour(boolean etat) {
        this.tour.removeAll();
        this.creerPanelTour();
        this.btnTour.setEnabled(etat);
        this.btnRotationDroite.setVisible(!etat);
        this.btnRotationGauche.setVisible(!etat);
        this.tour.revalidate();        
        this.tour.repaint();
    }    
}

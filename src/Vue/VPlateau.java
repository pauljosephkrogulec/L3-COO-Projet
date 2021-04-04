package Vue;

// On importe les librairies..
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// On importe la classe JeuImpl depuis le modèle..
import Modele.JeuImpl;

/** Classe qui hérite des propriétés d'un JPanel et modélise le plateau du jeu..
 */
public class VPlateau extends JPanel {

    // On déclare quelques variables...
    private VJeu jeuVue;
    private ArrayList<JButton> listBoutons;

    /** Constructeur de la classe VPlateau qui configure de panel du plateau.
     * @param jeuVue : la fenêtre du jeu du labyrinthe.
     * @param HAUTEUR : la hauteur de la fenêtre.
     */
    public VPlateau(VJeu jeuVue, int HAUTEUR) {
        this.setBackground(new Color(69, 46, 43));
        this.setPreferredSize(new Dimension(500, HAUTEUR));
        this.setLayout(new BorderLayout());

        this.jeuVue = jeuVue;
        this.listBoutons = new ArrayList<JButton>();
    }

    /** Méthode qui prend en paramètre le modèle du jeu et va créer et ajouter les boutons d'insertions
     * d'un couloir au sein du plateau.
     * @param jeuModele : le modèle du Jeu.
     * @param menu : le menu du jeu.
     */
    public void creerButtonInsertion(JeuImpl jeuModele, VMenu menu) {

        // On déclare les composants graphiques nécéssaires..
        JPanel top, left, right, bottom;
        InsertionPlateau btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;

        // On initialise le panel qui contiendra les boutons en haut du plateau.
        top = new JPanel();
        top.setBackground(new Color(69, 46, 43));
        top.setPreferredSize(new Dimension(500, 50));
        top.setLayout(null);

        // On y ajoute les 3 boutons d'insertions qui lui correspond.
        btn0 = new InsertionTop(this.jeuVue, jeuModele, menu, 0, 0);
        btn0.setBounds(115, 5, 42, 42);
        top.add(btn0);
        listBoutons.add(btn0);

        btn1 = new InsertionTop(this.jeuVue, jeuModele, menu, 1, 0);
        btn1.setBounds(227, 5, 42, 42);
        top.add(btn1);
        listBoutons.add(btn1);

        btn2 = new InsertionTop(this.jeuVue, jeuModele, menu, 2, 0);
        btn2.setBounds(341, 5, 42, 42);
        top.add(btn2);
        listBoutons.add(btn2);

        // On place le panel en haut.
        this.add(top, BorderLayout.NORTH);
        
        // On initialise le panel qui contiendra les boutons à gauche du plateau.
        left = new JPanel();
        left.setBackground(new Color(69, 46, 43));
        left.setPreferredSize(new Dimension(50, 500));
        left.setLayout(null);

        // On y ajoute les 3 boutons d'insertions qui lui correspond.
        btn3 = new InsertionLeft(this.jeuVue, jeuModele, menu, 3, 0);
        btn3.setBounds(5, 70, 42, 42);
        left.add(btn3);
        listBoutons.add(btn3);

        btn4 = new InsertionLeft(this.jeuVue, jeuModele, menu, 4, 0);
        btn4.setBounds(5, 183, 42, 42);
        left.add(btn4);
        listBoutons.add(btn4);

        btn5 = new InsertionLeft(this.jeuVue, jeuModele, menu, 5, 0);
        btn5.setBounds(5, 295, 42, 42);
        left.add(btn5);
        listBoutons.add(btn5);
        
        // On place le panel à gauche.
        this.add(left, BorderLayout.WEST);
        
        // On initialise le panel qui contiendra les boutons à droite du plateau.
        right = new JPanel();
        right.setBackground(new Color(69, 46, 43));
        right.setPreferredSize(new Dimension(50, 500));
        right.setLayout(null);

        // On y ajoute les 3 boutons d'insertions qui lui correspond.
        btn6 = new InsertionRight(this.jeuVue, jeuModele, menu, 8, 0);
        btn6.setBounds(5, 70, 42, 42);
        right.add(btn6);
        listBoutons.add(btn6);

        btn7 = new InsertionRight(this.jeuVue, jeuModele, menu, 7, 0);
        btn7.setBounds(5, 183, 42, 42);
        right.add(btn7);
        listBoutons.add(btn7);

        btn8 = new InsertionRight(this.jeuVue, jeuModele, menu, 6, 0);
        btn8.setBounds(5, 295, 42, 42);
        right.add(btn8);
        listBoutons.add(btn8);

        // On place le panel à droite.
        this.add(right, BorderLayout.EAST);
        
        // On initialise le panel qui contiendra les boutons en bas du plateau.
        bottom = new JPanel();
        bottom.setBackground(new Color(69, 46, 43));
        bottom.setPreferredSize(new Dimension(500, 50));
        bottom.setLayout(null);

        // On y ajoute les 3 boutons d'insertions qui lui correspond.
        btn9 = new InsertionBottom(this.jeuVue, jeuModele, menu, 11, 0);
        btn9.setBounds(115, 5, 42, 42);
        bottom.add(btn9);
        listBoutons.add(btn9);

        btn10 = new InsertionBottom(this.jeuVue, jeuModele, menu, 10, 0);
        btn10.setBounds(227, 5, 42, 42);
        bottom.add(btn10);
        listBoutons.add(btn10);

        btn11 = new InsertionBottom(this.jeuVue, jeuModele, menu, 9, 0);
        btn11.setBounds(341, 5, 42, 42);
        bottom.add(btn11);
        listBoutons.add(btn11);

        // On place le panel en bas.
        this.add(bottom, BorderLayout.SOUTH);
    }

    /** Méthode qui prend en paramètre un boolean et actualise l'ensemble des boutons d'insertion du plateau.
     * @param etat : Vrai, les boutons sont visibles. Faux, ils ne le sont pas.
     */
    public void setEtatButton(boolean etat) {
        for(int i = 0; i < listBoutons.size(); i++) {
            listBoutons.get(i).setVisible(etat);
        }
    }

    /** Méthode qui affiche le message de fin de la partie et le gagnant.
     */
    public void afficheMessageGagnant() {

        JPanel messageFin = new JPanel();
        messageFin.setBackground(new Color(69, 46, 43));
        messageFin.setLayout(null);

        // On crée un label qui affiche le pion gagnant.
        JLabel pionGagnant = new JLabel();
        pionGagnant.setIcon(new ImageIcon(VJeu.class.getResource("../img/" + this.jeuVue.getJoueurCourant().getPion().getCouleur() + ".png")));
        messageFin.add(pionGagnant);
        pionGagnant.setBounds(192, 205, 30, 30);

        // On crée un label qui affiche le pion gagnant.
        JLabel msgGagnant = new JLabel();
        msgGagnant.setIcon(new ImageIcon(VJeu.class.getResource("../img/message_fin.png")));
        messageFin.add(msgGagnant);
        msgGagnant.setBounds(40, 190, 420, 100);

        this.add(messageFin);
    }
}

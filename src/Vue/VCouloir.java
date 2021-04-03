package Vue;

// On importe les librairies..
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;

// On importe quelques classes du modèle..
import Modele.Couloir;
import Modele.Pion;

/** Classe qui hérite des propriétés d'un JButton et modélise un couloir avec ses événements..
 */
public class VCouloir extends JButton implements MouseListener {

    // On déclare quelques variables...
    private Couloir couloir;
    private int cordX, cordY;

    /** Constructeur de la classe VCouloir qui modélise un couloir selon les paramètres de son modèle.
     * @param couloir > le couloir au sein du Modele.
     * @param x > sa coordonée X au sein du plateau.
     * @param y > sa coordonée Y au sein du plateau.
     */
    public VCouloir(Couloir couloir, int x, int y) {
        super();

        // On paramètre le bouton.
        this.setBackground(new Color(69, 46, 43));
        this.setBorder(BorderFactory.createBevelBorder(0,new Color(69, 46, 43), new Color(69, 46, 43)));
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setLayout(null);

        // On récupère ses paramètres.
        this.couloir = couloir;
        this.cordX = x;
        this.cordY = y;
        this.addMouseListener(this);
    }
    
    /** Méthode qui renvoie la coordonée X du couloir au sein de son plateau.
     * @return : la coordonée X.
     */
    public int getcordX() {
        return this.cordX;
    }

    /** Méthode qui renvoie la coordonée Y du couloir au sein de son plateau.
     * @return : la coordonée Y.
     */
    public int getcordY() {
        return this.cordY;
    }

    /** Méthode qui prend en paramètre les composants graphiques et déssine les composants du couloir.
     * (un couloir est composé d'une image de sa forme, l'objectif qui le compose, et les joueurs présents sur celui-ci).
     * @param g > le composant graphique.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // On déssine la forme du couloir (COUDE, TE, DROIT)
        g.drawImage(new ImageIcon(VCouloir.class.getResource("../img/couloirs/" + couloir.getForme() + "_" + couloir.getOrientation() + ".gif")).getImage(), 3, 3, 50, 50, this);

        // Si celui-ci possède un objectif, on le déssine.
        if(couloir.getObjectif() != null) {
            g.drawImage(new ImageIcon(VCouloir.class.getResource("../img/objectifs/" + couloir.getObjectif() + ".png")).getImage(), 18, 18, 20, 20, this);
        }

        // On déssine également l'ensemble des pions qui sont sur lui.
        for(Pion pion : couloir.getPions()) {
            g.drawImage(new ImageIcon(VCouloir.class.getResource("../img/" + pion.getCouleur() + ".png")).getImage(), 20, 20, 16, 16, this);
        }
    }

    /** Méthode qui prend en paramètre un événement de sourie et actualise les bordures du boutons, lorsque la sourie entre dans le couloir.
     * @param e > l'événement de la sourie.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBorder(BorderFactory.createBevelBorder(0,new Color(236, 192, 111), new Color(236, 192, 111)));
    }

    /** Méthode qui prend en paramètre un événement de sourie et actualise les bordures du boutons, lorsque la sourie sort du couloir.
     * @param e > l'événement de la sourie.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setBorder(BorderFactory.createBevelBorder(0,new Color(69, 46, 43), new Color(69, 46, 43)));
    }

    // Autres méthodes de MouseListener non-utilisées.
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
    
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/** Constructeur de la classe JeuFactory qui paramètre la fenêtre et ses fonctionnalités.
 */
public class JeuFactory extends JFrame implements ActionListener {

    // On déclare quelques variables...
    private static int LONGUEUR = 800;
    private static int HAUTEUR = 500;
    private JPanel menu, labyrinthe;
    private static JButton[] arrayBtn;
    
    private static final ImageIcon[] imgTab = {
        new ImageIcon(JeuFactory.class.getResource("img/CASE0.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE1.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE2.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE3.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE4.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE5.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE6.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE7.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE8.gif")), 
        new ImageIcon(JeuFactory.class.getResource("img/CASE9.gif"))
    };

    public JeuFactory() {
        // On initialise la fenêtre.
        super("Jeu du Labyrinthe");
        this.setSize(LONGUEUR, HAUTEUR);


        this.menu = creerMenu();
        this.getContentPane().add(this.menu, "East");

        this.labyrinthe = creerLabyrinthe();
        this.getContentPane().add(this.labyrinthe, "West");

        // On paramètre la fenêtre.
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /** Méthode qui créer le Menu du jeu.
     */
    public JPanel creerMenu() {

        JPanel m = new JPanel();
        // On paramètre le Menu.
        m.setPreferredSize(new Dimension(300, HAUTEUR));
        m.setBackground(new Color(29, 30, 33));
        m.setLayout(null);

        return m;
    }

    /** Méthode qui créer le Menu du jeu.
     */
    public JPanel creerLabyrinthe() {

        JPanel l = new JPanel();
        // On paramètre le Menu.
        l.setPreferredSize(new Dimension(LONGUEUR - 320, HAUTEUR));
        l.setLayout(null);

        GridLayout grid = new GridLayout(8, 8);
        l.setLayout(grid);

        arrayBtn = new JButton[49];
        // add JButtons dynamically
        for(int i=0; i < arrayBtn.length; i++) {
            arrayBtn[i] = new JButton(Integer.toString(i));
            l.add(arrayBtn[i]);
        }

        return l;
    }

    private Jeu creeJeu() {
        return new JeuImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

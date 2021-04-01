package Vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Modele.*;

public class VLabyrinthe extends JPanel {

    private VJeu vjeu;

    public VLabyrinthe(VJeu vjeu, int H) {
        super();        
        this.setBackground(new Color(69, 46, 43));
        this.setPreferredSize(new Dimension(500, H));
        this.setLayout(new BorderLayout());

        this.vjeu = vjeu;
    }

    public void creerLabyrinthe(JeuImpl jeu) {
        JPanel top = new JPanel();
        top.setBackground(new Color(69, 46, 43));
        top.setPreferredSize(new Dimension(500, 50));
        top.setLayout(null);

        JButton btn0 = new InsertionTop(this.vjeu, jeu, 0, 0);
        btn0.setBounds(115, 5, 42, 42);
        top.add(btn0);

        JButton btn1 = new InsertionTop(this.vjeu, jeu, 1, 0);
        btn1.setBounds(227, 5, 42, 42);
        top.add(btn1);

        JButton btn2 = new InsertionTop(this.vjeu, jeu, 2, 0);
        btn2.setBounds(341, 5, 42, 42);
        top.add(btn2);

        this.add(top, BorderLayout.NORTH);
        
        JPanel left = new JPanel();
        left.setBackground(new Color(69, 46, 43));
        left.setPreferredSize(new Dimension(50, 500));
        left.setLayout(null);

        JButton btn3 = new InsertionLeft(this.vjeu, jeu, 3, 0);
        btn3.setBounds(5, 70, 42, 42);
        left.add(btn3);

        JButton btn4 = new InsertionLeft(this.vjeu, jeu, 4, 0);
        btn4.setBounds(5, 183, 42, 42);
        left.add(btn4);

        JButton btn5 = new InsertionLeft(this.vjeu, jeu, 5, 0);
        btn5.setBounds(5, 295, 42, 42);
        left.add(btn5);
        
        this.add(left, BorderLayout.WEST);
        
        JPanel right = new JPanel();
        right.setBackground(new Color(69, 46, 43));
        right.setPreferredSize(new Dimension(50, 500));
        right.setLayout(null);

        JButton btn6 = new InsertionRight(this.vjeu, jeu, 8, 0);
        btn6.setBounds(5, 70, 42, 42);
        right.add(btn6);

        JButton btn7 = new InsertionRight(this.vjeu, jeu, 7, 0);
        btn7.setBounds(5, 183, 42, 42);
        right.add(btn7);

        JButton btn8 = new InsertionRight(this.vjeu, jeu, 6, 0);
        btn8.setBounds(5, 295, 42, 42);
        right.add(btn8);

        this.add(right, BorderLayout.EAST);
        
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(69, 46, 43));
        bottom.setPreferredSize(new Dimension(500, 50));
        bottom.setLayout(null);

        JButton btn9 = new InsertionBottom(this.vjeu, jeu, 11, 0);
        btn9.setBounds(115, 5, 42, 42);
        bottom.add(btn9);

        JButton btn10 = new InsertionBottom(this.vjeu, jeu, 10, 0);
        btn10.setBounds(227, 5, 42, 42);
        bottom.add(btn10);

        JButton btn11 = new InsertionBottom(this.vjeu, jeu, 9, 0);
        btn11.setBounds(341, 5, 42, 42);
        bottom.add(btn11);

        this.add(bottom, BorderLayout.SOUTH);
    }    
}

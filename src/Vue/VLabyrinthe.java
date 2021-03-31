package Vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VLabyrinthe extends JPanel {

    public VLabyrinthe(int H) {
        super();
        
        this.setBackground(new Color(68, 79, 116));
        this.setPreferredSize(new Dimension(500, H));
        this.setLayout(new BorderLayout());
    }

    public void creerLabyrinthe() {
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

        this.add(top, BorderLayout.NORTH);
        
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
        
        this.add(left, BorderLayout.WEST);
        
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

        this.add(right, BorderLayout.EAST);
        
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

        this.add(bottom, BorderLayout.SOUTH);
    }    
}

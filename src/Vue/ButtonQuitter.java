package Vue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/** Classe qui herite de JButton et modélise le bouton Quitter avec son événement..
 */
public class ButtonQuitter extends JButton implements ActionListener {

    /** Constructeur de la class btnQuitter.
    */
    public ButtonQuitter() {
        super();
        this.setFocusable(false);
        this.setOpaque(true);
        this.setIcon(new ImageIcon(ButtonQuitter.class.getResource("../img/close.png")));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addActionListener(this);
    }

    /** Méthode de ActionEvent qui quitte la fenêtre. 
     * @param e : l'événement.
     */
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}

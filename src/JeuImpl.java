
// On importe les librairies.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeuImpl implements Jeu, ActionListener {

    // Déclaration des variables...
    private CouloirMobile supplementaire;
    private PositionInsertion positionOrigine;
    private List<Joueur> joueurs;
    private Map<Couleur, Pion> pions;
    private Objectif[] objectifs;
    private CouloirMobile[] couloirsMobiles;
    private Plateau plateau;
    private int i;

    public JeuImpl() {
        preparer();
    }

    @Override
    public void modifierCouloir(PositionInsertion pos, Orientation orientation) {
        assert pos != positionOrigine;
        supplementaire = plateau.modifierCouloirs(pos, supplementaire);
        positionOrigine = pos.oppose();
    }

    @Override
    public void enregistrer(Joueur joueur, Couleur couleur) {
        joueur.recevoirPion(pions.get(couleur));
    }

    @Override
    public List<Couloir> couloirs() {
        List<Couloir> cs = new ArrayList<>();
        for (Couloir[] couloirs : plateau.getCouloirs()) {
            for (Couloir c : couloirs) {
                cs.add(c);
            }
        }
        return cs;
    }

    private void preparer() {
        Random r = new Random();
        this.joueurs = new ArrayList<>();
        this.pions = new HashMap<>();
        this.objectifs = Objectif.values();
        this.supplementaire = new CouloirMobile(Orientation.SUD, Forme.DROIT, null, false);
        this.couloirsMobiles = new CouloirMobile[34];
        int i, obj = 12;
        for (i = 0; i < 4; i++) {
            Joueur j = new JoueurImpl(14, this);
            int x = 0, y = 0;
            if (i == 1)
                y = 6;
            else if (i == 2)
                x = 6;
            else if (i == 3) {
                x = 6;
                y = 6;
            }

            Pion p = new PionImpl(this.plateau, new Position(x, y), new Position(x, y));
            this.pions.put(Couleur.values()[i], p);
            j.recevoirPion(p);
            this.joueurs.add(j);
        }
        this.objectifs = Objectif.values();
        for (i = 0; i < 12; i++)
            this.couloirsMobiles[i] = new CouloirMobile(Orientation.values()[r.nextInt(4)], Forme.DROIT, null, false);
        for (; i < 20; i++) {
            if (i < 19)
                this.couloirsMobiles[i] = new CouloirMobile(Orientation.values()[r.nextInt(4)], Forme.TE,
                        objectifs[obj++], false);
            else
                this.couloirsMobiles[i] = new CouloirMobile(Orientation.values()[r.nextInt(4)], Forme.TE, null, false);
        }
        for (; i < 34; i++) {
            if (i < 19)
                this.couloirsMobiles[i] = new CouloirMobile(Orientation.values()[r.nextInt(4)], Forme.COUDE,
                        objectifs[obj++], false);
            else
                this.couloirsMobiles[i] = new CouloirMobile(Orientation.values()[r.nextInt(4)], Forme.COUDE, null,
                        false);
        }
        this.plateau = new Plateau(this.couloirsMobiles);
    }

    public static CouloirMobile[] RandomizeArray(CouloirMobile[] array) {
        Random rgen = new Random(); // Random number generator

        for (int i = 0; i < array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            CouloirMobile temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    private void jouer() {
        Joueur joueur;
        do {
            joueur = prochainJoueur();
            joueur.joue();
        } while (!aGagne(joueur));
    }

    private Joueur prochainJoueur() {
        return joueurs.get((this.i)++ % joueurs.toArray().length);
    }

    private boolean aGagne(Joueur joueur) {
        return joueur.getPion().getPositionInitiale() == joueur.getPion().getPositionCourante()
                && joueur.objectifsFinis();
    }

    public JButton jouerButton() {
        JButton j = new JButton("Jouer");

        j.addActionListener(this);
        return j;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jouer();
    }
}

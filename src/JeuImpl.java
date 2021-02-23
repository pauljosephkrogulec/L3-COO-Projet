import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JeuImpl implements Jeu {

    public JeuImpl() {
        preparer();
    }

    private CouloirMobile supplementaire;
    private PositionInsertion positionOrigine;
    private List<Joueur> joueurs;
    private Map<Couleur, Pion> pions;
    private Objectif[] objectifs;
    private CouloirMobile[] couloirsMobiles;
    private Plateau plateau;
    private int i;
    @Override
    public void modifierCouloir(PositionInsertion pos, Orientation orientation) {
        assert pos != positionOrigine;
        supplementaire = plateau.modifierCouloirs(pos, supplementaire);
        positionOrigine = pos.oppose();
        for (Pion p : supplementaire.getPions()) {
            PionImpl pi = (PionImpl) p;
            pi.poserA(pos.getPos());
        }
    }

    @Override
    public void enregistrer(Joueur joueur, Couleur couleur) {
        joueur.recevoirPion(pions.get(couleur));
    }

    @Override
    public List<Couloir> couloirs() {
        return null ;
    }

    private void preparer() {
        i = 0;
        this.positionOrigine = null;
        this.supplementaire = new CouloirMobile(null,null,null,false);
        this.joueurs = new ArrayList<>();
        this.pions = new HashMap<>();
        this.objectifs = new Objectif[24];
        this.couloirsMobiles = new CouloirMobile[34];
        int i;
        for(i = 0;i< 4;i++) {
            Joueur j = new JoueurImpl(14,this);
            Pion p = new PionImpl(this.plateau,null,null);
            this.pions.put(Couleur.values()[i], p);
            this.joueurs.add(j);
        }
        for(i = 0;i<24;i++) this.objectifs[i] = Objectif.values()[i];
        for(i = 0;i< 34;i++) this.couloirsMobiles[i] = new CouloirMobile(null,null,null,true);
        this.plateau = new Plateau(this.couloirsMobiles);
    }

    private void jouer() {
        Joueur joueur;
        do{
            joueur = prochainJoueur();
            joueur.joue();
        } while (!aGagne(joueur));
    }

    private Joueur prochainJoueur() {
        return joueurs.get((this.i)++%joueurs.toArray().length);
    }
    private boolean aGagne(Joueur joueur){
        return true;
    }
}

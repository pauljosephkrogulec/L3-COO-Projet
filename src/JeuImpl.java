import java.util.List;
import java.util.Map;

public class JeuImpl implements Jeu {

    private CouloirMobile supplementaire;
    private PositionInsertion positionOrigine;
    private List<Joueur> joueurs;
    private Map<Couleur, Pion> pions;
    private Objectif[] objectifs;
    private CouloirMobile[] couloirsMobiles;
    private Plateau plateau;

    @Override
    public void modifierCouloir(PositionInsertion pos, Orientation orientation) {
    }

    @Override
    public void enregistrer(Joueur joueur, Couleur couleur) {
    }

    @Override
    public List<Couloir> couloirs() {
        // TODO Auto-generated method stub
        return null;
    }

    private void preparer() {

    }

    private void jouer() {

    }

    private Joueur prochainJoueur() {
        return null;
    }
}

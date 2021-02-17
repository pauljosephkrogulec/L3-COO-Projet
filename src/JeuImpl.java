import java.util.List;
import java.util.Map;

public class JeuImpl implements Jeu {

    private Couloir_Mobile supplementaire;
    private PositionInsertion positionOrigine;
    private List<Joueur> joueurs;
    private Map<Couleur, Pion> pions;
    private Objectif[] objectifs;
    private Couloir_Mobile[] couloirsMobiles;
    private Plateau plateau;

    @Override
    public void modifierCouloir(PositionInsertion pos, Orientation orientation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enregistrer(Joueur joueur, Couleur couleur) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Couloir> couloirs() {
        // TODO Auto-generated method stub
        return null;
    }
}

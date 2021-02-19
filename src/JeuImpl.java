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
        assert pos != positionOrigine;
        supplementaire = plateau.modifierCouloirs(pos, supplementaire);
        positionOrigine = pos.oppose();
    }

    @Override
    public void enregistrer(Joueur joueur, Couleur couleur) {
    }

    @Override
    public List<Couloir> couloirs() {
        return null;
    }

    private void preparer() {

    }

    private void jouer() {
        Joueur joueur;
        do{
            joueur = prochainJoueur();
            joueur.joue();
        } while (!aGagne(joueur));
    }

    private Joueur prochainJoueur() {
        return null;
    }
    private boolean aGagne(Joueur joueur){
        return true;
    }
}

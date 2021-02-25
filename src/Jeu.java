import java.util.List;

public interface Jeu {
    void modifierCouloir(PositionInsertion pos, Orientation orientation);
    void enregistrer(Joueur joueur, Couleur couleur);
    List<Couloir> couloirs();
}
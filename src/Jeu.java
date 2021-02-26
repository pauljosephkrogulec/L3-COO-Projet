// On importe les librairies.
import java.util.List;

/** Interface modélisant le jeu.
 */
public interface Jeu {

    // Déclarations des méthodes essentielles...
    void modifierCouloir(PositionInsertion pos, Orientation orientation);
    void enregistrer(Joueur joueur, Couleur couleur);
    List<Couloir> couloirs();
}
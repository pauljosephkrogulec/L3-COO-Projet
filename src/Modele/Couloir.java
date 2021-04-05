package modele;

// On importe les librairies.
import java.util.List;

/** Interface modélisant un couloir.
 */
public interface Couloir{

    // Déclarations des méthodes essentielles...
    public Orientation getOrientation();
    public void setOrientation(Orientation new_orientation);
    public Forme getForme();
    public Objectif getObjectif();
    public List<Pion> getPions();
    public void setPion(Pion p);
    public void delPion(Pion p);
}
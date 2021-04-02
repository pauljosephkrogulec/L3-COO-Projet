package Modele;
// On importe les librairies.
import java.util.List;

/** Interface modélisant un couloir.
 */
public interface Couloir{

    // Déclarations des méthodes essentielles...
    public Orientation getOrientation();
    public Forme getForme();
    public Objectif getObjectif();
    public List<Pion> getPions();
    public void setPions(Pion p);  
}
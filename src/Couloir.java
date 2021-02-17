import java.util.List;

public interface Couloir {
    public Orientation getOrientation();
    public Forme getForme();
    public Objectif getObjectif();
    public List<Pion> getPions();    
}

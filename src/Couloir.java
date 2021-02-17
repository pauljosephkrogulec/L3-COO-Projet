import java.util.List;

interface Couloir {
    public Orientation getOrientation();
    public Forme getForme();
    public Objectif getObjectif();
    public List<Pion> getPions();    
}

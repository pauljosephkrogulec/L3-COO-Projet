import java.util.List;

public class CouloirImpl implements Couloir {

    protected Orientation orientation;
    protected Forme forme;
    protected Objectif objectif;
    protected List<Pion> pions;

    public CouloirImpl(Orientation orientation, Forme forme, Objectif objectif) {
        this.orientation = orientation;
        this.forme = forme;
        this.objectif = objectif;
        this.pions = null;
    }

    @Override
    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public Forme getForme() {
        return this.forme;
    }

    @Override
    public Objectif getObjectif() {
        return this.objectif;
    }

    @Override
    public List<Pion> getPions() {
        return this.pions;
    }
    
    @Override
    public void setPions(Pion p){
        pions.add(p);
    }
    
}

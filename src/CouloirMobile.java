public class CouloirMobile extends CouloirImpl {

    private boolean posee;

    public CouloirMobile(Orientation orientation, Forme forme, Objectif objectif, boolean posee) {
        super(orientation, forme, objectif);
        this.posee = posee;
    }

    public boolean changeOrientation(Orientation orientation) {
        if(!this.posee) {
            this.orientation = orientation;
            return true;
        }
        return false;
    }
}

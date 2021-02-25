public enum PositionInsertion {
    N1(1,0,1), N2(2,0,3), N3(3,0,5), E1(4,1,0), E2(5,3,0), E3(6,5,0), S3(7,6,5), S2(8,6,3), S1(9,6,1), O3(10,5,6), O2(11,3,6), O1(12,1,6);

    private int place;
    private Position p;

    public PositionInsertion oppose() {
        return PositionInsertion.values()[(12 - this.place)]; 
    }
    public Position getPos(){
        return this.p;
    }
    private PositionInsertion(int place,int x,int y) {
        this.place = place;
        this.p = new Position(x, y);
    }
}
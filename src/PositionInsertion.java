public class PositionInsertion {
    N1(0),N2(1),N3(2),E1(3),E2(4),E3(5),S3(6),S2(7),S1(8),O3(9),O2(10),O1(11);

    private int place;
    public PositionInsertion oppose() { return PositionInsertion.values()[(11-this.place)]; }
    private PositionInsertion(int place) { this.place = place;}

}
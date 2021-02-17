import java.util.Stack;

public class JoueurImpl implements Joueur {

    private int age;
    private Stack<Objectif> objectifs;
    private Jeu jeu;
    private Pion pion;

    public JoueurImpl(int age, Stack<Objectif> objectifs, Jeu jeu, Pion pion) {
        this.age = age;
        this.objectifs = objectifs;
        this.jeu = jeu;
        this.pion = pion;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void joue() {
    }

    @Override
    public void fixerObjectifs(Stack<Objectif> objectifs) {

    }

    @Override
    public void recevoirPion(Pion p) {
    }
    
    private PositionInsertion choisirPositionIntesertionCouloir() {
        return null;

    }

    private Position choisirPositionPion() {
        return null;
    }

    private Orientation choisirOrientationCouloir() {
        return null;
    }
}

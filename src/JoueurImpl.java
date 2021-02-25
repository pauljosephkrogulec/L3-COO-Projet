import java.util.Stack;

public class JoueurImpl implements Joueur {

    private int age;
    private Stack<Objectif> objectifs;
    private Jeu jeu;
    private Pion pion;

    public JoueurImpl(int age, Jeu jeu) {
        this.age = age;
        this.objectifs = null;
        this.jeu = jeu;
        this.pion = null;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public Pion getPion() {
        return this.pion;
    }

    @Override
    public void joue() {
        this.jeu.modifierCouloir(choisirPositionInsertionCouloir(), choisirOrientationCouloir());
        Objectif objectif = pion.deplacer(choisirPositionPion());
        if(objectif == this.objectifs.peek()) {
            objectifs.pop();
        }
    }

    @Override
    public void fixerObjectifs(Stack<Objectif> objectifs) {
        this.objectifs = objectifs;
    }

    @Override
    public void recevoirPion(Pion p) {
        this.pion = p;
    }

    @Override
    public boolean objectifsFinis() {
        return this.objectifs.empty();
    }
    
    private PositionInsertion choisirPositionInsertionCouloir() {
        return null;
    }

    private Position choisirPositionPion() {
        return null;
    }

    private Orientation choisirOrientationCouloir() {
        return null;
    }
}

import java.util.Stack;

public interface Joueur {
    int getAge();
    void joue();
    void fixerObjectifs(Stack<Objectif> objectifs);
    void recevoirPion(Pion p);
}

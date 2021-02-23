import java.util.Stack;

public interface Joueur {
    int getAge();
    Pion getPion();
    void joue();
    void fixerObjectifs(Stack<Objectif> objectifs);
    void recevoirPion(Pion p);
    boolean objectifsFinis();
}

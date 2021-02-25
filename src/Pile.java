public class Pile {
    private Couloir[] items;
    private int top=0;
    private int max=50;
    public Pile(){items = new Couloir[max];}
    public Couloir empiler(Couloir item){
        items[top++] = item;
        return item;
    }
    public Couloir dépiler(){
        return items[--top];
    } 
    public boolean estVide(){
        return (top == 0);
    }
}

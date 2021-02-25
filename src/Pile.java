public class Pile {
    private Position[] items;
    private int top=0;
    private int max=50;
    public Pile(){items = new Position[max];}
    public Position empiler(Position item){
        items[top++] = item;
        return item;
    }
    public Position dépiler(){
        return items[--top];
    } 
    public boolean estVide(){
        return (top == 0);
    }
}

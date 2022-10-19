import java.util.ArrayList;
class Location<T>{
    public T data;

    public Location<?> previous;
    public Location<?> next;

    //public ArrayList<Player> player = new ArrayList<>();

    public boolean tagged = false;

    public Location(T data){
        this.data = data;
        previous = null;
        next = null;
    }

    public void receive(Player player){
        if(data instanceof Property x){
            //Property property = (Property) data;
            if(!x.owner.equals(player)){
                x.collectRent(player);
            }
        }
        else if(data instanceof Railroad){

        }
        else if()
    }
}

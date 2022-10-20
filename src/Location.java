import java.util.ArrayList;
class Location<T>{
    public T data;

    public Location<T> previous;
    public Location<T> next;

    //public ArrayList<Player> player = new ArrayList<>();

    public boolean tagged = false;

    public Location(T data){
        this.data = data;
        previous = null;
        next = null;
    }

    /**
     * Try to remake this so that somehow name is overridden by BoardSpace, so this method can be empty
     */
    public String name(){
        if(data instanceof BoardSpace x){ return x.name; }
        else{ return null; }
    }

    /*public void receive(Player player){
        if(data instanceof Property x){
            //Property property = (Property) data;
            if(!x.owner.equals(player)){
                x.collectRentFrom(player);
            }
        }
        else if(data instanceof Railroad){

        }
        else if()
    }

    public void*/
}


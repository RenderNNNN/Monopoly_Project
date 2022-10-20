public class CircularlyLinkedList<T>{
    Location<T> current;

    public CircularlyLinkedList(){ current = null; }

    public boolean isEmpty(){ return current == null; }

    public void initialize(){

    }

    public void insert(T data, boolean right){
        Location<T> current = new Location<T>(data);
        if(this.current != null){
            Location<T> next = this.current; if(!right){ next = this.current.previous; }
            Location<T> previous = this.current.previous; if(!right){ previous = this.current; }

            next.previous = current;
            previous.next = current;

            current.next = next;
            current.previous = previous;
        }
        else{ //If empty
            current.next = current;
            current.previous = current;
        }
        this.current = current;
    }

    public void delete(T data){
        Location<T> current = this.current;
        current.tagged = true;
        while(true){
            current = current.next;
            if(current.equals(data)){
                Location<T> left = current.previous;
                Location<T> right = current.next;
                left.next = right;
                right.previous = left;
            }
            if(current.tagged){ current.tagged = false; break; }
        }
    }

    /*public Location<?> findName(String name){
        current.tagged = true;
        Location<?> x = current;
        while(true){ if(x in)
        }
        return null;
    }*/

    public String toString(boolean right){
        String string = "";
        current.tagged = true;
        Location<?> link = current;

        while(true){
            string += link.data + ", ";
            if(right){ link = link.next; }
            else{ link = link.previous; }
            if(link.tagged){ link.tagged = false; break; }
        }
        return "[" + string + "\b\b]";
    }

    public void examine(){
        String string = "";
        Location<?> link = current;
        for(int i = 0; i < 7; i++){
            string += link.data + ", ";
            link = link.next;
        }
        System.out.println(("[" + string + "\b\b]"));
    }
}
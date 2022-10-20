import java.util.ArrayList;
public class Player{
    /*
    0) Purple
    1) Light Blue
    2) Pink
    3) Orange
    4) Red
    5) Yellow
    6) Green
    7) Dark Blue
     */
    public String name;
    public int balance;
    public Location<BoardSpace> location;
    public int GOJFC = 0;
    public int sameMoveCounter;
    public boolean inJail = false;

    public Player(String name, int balance){
        this.name = name;
        this.balance = balance;
    }

    public void advance(String name, boolean ignoreGo){
        //Moves player to the number
        while(true){
            if(location.data.name.equals("Go") && !ignoreGo){ balance += 200; }
            if(location.data.name.equals(name)){ break; }
            location = location.next;
        }

    }

}

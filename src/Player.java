import java.util.ArrayList;
public class Player{
    //
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
    public Location<?> location;

    public Player(String name){
        this.name = name;
        this.balance = 200;
    }

    public void advance(){
        //Rolls die, updates location,
    }

}

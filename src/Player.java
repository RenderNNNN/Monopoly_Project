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

    public Player(String name){ //can we add a player number? So I can loop through the players numerically, or at least through a player array
        this.name = name;
        this.balance = 200;
    }

    public void advance(String name, boolean ignoreGo){ //add int argument to loop location.next for however many the die rolls?
        //Moves player to the number
        while(true){
            if(location.data.name.equals("Go") && !ignoreGo){ balance += 200; }
            if(location.data.name.equals(name)){ break; }
            location = location.next;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Location<BoardSpace> getLocation() {
        return location;
    }

    public void setLocation(Location<BoardSpace> location) {
        this.location = location;
    }
}

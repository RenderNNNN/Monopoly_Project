import java.util.ArrayList;

public class Railroad{
    public static final Railroad[] railroads = railroads();

    public Player owner;
    public String name = "";
    public int price = 200;

    public Railroad(String name){
        owner = null;
        this.name = name;
    }

    //Only the bank can use sell()
    public void sell(Player owner){
        //boolean here
        this.owner = owner;
        owner.balance -= price;
    }

    public void tradeTo(Player otherPlayer, Object[] price){
        for(Object x : price){
            if(x instanceof Integer y){
                owner.balance += y;
                otherPlayer.balance -= y;
            }
            else if(x instanceof Property y){
                y.sell(y.houses, 0);
                y.sell(y.hotel, 1);
                y.owner = owner;
            }
            else if(x instanceof Railroad y){ y.owner = owner; }
        }
        owner = otherPlayer;
    }

    public int getRent(Player player){
        if(!player.equals(owner)){
            int rent = 25;
            for(Railroad x : railroads){ if(!x.equals(this) && x.owner.equals(owner)){ rent *= 2; } }
            return rent;
        }
        else return 0;
    }

    private static Railroad[] railroads(){
        Railroad[] railroads = new Railroad[4];
        railroads[0] = new Railroad("Reading Railroad");
        railroads[1] = new Railroad("Pennsylvania Railroad");
        railroads[2] = new Railroad("B&O Railroad");
        railroads[3] = new Railroad("Short Line");
        return railroads;
    }
}

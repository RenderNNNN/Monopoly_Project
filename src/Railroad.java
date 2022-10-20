import java.util.ArrayList;

public class Railroad extends BoardSpace{
    public static final Railroad[] railroads = railroads();

    public Player owner;
    //public String name = "";
    public int price = 200;

    public Railroad(String name){
        super(name);
        owner = null;
        //this.name = name;
    }

    //Only the bank can use sell()
    public void sell(Player owner){
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

    @Override
    public void receive(Player player){
        int rent = 25;
        for(Railroad x : railroads){ if(x.owner.equals(owner) && !x.equals(this)){ rent *= 2; } }
        if(!player.equals(owner)){ player.balance -= rent; }
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













/*if(!player.equals(owner)){
            int rent = 25;
            for(Property x : properties[color]){
                if(!x.equals(this) && x.owner.equals(owner)){
                    rent *= 2;
                }
            }
            player.balance -= rent;
        }*/

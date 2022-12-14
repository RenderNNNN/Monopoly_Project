import java.util.ArrayList;
public class Property extends BoardSpace{
    /*
    (color * 3) - 1

    0) Purple (ai = 0) (0 * 3)
    1) Light Blue (actual index = 2) ((1) * 3) = 3
    2) Pink (ai = 5)
    3) Orange
    4) Red
    5) Yellow
    6) Green (actual index = 17) ((6) * 3) = 18
    7) Dark Blue (actual index = 20)   ((7) * 3) = 21

     */
    public static final Property[][] properties = properties();

    public Player owner;
    public boolean mortgaged;
    public int hotelPrice;

    public int houses;
    public int hotel;

    public int color;
    public int[] rent;
    public int price;
    public int mortgageValue;
    public int housePrice;
    //public String name;

    public Property(int color, int[] rent, int price, int mortgageValue, int housePrice, String name){
        super(name);
        owner = null;
        mortgaged = false;
        hotelPrice = housePrice * 5;

        this.color = color;
        this.rent = rent;
        this.price = price;
        this.mortgageValue = mortgageValue;
        this.housePrice = housePrice;
        //this.name = name;
    }

    public void mortgage(){
        for(Property x : properties[color]){
            owner.balance += (x.houses * (x.housePrice / 2)) + (x.hotel * (x.hotelPrice / 2)); x.houses = 0; x.hotel = 0;
        }
        owner.balance += mortgageValue;
        mortgaged = true;
    }

    public void unMortgage(){
        owner.balance -= mortgageValue * 1.1;
        mortgaged = false;
    }

    public void sell(Player owner){
        this.owner = owner;
        owner.balance -= price;
    }

    public String buy(int num, int type){
        boolean colorSetOwned = true;
        boolean noneMortgaged = true;
        boolean buildingsEqual = true;
        boolean buildingsEven = true;
        for(int i = 0; i < 3; i++){
            Property x = properties[color][i]; if(x == null){ continue; }
            if(!x.owner.equals(owner)){ colorSetOwned = false; break; }
            if(x.mortgaged){ noneMortgaged = false; break; }
            if(x.houses + (hotel * 4) < houses){ buildingsEven = false; break; }
            if(buildingsEqual && x.houses != houses){ buildingsEqual = false; }
        }
        boolean legalNumber = hotel == 0 && ((type == 0 && ((num > 0 && houses + num <= 4) || (num == 0 && houses + 1 <= 4)))
                || (type == 1 && houses == 4 && buildingsEqual));
        int mult = 3; if(properties[color][2] == null){ mult = 2; } int price = housePrice;
        if(num > 0 && type == 0){ price = mult * num * housePrice; }
        else if(num > 0 && type == 1){ price = mult * ((4 - houses) + 1) * housePrice; }
        boolean sufficientFunds = owner.balance - price >= 0;

        String error = "";
        if(!colorSetOwned){ error = "error 1"; }
        else if(!noneMortgaged){ error = "error 2"; }
        else if(!buildingsEqual){ error = "error 3"; }
        else if(!buildingsEven){ error = "error 4"; }
        else if(!legalNumber){ error = "error 5"; }
        else if(!sufficientFunds){ error = "error 6"; }

        if(error.equals("")){
            owner.balance -= price;
            if(num == 0 && type == 0){ houses++; }
            else if(num > 0 && type == 0){ for(Property x : properties[color]){ if(x == null){ continue; } x.houses += num; } }
            else if(num == 0){ hotel = 1; houses = 0; }
            else if(num > 0){ for(Property x : properties[color]){ if(x == null){ continue; } x.hotel = 1; x.houses = 0; } }
        }
        //System.out.println(colorSetOwned + ", " + noneMortgaged + ", " + buildingsEven + ", " + legalNumber + ", " + sufficientFunds);
        return error;
    }

    public String sell(int num, int type){
        boolean buildingsEqual = true;
        boolean buildingsEven = true;
        for(int i = 0; i < 3; i++){
            Property x = properties[color][i]; if(x == null){ continue; }
            if(x.houses + (hotel * 4) > houses){ buildingsEven = false; break; }
            if(buildingsEqual && x.houses != houses){ buildingsEqual = false; }
        }

        /*boolean a = (num == 0 && buildingsEven && houses - 1 >= 0);
        boolean b = (num > 0 && buildingsEqual && houses - num >= 0);
        boolean c = (num == 0 && hotel == 1);
        boolean d = (num > 0 && buildingsEqual && hotel == 1);*/
        /*boolean canSell = ((num == 0 && buildingsEven && houses - 1 >= 0)
                || (num > 0 && buildingsEqual && houses - num >= 0))
                || ((num == 0 && hotel == 1)
                || (num > 0 && buildingsEqual && hotel == 1));*/

        boolean legalNumber = (type == 0 && ((num == 0 && buildingsEven && houses - 1 >= 0)
                || (num > 0 && buildingsEqual && houses - num >= 0)))
                || (type == 1 && ((num == 0 && hotel == 1)
                || (num > 0 && buildingsEqual && hotel == 1)));

        String error = "";
        if(!buildingsEqual){ error = "error 1"; }
        else if(!buildingsEven){ error = "error 2"; }
        else if(!legalNumber){ error = "error 3"; }

        if(error.equals("")){
            if(num == 0 && type == 0){ houses -= 1; owner.balance += housePrice; }
            else if (num > 0 && type == 0){
                for(Property x : properties[color]){ if(x == null){ continue; } x.houses -= num; owner.balance += housePrice; }
            }
            else if(num == 0){ hotel = 0; houses = 4; owner.balance += housePrice; }
            else{
                for(Property x : properties[color]){ if(x == null){ continue; } x.hotel = 0; x.houses = 4; owner.balance += housePrice; }
            }
        }
        return error;
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
        //Also tradeable for get out of jail free
    }

    @Override
    public void receive(Player player){
        if(!player.equals(owner)){
            player.balance -= rent[houses + (hotel * 5)];
        }
    }

    public static Property[][] properties(){
        Property[][] properties = new Property[8][3];
        properties[0][0] = new Property(0, new int[]{2, 10, 30, 90, 160, 250}, 60, 30, 50,"Mediterranean Avenue");
        properties[0][1] = new Property(0, new int[]{4, 20, 60, 180, 320, 450}, 60, 30, 50,"Baltic Avenue");
        properties[0][2] = null;
        properties[1][0] = new Property(1, new int[]{6, 30, 90, 270, 400, 550}, 100, 50, 50,"Oriental Avenue");
        properties[1][1] = new Property(1, new int[]{6, 30, 90, 270, 400, 550}, 100, 50, 50,"Vermont Avenue");
        properties[1][2] = new Property(1, new int[]{8, 40, 100, 300, 450, 600}, 120, 60, 50,"Connecticut Avenue");
        properties[2][0] = new Property(2, new int[]{10, 50, 150, 450, 625, 750}, 140, 70, 100,"St. Charles Place");
        properties[2][1] = new Property(2, new int[]{10, 50, 150, 450, 625, 750}, 140, 70, 100,"States Avenue");
        properties[2][2] = new Property(2, new int[]{12, 60, 180, 500, 700, 900}, 160, 80, 100,"Virginia Avenue");
        properties[3][0] = new Property(3, new int[]{14, 70, 200, 550, 750, 950}, 180, 90, 100,"St. James Place");
        properties[3][1] = new Property(3, new int[]{14, 70, 200, 550, 750, 950}, 180, 90, 100,"Tennessee Avenue");
        properties[3][2] = new Property(3, new int[]{16, 80, 220, 600, 800, 1000}, 200, 100, 100,"New York Avenue");
        properties[4][0] = new Property(4, new int[]{18, 90, 250, 700, 875, 1050}, 220, 110, 150,"Kentucky Avenue");
        properties[4][1] = new Property(4, new int[]{18, 90, 250, 700, 875, 1050}, 220, 110, 150,"Indiana Avenue");
        properties[4][2] = new Property(4, new int[]{20, 100, 300, 750, 925, 1100}, 240, 120, 150,"Illinois Avenue");
        properties[5][0] = new Property(5, new int[]{22, 110, 330, 800, 975, 1150}, 260, 130, 150,"Atlantic Avenue");
        properties[5][1] = new Property(5, new int[]{22, 110, 330, 800, 975, 1150}, 260, 130, 150,"Ventnor Avenue");
        properties[5][2] = new Property(5, new int[]{24, 120, 360, 850, 1025, 1200}, 280, 140, 150,"Marvin Gardens");
        properties[6][0] = new Property(6, new int[]{26, 130, 390, 900, 1100, 1275}, 300, 150, 200,"Pacific Avenue");
        properties[6][1] = new Property(6, new int[]{26, 130, 390, 900, 1100, 1275}, 300, 150, 200,"North Carolina Avenue");
        properties[6][2] = new Property(6, new int[]{28, 150, 450, 1000, 1200, 1400}, 320, 160, 200,"Pennsylvania Avenue");
        properties[7][0] = new Property(7, new int[]{35, 175, 500, 1100, 1300, 1500}, 350, 175, 200,"Park Place");
        properties[7][1] = new Property(7, new int[]{50, 200, 600, 1400, 1700, 2000}, 400, 200, 200,"Boardwalk");
        properties[7][2] = null;
        return properties;
    }
}
/*public String canBuy(int num, int type){ //0 = house, 1 = hotel
        boolean fullColorSetOwned = true;
        boolean noneMortgaged = true;
        boolean allEven = true; //For multiple houses
        boolean noHotels = true;
        boolean even = true;
        int leastHouses = 4;
        for(Property x : Property.properties){
            if(x.color == color){
                if(fullColorSetOwned && !x.owner.equals(owner)){ fullColorSetOwned = false; }
                if(x.mortgaged){ noneMortgaged = false; }
                if(x.houses != houses){ allEven = false; }
                if(x.hotel == 1){ noHotels = false; }
                if(x.houses + (hotel * 4) < leastHouses){ leastHouses = x.houses; }
            }
        }
        even = !(leastHouses < houses);
        boolean legalNum1 = even && (type == 0 && num >= 0 && ((num == 0 && houses + 1 <= 4) || (allEven && leastHouses + num <= 4)));
        boolean legalNum2 = allEven && ((num == 0 && hotel == 0) || noHotels);
        boolean legalNum = (type == 0 && legalNum1) || (type == 1 && legalNum2);
        int mult = 3; if(color == 0 || color == 7){ mult = 2; }
        int price = housePrice;
        if(num != 0 && type == 0){ price = mult * num * housePrice; }
        if(num != 0 && type == 1){
            if(leastHouses == 4){ price = housePrice * mult; }
            else{ price = ((4 - leastHouses) + 1) * mult * housePrice; }
        }
        boolean sufficientFunds = owner.balance - price >= 0;
        boolean canBuy = fullColorSetOwned && noneMortgaged && legalNum && sufficientFunds;
        return "";
    }*/
/*public boolean canBuyHouse(){
        /*
        Conditions:
            Owner balance >= house price
            houses < 4
            hotel == 0
            Property is not mortgaged
            Owner owns full color set
            Owner is building houses evenly
         */
        /*boolean bool = true;
        int leastHouses = 3;
        if(owner.balance >= housePrice && houses < 4 && hotel == 0){
            for(Property x : Property.properties){
                if(x.color == color){
                    if(x.houses < leastHouses){ leastHouses = x.houses; }
                    if(x.mortgaged || !x.owner.equals(owner)){ bool = false; break; }
                }
            }
        }
        return bool && leastHouses == houses;
    }*/
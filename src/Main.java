import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome! Please enter the total number of players");
        int totalPlayerCount = input.nextInt();
        for (int i = 1; i <= totalPlayerCount; i++){
            System.out.println("Player");
        }
    }
}

import java.util.Random;
import java.util.Scanner;

public class Main{
    public static CircularlyLinkedList<BoardSpace> board = new CircularlyLinkedList<>();
    public static CircularlyLinkedList<Player> players = new CircularlyLinkedList<>();

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome! Please enter the total number of players");
        int totalPlayerCount = input.nextInt();
        int counter = 1;
        System.out.println("Players, please enter your names");
        //declare and initialize players
        Player playerOne = new Player("Nathan");
        Player playerTwo = new Player("Sarah");
    }
    public static int[] rollDie(){
        Random random = new Random(12);
        return new int[]{(int)(Math.random() * (6) + 1), (int)(Math.random() * (6) + 1)};
    }

    public static void printBoard(){

    }
}

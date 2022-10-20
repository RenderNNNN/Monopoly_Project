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
        if(counter <= totalPlayerCount){
            Player playerOne = new Player(input.nextLine());
            counter++;
        }
        if(counter <= totalPlayerCount){
            Player playerTwo = new Player(input.nextLine());
        }
    }
}

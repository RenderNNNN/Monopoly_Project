import java.util.*;

public class Main {
    public static CircularlyLinkedList<BoardSpace> board = new CircularlyLinkedList<>();
    public static CircularlyLinkedList<Player> players = new CircularlyLinkedList<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome! Please enter the total number of players");
        int totalPlayerCount = input.nextInt() - 1;
        int temp = totalPlayerCount;
        Player[] players = new Player[totalPlayerCount];
        while (totalPlayerCount > 1) {
            if (totalPlayerCount == 5) {
                players[totalPlayerCount] = new Player("Player Six");
            }
            if (totalPlayerCount == 4) {
                players[totalPlayerCount] = new Player("Player Five");
            }
            if (totalPlayerCount == 3) {
                players[totalPlayerCount] = new Player("Player Four");
            }
            if (totalPlayerCount == 2) {
                players[totalPlayerCount] = new Player("Player Three");
            }
            totalPlayerCount--;
        }
        players[1] = new Player("Player Two");
        players[0] = new Player("Player One");

        int currentPlayer = 0;
        while (players[currentPlayer].getBalance() > 200) {
            int[] dieOutput = rollDie();
            System.out.println(players[currentPlayer ].getName() + ", you have rolled a " + rollDie()[0] + ", " + rollDie()[1]);
            players[currentPlayer].advance(rollDie()[0] + rollDie()[1]); //change advance arguments to implement something like this?
            isJail(players[currentPlayer]);
            currentPlayer++;
            if(currentPlayer > temp){
                currentPlayer = 0;
            }
        }
    }

    public static int[] rollDie() {
        Random random = new Random(12);
        return new int[]{(int) (Math.random() * (6) + 1), (int) (Math.random() * (6) + 1)};
    }

    public static void isJail(Player player) {
        if (player.getLocation().name().equals("Jail")) {
            System.out.print("Oh no! You have gone to jail!");
        }
    }

    public static void printBoard() {

    }
}
/*

____________________________________________________________________________________________________
|        |        |        |        |        |        |        |        |        |        |        |
|  JAIL  |        |        |        |        |        |        |        |        |        |  PARK  |
____________________________________________________________________________________________________
|        |                                                                                |        |
 _________                                                                                __________
|        |                                                                                |        |
 _________                                                                                __________
|        |                                                                                |        |
 _________                                                                                __________
|        |                                                                                |        |
 _________                                                                                __________
|        |                                    MONOPOLY                                    |        |
 _________                                                                                __________
|        |                                                                                |        |
 _________                                                                                __________
|        |                                                                                |        |
 _________                                                                                __________
|        |                                                                                |        |
 _________                                                                                __________
|        |                                                                                |        |
____________________________________________________________________________________________________
|        |        |        |        |        |        |        |        |        |        | GO TO  |
|   GO   |        |        |        |        |        |        |        |        |        |  JAIL  |
____________________________________________________________________________________________________

______________________________________________________________________________________________________________________________________
|                |          |          |          |          |          |          |          |          |          |                |
|                |          |          |          |          |          |          |          |          |          |                |
|                |          |          |          |          |          |          |          |          |          |                |
|   ___JAIL___   |          |          |          |          |          |          |          |          |          |   ___PARK___   |
|                |          |          |          |          |          |          |          |          |          |                |
|                |          |          |          |          |          |          |          |          |          |                |
______________________________________________________________________________________________________________________________________
|                |                                                                                                  |                |
|                |                                                                                                  |                |
________________                                                                                                     ________________
|                |                                                                                                  |                |
|                |                                                                                                  |                |
________________                                                                                                     ________________
|                |                                                                                                  |                |
|                |                                                                                                  |                |
________________                                                                                                     ________________
|                |                                                                                                  |                |
|                |                                                                                                  |                |
________________                                                                                                     ________________
|                |                                             MONOPOLY                                             |                |
|                |                                                                                                  |                |
________________                                                                                                     ________________
|                |                                                                                                  |                |
|                |                                                                                                  |                |
________________                                                                                                     ________________
|                |                                                                                                  |                |
|                |                                                                                                  |                |
________________                                                                                                     ________________
|                |                                                                                                  |                |
|                |                                                                                                  |                |
________________                                                                                                     ________________
|                |                                                                                                  |                |
|                |                                                                                                  |                |
______________________________________________________________________________________________________________________________________
|                |          |          |          |          |          |          |          |          |          |                |
|                |          |          |          |          |          |          |          |          |          |                |
|                |          |          |          |          |          |          |          |          |          |                |
|   ____GO____   |          |          |          |          |          |          |          |          |          |   GO TO JAIL   |
|                |          |          |          |          |          |          |          |          |          |                |
|                |          |          |          |          |          |          |          |          |          |                |
______________________________________________________________________________________________________________________________________
 */

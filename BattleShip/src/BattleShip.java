import java.util.Random;
import java.util.Scanner;

// println: start a new line to print
// print: continue with the content before

public class BattleShip {

    public static void printMap(String[][] Map) {
        System.out.println(" ");
        System.out.println("  0123456789");
        for (int row=0; row<Map.length; row++)
        {
            System.out.print(row+"|");
            for (int col=0; col<Map[row].length; col++)
            {
                if (Map[row][col]==null ||
                        Map[row][col]=="2" ||
                        Map[row][col]=="6") {
                    System.out.print(" ");
                }
                else if(Map[row][col]=="1"){
                    System.out.print("@");
                }
                else if (Map[row][col]=="3"){
                    System.out.print("!"); // hit computer
                }
                else if (Map[row][col]=="4"){
                    System.out.print("X"); // hit player
                }
                else if (Map[row][col]=="5"){
                    System.out.print("-"); // miss
                }
            }
            System.out.println("|"+row);
        }
        System.out.println("  0123456789");
    }

    public static void main(String args[]){

        // creating the ocean map using a single 2D array
        String[][] Map = new String[10][10];

        System.out.println("  0123456789");
        for (int row=0; row<Map.length; row++)
        {
            System.out.print(row+"|");
            for (int col=0; col<Map[row].length; col++)
            {
                if (Map[row][col]==null) {
                    System.out.print(" ");
                }
            }
            System.out.println("|"+row);
        }
        System.out.println("  0123456789");


        // player deploy ships
        System.out.println(" ");
        System.out.println("Deploy your ship...");
        Scanner input = new Scanner(System.in); // creating a scanner to use
        int count = 0;

        while(count<5) {
            System.out.print("Enter the X coordinate for your ship: ");
            int x = input.nextInt();

            System.out.print("Enter the Y coordinate for you ship: ");
            int y = input.nextInt();

            if (x>9 || x<0 || y>9 || y<0)
            {
                System.out.println("Out of boundary!");
                continue;
            }
            else if (Map[x][y]=="1"){
                System.out.println("Coordinate already taken!");
                continue;
            }
            else {
                Map[x][y] = "1";
                count++;
                System.out.println(count+" ship deployed");
            }
        }


        // Print out player ships
        System.out.println(" ");
        System.out.println("  0123456789");
        for (int row=0; row<Map.length; row++)
        {
            System.out.print(row+"|");
            for (int col=0; col<Map[row].length; col++)
            {
                if (Map[row][col]==null) {
                    System.out.print(" ");
                }
                else if(Map[row][col]=="1"){
                    System.out.print("@");
                }
            }
            System.out.println("|"+row);
        }
        System.out.println("  0123456789");


        //Computer deploy ships
        System.out.println(" ");
        System.out.println("Computer deploying ships...");

        Random rand = new Random(); // creating a random obj to use
        int comp_count=0;

        while (comp_count < 5){
            int comp_x = rand.nextInt(10);
            int comp_y = rand.nextInt(10);

            if (Map[comp_x][comp_y]=="1" || Map[comp_x][comp_y]=="2"){
                continue;
            }
            else {
                Map[comp_x][comp_y] = "2";
                comp_count++;
                System.out.println(comp_count+" ship deployed");
            }
        }

        int flag=0; // flag=0, player turn; flag=1, computer turn

        int comp_ships=5;
        int player_ships=5;


        while(comp_ships>0 && player_ships>0)
        {
            if (flag==0){ // player turn
                System.out.println(" ");
                System.out.println("Your turn...");
                boolean valid=false;
                int guess_x = 0, guess_y=0;

                while(!valid) {
                    System.out.print("Guess X coordinate: ");
                    guess_x = input.nextInt();
                    System.out.print("Guess Y coordinate: ");
                    guess_y = input.nextInt();

                    if (guess_x > 9 || guess_x < 0 || guess_y > 9 || guess_y < 0) {
                        System.out.println("Out of boundary!");
                        continue;
                    } else if (Map[guess_x][guess_y] == "3" ||
                            Map[guess_x][guess_y] == "4" ||
                            Map[guess_x][guess_y] == "5" ) {
                        System.out.println("Coordinate already taken!");
                        continue;
                    } else {
                        valid = true;
                    }
                }

                if (Map[guess_x][guess_y]=="2"){
                    System.out.println("Boom! You sunk the ship!");
                    Map[guess_x][guess_y]="3"; // hit computer
                    comp_ships--;
                }
                else if (Map[guess_x][guess_y]=="1"){
                    System.out.println("Oh no, you sunk your own ship!");
                    Map[guess_x][guess_y]="4"; // hit player
                    player_ships--;
                }
                else {
                    System.out.println("Sorry, you missed.");
                    Map[guess_x][guess_y]="5"; // miss
                }

                flag=1;
                // if player win
                // print out current map and result
                if (comp_ships==0)
                {
                    printMap(Map);
                    System.out.println(" ");

                    System.out.println("Your ships: "+ player_ships + " || " +
                            "Computer ships: "+ comp_ships);
                    System.out.println("------------------------");
                    System.out.println("Hooray! You win the battle! :)");
                }

            }
            else{ // computer turn

                boolean comp_valid=false;
                System.out.println(" ");
                System.out.println("Computer turn...");

                while(!comp_valid){
                    int comp_guess_x = rand.nextInt(10);
                    int comp_guess_y = rand.nextInt(10);

                    if (Map[comp_guess_x][comp_guess_y] == "3" ||
                            Map[comp_guess_x][comp_guess_y] == "4" ||
                            Map[comp_guess_x][comp_guess_y] == "6" ) {continue; }
                    else {
                        comp_valid = true;

                        if (Map[comp_guess_x][comp_guess_y]=="2"){
                            System.out.println("The Computer sunk one of its own ships!");
                            Map[comp_guess_x][comp_guess_y]="3"; // hit computer
                            comp_ships--;
                        }
                        else if (Map[comp_guess_x][comp_guess_y]=="1"){
                            System.out.println("The Computer sunk one of your ships!");
                            Map[comp_guess_x][comp_guess_y]="4"; // hit player
                            player_ships--;
                        }
                        else {
                            System.out.println("Computer missed");
                            Map[comp_guess_x][comp_guess_y]="6"; // miss, but no mark
                        }

                        flag=0;

                        // print out current map and result
                        printMap(Map);
                        System.out.println(" ");

                        System.out.println("Your ships: "+ player_ships + " || " +
                                "Computer ships: "+ comp_ships);
                        System.out.println("------------------------");
                        if (player_ships==0) {
                            System.out.println("Computer win! Try again!");
                        }
                    }
                }
            }
        }























    }

}



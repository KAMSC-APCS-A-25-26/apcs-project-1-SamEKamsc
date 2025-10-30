import java.io.File;
import java.util.Scanner;


public class TextAdventure {
    public static void main(String[] args) throws Exception {
        TextAdventure.main_menu();
        TextAdventure.choose_difficulty();
        Globals.player = new Player(Globals.team_name);
        Globals.enemy_one = new Enemy_team("Polytrack Pitmasters");
        Globals.enemy_two = new Enemy_team("Houtrouw Hyperdrivers");
        Globals.enemy_three = new Enemy_team("Afsal Accelerators");
        int cont = 1;
        int day = 0;
        while (cont == 1) {
            int[] ret = GameLoop.week_cycler(day);
            day = ret[0];
            cont = ret[1];
            GameLoop.day_menu();
        }
    }

    public static String main_menu() throws Exception {
        // Declare Scanners
        Scanner sc = new Scanner(System.in);
        Scanner readme_sc = new Scanner(new File("README.txt"));
        // Print Title
        System.out.println("=======================================");
        System.out.println("                  THE");
        System.out.println("Γ ‾ ‾ ⅂    Γ ‾ ‾ ⅂    Γ ‾ ‾ ‾  Γ ‾ ‾ ‾");
        System.out.println("|     |    |     |    |        |");
        System.out.println("| — — ⅃    | — — |    |        | — — —");
        System.out.println("| \\        |     |    |        |");
        System.out.println("|   \\      |     |    L _ _ _  L _ _ _");
        System.out.println("             BY SAM EDMONDS");
        System.out.println("=======================================");
        System.out.println("Welcome to the RACE!");
        // Main menu switch case of play or how to play
        int choice_one = 0;
        String name = "";
        while (choice_one != 1) {
            System.out.println("--------------");
            System.out.println("1. Play");
            System.out.println("2. How to play");
            System.out.print("Enter your choice: ");
            choice_one = sc.nextInt();
            System.out.println();
            switch (choice_one) {
                case 1:
                    System.out.println("---------------------");
                    System.out.print("Enter your team name: ");
                    name = sc.next();
                    break;
                case 2:
                    System.out.println();
                    while (readme_sc.hasNext()) {
                        System.out.println(readme_sc.nextLine());
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Please enter one of the options");
                    break;
            }
        }
        readme_sc.close();
        return name;
    }

    public static void choose_difficulty() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------");
        System.out.println("Choose Difficuly");
        System.out.println("1. Easy");
        System.out.println("2. Normal");
        System.out.println("3. Hard");
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        System.out.println();
        switch (choice) {
            case 1:
                System.out.println("Easy Mode (wimp)");
                Globals.bank_account = 1500;
                Globals.difficulty_modifier = 0;
                Globals.day = 0;
                break;
            case 2:
                System.out.println("Normal Mode");
                Globals.bank_account = 1000;
                Globals.difficulty_modifier = 2;
                Globals.day = 0;
                break;
            case 3:
                System.out.println("Hard Mode");
                Globals.bank_account = 500;
                Globals.difficulty_modifier = 4;
                Globals.day = 0;
                break;
        }
    }

    
}
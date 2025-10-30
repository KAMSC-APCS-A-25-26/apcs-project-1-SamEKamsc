import java.util.Scanner;

public class GameLoop {
    public static int[] week_cycler(int day) throws Exception {
        // Cycles through days of the week, keeping track of total days and the days action
        Globals.day += 1;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        System.out.println("-----------------------------------");
        System.out.println("Day " + Globals.day + ": " + days[day]);
        int next_day = day+1;
        int cont = 1;
        if (next_day==7) {
            next_day = 0;
        }
        if (Globals.day == 10) {
            cont = 0;
        }
        int[] ret = {next_day, cont};
        return ret;
    }

    public static void day_menu() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("Weekly Development options");
        System.out.println("1. Develop Driver");
        System.out.println("2. Develop Car");
        System.out.println("3. Scout Opponents (-$250)");
        System.out.println("4. Manage Sponsers");
        System.out.println("5. See Stats (won't take the day)");
        int choice;
        do { 
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == 5) {
                Globals.player.show_stats();
            }
        } while (choice < 1 || choice > 4);
        // Switch cases the choice to go to sub menus
        switch (choice) {
            case 1:
                develop_driver();
                break;
            case 2:
                develop_car();
                break;
            case 3:
                scout_opponents();
                break;
            case 4:
                manage_sponsers();
                break;
        }
        
    }

    // 1. Develop Driver
    public static void develop_driver() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("Driver Development");
        System.out.println("1. Send Driver to the Gym");
        System.out.println("2. Send Driver to Watch Film");
        System.out.println("3. Send driver to Practice Driving");
        int choice;
        do { 
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
        } while (choice < 1 || choice > 3);
        // uses the choice to upgrade driver
        Globals.player.stats[choice-1] += 1;
    }

    // 2. Develop Car
    public static void develop_car() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("Car Development");
        System.out.println("1. Upgrade Car Motor");
        System.out.println("2. Increase Car Aerodynamics");
        System.out.println("3. Engineer Car Structure");
        int choice;
        do { 
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
        } while (choice < 1 || choice > 3);
        // uses the choice to upgrade driver
        Globals.player.stats[choice+2] += 1;
    }

    // 3. Scout Opponents
    public static void scout_opponents() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose Opp to Scout");
        System.out.println("1. " + Globals.enemy_one.name);
        System.out.println("2. " + Globals.enemy_two.name);
        System.out.println("3. " + Globals.enemy_three.name);
        int choice;
        do { 
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
        } while (choice < 1 || choice > 3);
        // uses the choice to scout chosen team
        Enemy_team[] enemy_teams = {Globals.enemy_one, Globals.enemy_two, Globals.enemy_three};
        enemy_teams[choice-1].scout();
    }

    // 4. Manage Sponsers
    public static void manage_sponsers() {
        
    }
}

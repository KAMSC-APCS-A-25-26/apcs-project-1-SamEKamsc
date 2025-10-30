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
            race_day();
        }
        else {
            day_menu();
        }
        if (Globals.bank_account <= 0 || Globals.races_lost == 3) {
            cont = 0;
        }
        int[] ret = {next_day, cont};
        return ret;
    }

    public static void day_menu() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("--------------------");
            System.out.println("Weekly Development options");
            System.out.println("1. Develop Driver");
            System.out.println("2. Develop Car");
            System.out.println("3. Scout Opponents (-$250)");
            System.out.println("4. Manage Sponsors");
            System.out.println("5. See Stats (won't take the day)");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
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
                manage_sponsors();
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
        Globals.bank_account -= 250;
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

    // 4. Manage Sponsors
    public static void manage_sponsors() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("Manage Sponsors:");
        System.out.println("1. Add Sponsor (" + Globals.number_of_sponsors + "/3)");
        System.out.println("2. Create Sponsored Video (+$250)");
        int choice;
        do { 
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
        } while (choice < 1 || choice > 2);
        // shows the stats based on input
    }

    // 5. View Stats
    public static void see_stats() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("View Stats");
        System.out.println("1. Sponsorships");
        System.out.println("2. Driver Stats");
        System.out.println("3. Bank Account");
        int choice;
        do { 
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
        } while (choice < 1 || choice > 3);
        // shows the stats based on input
        switch (choice) {
            case 1:
                System.out.println(Globals.team_name + "'s Sponsorships");
                for (int i = 0; i < 7; i++) {
                    if (Globals.sponsors_days_left[i] != 0) {
                        System.out.println("Sponsor: " + Globals.sponsors_names[i]);
                        System.out.println("  Pay: " + Globals.sponsors_pay_amount[i]);
                        System.out.println("  Days left:" + Globals.sponsors_days_left[i]);
                    }
                }
                break;
            case 2:
                System.out.println(Globals.team_name + "'s Drivers Stats");
                Globals.player.show_stats();
                break;
            case 3:
                System.out.println(Globals.team_name + "'s Bank Account:" + Globals.bank_account);
                break;

        }
    }

    // Race Days
    public static void race_day() {

    }
}

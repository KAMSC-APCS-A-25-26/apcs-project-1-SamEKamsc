import java.util.Random;
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
        update_sponsor();
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
            System.out.println("1. Develop Driver (-$250)");
            System.out.println("2. Develop Car (-$400)");
            System.out.println("3. Scout Opponents (-$250)");
            System.out.println("4. Manage Sponsors");
            System.out.println("5. See Stats (won't take the day)");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == 5) {
                see_stats();
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
        Globals.bank_account -= 250;
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
        Globals.bank_account -= 400;
    }

    // 3. Scout Opponents
    public static void scout_opponents() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose Opp to Scout");
        System.out.println("1. " + Globals.enemy_one.name);
        System.out.println("2. " + Globals.enemy_two.name);
        System.out.println("3. " + Globals.enemy_three.name);
        System.out.println("4. " + Globals.enemy_four.name);
        Globals.bank_account -= 250;
        int choice;
        do { 
            System.out.print("Enter your choice: ");  // UPDATE TS SO THAT IT DOES WHAT IT SAYS IN REAMDE AND MAKE Sure player knows what is out of date
            choice = sc.nextInt();
            System.out.println();
        } while (choice < 1 || choice > 4);
        // uses the choice to scout chosen team
        Enemy_team[] enemy_teams = {Globals.enemy_one, Globals.enemy_two, Globals.enemy_three, Globals.enemy_four};
        enemy_teams[choice-1].scout();
    }

    // 4. Manage Sponsors
    public static void manage_sponsors() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("Manage Sponsors:");
        System.out.println("1. Add Sponsor (" + Globals.number_of_sponsors + "/3)");
        System.out.println("2. Create Sponsored Video (+$200 to $300)");
        int choice;
        do { 
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
        } while ((choice < 1 || choice > 2) || (choice == 1 && Globals.number_of_sponsors >= 3));
        // makes $$$ based on choice
        switch (choice) {
            case 1:
                choose_sponser();
                break;
            case 2:
                Random rd = new Random();
                int money = rd.nextInt(200, 301);
                Globals.bank_account += money;
                System.out.println("Sponser Advertisment made. Bank +$" + money);
                break;
        }
    }
    public static void choose_sponser() {
        Scanner sc = new Scanner(System.in);
        int amount_chosen = 0;
        int[] chosen_sponsors = {0, 0, 0};
        while (amount_chosen < 3) {
            Random rd = new Random();
            int random_index = rd.nextInt(0, 7);
            boolean valid_sponsor = true;
            if (Globals.sponsors_days_left[random_index] != 0) {
                valid_sponsor = false;
            }
            for (int i = 0; i < amount_chosen; i++) {
                if (random_index == chosen_sponsors[i]) {
                    valid_sponsor = false;
                }
            }
            if (valid_sponsor) {
                chosen_sponsors[amount_chosen] = random_index;
                amount_chosen += 1;
            }
        }
        System.out.println("Choose Sponser:");
        for (int i = 0; i < 3; i++) {
            System.out.print((i+1) + ". " + Globals.sponsors_names[chosen_sponsors[i]]);
            System.out.print(": $" + Globals.sponsors_pay_amount[chosen_sponsors[i]]);
            System.out.println(" for " + Globals.sponsors_pay_days[chosen_sponsors[i]] + " days");
        }
        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
        } while (choice < 1 || choice > 3);
        switch (choice) {
            case 1:
                Globals.sponsors_days_left[chosen_sponsors[0]] = Globals.sponsors_pay_days[chosen_sponsors[0]];
                Globals.number_of_sponsors += 1;
                break;
            case 2:
                Globals.sponsors_days_left[chosen_sponsors[1]] = Globals.sponsors_pay_days[chosen_sponsors[1]];
                Globals.number_of_sponsors += 1;
                break;
            case 3:
                Globals.sponsors_days_left[chosen_sponsors[2]] = Globals.sponsors_pay_days[chosen_sponsors[2]];
                Globals.number_of_sponsors += 1;
                break;
        }
    }
    public static void update_sponsor() {
        for (int i = 0; i < 7; i++) {
            if (Globals.sponsors_days_left[i] >= 1) {
                Globals.bank_account += Globals.sponsors_pay_amount[i];
                Globals.sponsors_days_left[i] -= 1;
            }
            if (Globals.sponsors_days_left[i] == 1) {
                Globals.number_of_sponsors -= 1;
            }
        }
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
                        System.out.println("  Pay: $" + Globals.sponsors_pay_amount[i]);
                        System.out.println("  Days left: " + Globals.sponsors_days_left[i]);
                    }
                }
                break;
            case 2:
                System.out.println(Globals.team_name + "'s Drivers Stats");
                Globals.player.show_stats();
                break;
            case 3:
                System.out.println(Globals.team_name + "'s Bank Account: " + Globals.bank_account);
                break;

        }
    }

    // Race Days
    public static void race_day() {

    }
}

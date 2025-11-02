import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameLoop {
    public static int[] week_cycler(int day) throws Exception {
        // Cycles through days of the week, keeping track of total days and the days action
        Globals.day += 1;
        Globals.total_days += 1;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        System.out.println("-----------------------------------");
        System.out.println("Day " + Globals.day + ": " + days[day]);
        int next_day = day+1;
        int cont = 1;
        update_sponsor();
        if (Globals.bank_account <= 0) {
            System.out.println("You went Bankrupt: Game Over");
            cont = 0;
            Globals.add_week_to_tracker();
        }
        else if  (Globals.races_lost == 3) {
            System.out.println("You Lost 3 Games in a Row: Game Over");
            cont = 0;
            Globals.add_week_to_tracker();
        }
        else if (Globals.total_days >= 98) {
            System.out.println("You Win: Well Done");
            cont = 0;
            Globals.add_week_to_tracker();
        }
        else if (next_day==7) {
            next_day = 0;
            race_day();
            Globals.add_week_to_tracker();
        }
        else {
            day_menu();
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
            System.out.println("Weekly Development options (Bank: " + Globals.bank_account + ")");
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
        String[] action = {"the Gym", "Watch Film", "Practice Driving"};
        Globals.add_day_to_week("Sent Driver to " + action[choice-1] + " -$250");
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
        String[] action = {"Motor", "Aerodynamics", "Structure"};
        Globals.add_day_to_week("Upgraded Cars " + action[choice-1] + " -$400");
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
        int choice;
        do { 
            System.out.print("Enter your choice: ");  // UPDATE TS SO THAT IT DOES WHAT IT SAYS IN REAMDE AND MAKE Sure player knows what is out of date
            choice = sc.nextInt();
            System.out.println();
        } while (choice < 1 || choice > 4);
        // uses the choice to scout chosen team
        Enemy_team[] enemy_teams = {Globals.enemy_one, Globals.enemy_two, Globals.enemy_three, Globals.enemy_four};
        enemy_teams[choice-1].scout();
        Globals.bank_account -= 250;
        Globals.add_day_to_week("Scouted " + enemy_teams[choice-1].name + " -$250");
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
                System.out.println("Sponsored Advertisment made. Bank +$" + money);
                Globals.add_day_to_week("Created Sponsored Advertisment +$" + money);
                break;
        }
    }
    // menu for choosing which sponsor to get
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
        Globals.sponsors_days_left[chosen_sponsors[choice-1]] = Globals.sponsors_pay_days[chosen_sponsors[choice-1]];
        Globals.number_of_sponsors += 1;
        Globals.add_day_to_week("Signed Contract with " + Globals.sponsors_names[chosen_sponsors[choice-1]] + " +$" + Globals.sponsors_pay_amount[chosen_sponsors[choice-1]] + " for " + Globals.sponsors_pay_days[chosen_sponsors[choice-1]] + " days");
    }
    // updates pay and reduces the aamount of day left on a sponsor
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
        System.out.println("3. See Opponents Stats");
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
                see_opp_stats();
                break;

        }
    }
    // menu for choosing which opponents stats to see
    public static void see_opp_stats() {
        // Sets up Scanner, Prints prompt, and takes in prompt
        Scanner sc = new Scanner(System.in);
        System.out.println("Opponents Stats");
        System.out.println("1. " + Globals.enemy_one.name);
        System.out.println("2. " + Globals.enemy_two.name);
        System.out.println("3. " + Globals.enemy_three.name);
        System.out.println("4. " + Globals.enemy_four.name);
        System.out.println("5. All Opponents Compact view");
        int choice;
        do { 
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();
        } while (choice < 1 || choice > 5);
        switch (choice) {
            case 1:
                Globals.enemy_one.show_scouted_stats();
                break;
            case 2:
                Globals.enemy_two.show_scouted_stats();
                break;
            case 3:
                Globals.enemy_three.show_scouted_stats();
                break;
            case 4:
                Globals.enemy_four.show_scouted_stats();
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    Enemy_team[] enemies = {Globals.enemy_one, Globals.enemy_two, Globals.enemy_three, Globals.enemy_four};
                    System.out.print(enemies[i].name + "'s Stats: ");
                    for (int j = 0; j < 6; j++) {
                        if (enemies[i].revealed_stats[j] == 1) {
                            System.out.print(enemies[i].stats[j] +", ");
                        } 
                        else if (enemies[i].revealed_stats[j] == 2) {
                            System.out.print(enemies[i].stats[j] +" (Outdated), ");
                        } 
                        else {
                            System.out.print("Not found, ");
                        }
                    }
                    System.out.println();
                }
                break;
        }
    }

    // Race Days
    public static void race_day() {
        // System.out.println(Arrays.toString(Globals.enemy_one.stats) + ", " + Arrays.toString(Globals.enemy_two.stats) + ", " + Arrays.toString(Globals.enemy_three.stats) + ", "+ Arrays.toString(Globals.enemy_four.stats) + ", "+ Arrays.toString(Globals.player.stats));
        int[][] stats = {Globals.enemy_one.stats, Globals.enemy_two.stats, Globals.enemy_three.stats, Globals.enemy_four.stats, Globals.player.stats};
        int[] points = {0, 0, 0, 0, 0};
        for (int c1 = 0; c1 < 5; c1++) {
            for (int c2 = 0; c2 < 5; c2++) {
                if (c1 == c2) {
                    continue;
                }
                int[] team_one = stats[c1];
                int[] team_two = stats[c2];
                for (int i = 0; i < 6; i++) {
                    if (team_one[i] > team_two[i]) {
                        points[c1] += 1;
                    }
                    else if (team_one[i] < team_two[i]) {
                        points[c2] += 1;
                    }
                }   
            }
        }
        Enemy_team[] enemy_by_index = {Globals.enemy_one, Globals.enemy_two, Globals.enemy_three, Globals.enemy_four};
        int[][] team_index_and_points = {
            {0, points[0]},
            {1, points[1]},
            {2, points[2]},
            {3, points[3]},
            {4, points[4]} // player
        };
        Arrays.sort(team_index_and_points, (o1, o2) -> {
            // sort by the second element
            return Integer.compare(o2[1], o1[1]);
        });
        int players_place = 0;
        int players_reward = 0;
        for (int i = 0; i < 5; i++) {
            if (team_index_and_points[i][0] == 4) {
                System.out.print(i+1 +": " + Globals.team_name);
                players_place = i+1;
            }
            else {
                System.out.print(i+1 + ": " +enemy_by_index[team_index_and_points[i][0]].name);
            }
            if (i < 3) {
                int reward = 250*(3-i);
                System.out.println(". Prize: $" + reward);
                if (team_index_and_points[i][0] == 4) {
                    Globals.bank_account += reward;
                    players_reward = reward;
                    Globals.races_lost = 0;
                }
            }
            else {
                System.out.println(".");
                if (team_index_and_points[i][0] == 4) {
                    Globals.races_lost += 1;
                }
            }
        }
        Globals.add_day_to_week("Race Day: placed " + players_place + " +$" + players_reward);
        update_opp_stats();
    }
    // Mark drivers stats as outdated
    public static void update_opp_stats() {
        System.out.println("Opponents Teams have developed");
        Enemy_team[] enemies = {Globals.enemy_one, Globals.enemy_two, Globals.enemy_three, Globals.enemy_four};
        for (int i = 0; i < 4; i++) {
            enemies[i].develop();
        }
    }

    // See Choice Data
}

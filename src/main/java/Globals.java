import java.util.ArrayList;

public class Globals {
    // Player
    public static String team_name = "";
    public static int day = 0;
    public static int total_days = 0;
    public static int bank_account = 0;
    public static int races_lost = 0;
    public static int difficulty_modifier = 0;
    public static Player player;

    // Enemies
    public static Enemy_team enemy_one;
    public static Enemy_team enemy_two;
    public static Enemy_team enemy_three;
    public static Enemy_team enemy_four;

    // Sponsors
    public static String[] sponsors_names = {"A", "B", "C", "D", "E", "F", "G"};
    //                               total:   600, 550, 500, 450, 400, 350, 300
    public static int[] sponsors_pay_amount ={40,  42,  41,  45,  50,  70, 150};
    public static int[] sponsors_pay_days = { 15,  13,  12,   10,   8,   5,   2}; 
    public static int[] sponsors_days_left = { 0,   0,   0,   0,   0,   0,   0};
    public static int number_of_sponsors = 0;

    // Extra Credit Choice Tracker
    public static ArrayList<ArrayList<String>> tracker = new ArrayList<>();
    public static ArrayList<String> week_tracker = new ArrayList<>();
        // ^^ format so { {week#, day#, [place/choice], money_change, final_bank}, {...}, ...}
    public static void add_day_to_week(String action) {
        week_tracker.add(action);
    }
    
    public static void add_week_to_tracker() {
        ArrayList<String> copy = new ArrayList<>(week_tracker);
        tracker.add(copy);
        week_tracker.clear();
    }
    
    public static void tracker_printer() {
        // print out stuff ig
        for (int w = 0; w < tracker.size(); w++) {
            System.out.println("Week " + (w+1) + ":");
            for (int d = 0; d < tracker.get(w).size(); d++) {
                System.out.println("  Day " + (w*7 + d+1) + ": " + tracker.get(w).get(d));
            }
        }
        System.out.println("players stats: ");
        player.show_stats();
        Enemy_team[] enemies = {enemy_one, enemy_two, enemy_three, enemy_four};
        for (int e = 0; e < 4; e++) {
            System.out.println(enemies[e].name + " stats: ");
            for (int i = 0; i < 6; i++) {
                System.out.print( enemies[e].stats[i] + ", ");
            }
            System.out.println();
        }
    }

}

public class Globals {
    // Player
    public static String team_name = "";
    public static int day = 0;
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
    public static String[][] tracker;
        // ^^ format so { {week#, day#, [place/choice], money_change, final_bank}, {...}, ...}
    public static void tracker_printer() {
        // print out stuff ig
    }

}

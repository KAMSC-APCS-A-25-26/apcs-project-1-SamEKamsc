import java.util.Random;
public class Enemy_team {
    // Array here for easier access: fitness, intelligence, skill, motor, aerodynamics, structure
    public int[] stats = {0, 0, 0, 0, 0, 0};
    // Shows what stats are 0: not found, 1: found, 2: Outdated
    public int[] revealed_stats = {0, 0, 0, 0, 0, 0};

    // Enemy initialization: sets name and creates random stats
    public String name;
    public Enemy_team(String name) {
        this.name = name;
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            this.stats[i] = rd.nextInt(6)+Globals.difficulty_modifier+5;
        }
    }

    // handles Enemy scouting, 50% chance of a stat getting revealed.
    public void scout() {
        Random rd = new Random();
        String[] part = {"driver fitness", "driver intelligence", "driver skill", "car motor", "car aerodynamics", "car structure"};
        for (int i = 0; i < 6; i++) {
            if (rd.nextBoolean()) {
                System.out.println("Enemy " + part[i] + ": " + this.stats[i]);
                revealed_stats[i] = 1;
            }
            else {
                System.out.println("Enemy " + part[i] + ": not found");
            }
        }
    }


}

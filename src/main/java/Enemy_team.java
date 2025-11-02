import java.util.Random;
public class Enemy_team {
    // Array here for easier access: fitness, intelligence, skill, motor, aerodynamics, structure
    public int[] stats = {0, 0, 0, 0, 0, 0};
    // Shows what stats are 0: not found, 1: found, 2: Outdated
    public int[] revealed_stats = {0, 0, 0, 0, 0, 0};
    String[] titles = {"drivers fitness", "drivers intelligence", "drivers skill", "cars motor", "cars aerodynamics", "cars structure"};
    // Enemy initialization: sets name and creates random stats
    public String name;
    public Enemy_team(String name) {
        this.name = name;
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            this.stats[i] = rd.nextInt(6)+Globals.difficulty_modifier+3;
        }
    }

    // handles Enemy scouting, 50% chance of a stat getting revealed.
    public void scout() {
        Random rd = new Random();
        System.out.println(this.name + "'s Stats");
        for (int i = 0; i < 6; i++) {
            if (rd.nextBoolean()) {
                System.out.println(titles[i] + ": " + this.stats[i]);
                this.revealed_stats[i] = 1;
            }
            else {
                System.out.println(titles[i] + ": not found");
            }
        }
    }

    // Shows this opponents stats
    public void show_scouted_stats() {
        System.out.println(this.name + "'s Stats: ");
        for (int i = 0; i < 6; i++) {
            switch (this.revealed_stats[i]) {
                case 0:
                    System.out.println(titles[i] + ": Not found");
                    break;
                case 1:
                    System.out.println(titles[i] + ": " + this.stats[i]);
                    break;
                case 2:
                    System.out.println(titles[i] + " (Outdated): " + this.stats[i]);
                    break;
            }
        }
    }

    // Upgrades all the stats
    public void develop() {
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            stats[i] += rd.nextInt(0, Globals.difficulty_modifier+1);
            if (this.revealed_stats[i] == 1) {
                this.revealed_stats[i] = 2;
            }
        }
    }
}

import java.util.Random;
import java.util.Arrays;
public class Enemy_driver {
    // Array here for easier access: fitness, intelligence, skill, motor, aerodynamics, structure
    public int[] stats = {0, 0, 0, 0, 0, 0};

    public Enemy_driver() {
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            this.stats[i] = rd.nextInt(6)+Globals.difficulty_modifier+5;
        }
    }

    public int[] scout() {
        Random rd = new Random();
        int[] chosen_stats = {-1, -1, -1, -1, -1, -1};
        for (int i = 0; i < 6; i++) {
            if (rd.nextBoolean()) {
                chosen_stats[i] = this.stats[i];
            }
        }
        return chosen_stats;
    }


}

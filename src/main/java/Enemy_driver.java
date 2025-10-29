import java.util.Random;
public class Enemy_driver {
    // Array here for easier access: fitness, intelligence, skill, motor, aerodynamics, structure
    public int[] stats = {0, 0, 0, 0, 0, 0};

    public Enemy_driver() {
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            this.stats[i] = rd.nextInt(6)+Globals.difficulty_modifier+5;
        }
    }

    public void scout() {
        Random rd = new Random();
        String[] part = {"driver fitness", "driver intelligence", "driver skill", "car motor", "car aerodynamics", "car structure"};
        for (int i = 0; i < 6; i++) {
            if (rd.nextBoolean()) {
                System.out.println("enemy " + part[i] + ": " + this.stats[i]);
            }
            else {
                System.out.println("enemy " + part[i] + ": not found");
            }
        }
    }


}

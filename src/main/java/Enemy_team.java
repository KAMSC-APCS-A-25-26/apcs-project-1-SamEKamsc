import java.util.Random;
public class Enemy_team {
    // Array here for easier access: fitness, intelligence, skill, motor, aerodynamics, structure
    public int[] stats = {0, 0, 0, 0, 0, 0};
    public String name;
    public Enemy_team(String name) {
        this.name = name;
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
                System.out.println("Enemy " + part[i] + ": " + this.stats[i]);
            }
            else {
                System.out.println("Enemy " + part[i] + ": not found");
            }
        }
    }


}

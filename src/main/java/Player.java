public class Player {
    // Array here for easier access: fitness, intelligence, skill, motor, aerodynamics, structure
    public int[] stats = {6, 7, 6, 7, 6, 7};
    public String name;
    public Player(String name) {
        this.name = name;
    }

    public void show_stats() {
        String[] part = {"driver fitness", "driver intelligence", "driver skill", "car motor", "car aerodynamics", "car structure"};
        for (int i = 0; i < 6; i++) {
            System.out.println(part[i] + ": " + this.stats[i]);
        }
    }
}

public class GameLoop {
    public static int[] week_cycler(int day) throws Exception {
        Globals.day += 1;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        System.out.println("-----------------------------------");
        System.out.println("Day " + Globals.day + ": " + days[day]);
        int next_day = day+1;
        int cont = 1;
        if (next_day==7) {
            next_day = 0;
        }
        if (Globals.day == 10) {
            cont = 0;
        }
        int[] ret = {next_day, cont};
        return ret;
    }
}

import java.util.Scanner;
import java.io.File;

public class TextAdventure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.println("                  THE");
        System.out.println("Γ ‾ ‾ ⅂    Γ ‾ ‾ ⅂    Γ ‾ ‾ ‾  Γ ‾ ‾ ‾");
        System.out.println("|     |    |     |    |        |");
        System.out.println("| — — ⅃    | — — |    |        | — — —");
        System.out.println("| \\        |     |    |         |");
        System.out.println("|   \\      |     |    L _ _ _   L _ _ _");
        System.out.println("             BY SAM EDMONDS");
        System.out.println("=======================================");
        System.out.println("Welcome to the RACE!");
        System.out.println("1. Play");
        System.out.println("2. How to play");
        var choice_one = sc.nextInt();
        switch (choice_one) {
            case 1:
                System.out.println("run");
            case 2:
                Scanner file_sc = new Scanner(new File("src/main/java/README.txt"));
                while (file_sc.hasNext()) {
                    System.out.println(file_sc.next());
                }

        }


    }
}
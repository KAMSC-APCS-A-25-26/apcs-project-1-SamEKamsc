import java.util.Scanner;
import java.io.File;

public class TextAdventure {
    public static void main(String[] args) throws Exception {
        TextAdventure.main_menu();
    }
    public static void main_menu() throws Exception {
        Scanner sc = new Scanner(System.in);
        Scanner readme_sc = new Scanner(new File("README.txt"));
        System.out.println("=======================================");
        System.out.println("                  THE");
        System.out.println("Γ ‾ ‾ ⅂    Γ ‾ ‾ ⅂    Γ ‾ ‾ ‾  Γ ‾ ‾ ‾");
        System.out.println("|     |    |     |    |        |");
        System.out.println("| — — ⅃    | — — |    |        | — — —");
        System.out.println("| \\        |     |    |        |");
        System.out.println("|   \\      |     |    L _ _ _  L _ _ _");
        System.out.println("             BY SAM EDMONDS");
        System.out.println("=======================================");
        System.out.println("Welcome to the RACE!");
        //var invalid_input = true;
        var choice_one = 0;
        while (choice_one != 1) {
            System.out.println("--------------");
            System.out.println("1. Play");
            System.out.println("2. How to play");
            System.out.print("Enter your choice: ");
            choice_one = sc.nextInt();
           // invalid_input = false;
            switch (choice_one) {
                case 1:
                    System.out.println("run");
                    break;
                case 2:
                    System.out.println();
                    while (readme_sc.hasNext()) {
                        System.out.println(readme_sc.nextLine());
                    }
                    System.out.println();
                    break;
                default:
           //         invalid_input = true;
                    System.out.println("Please enter one of the options");
                    break;

            }
        }
    }
}
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        REPL repl = new REPL();
        while (true) {
            System.out.print("> ");
            String[] input = scanner.nextLine().split(" ");
            switch(input[0].toLowerCase()) {
                case "load"-> {
                    System.out.println("todo: implement load");
                }
                case "ins" -> {
                    repl.validate(String.join(" ", Arrays.copyOfRange(input, 1, input.length)));
                }
                default -> {
                    System.out.println("Invalid command");
                }
            }
        }
    }
}
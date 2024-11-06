import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final LinkedList lines = new LinkedList();
        REPL repl = new REPL(lines);
        
        principal: while (true) {
            System.out.print("> ");
            String[] input = scanner.nextLine().split(" ");
            
            switch(input[0].toLowerCase()) {
                case "load"-> {
                    System.out.println("todo: implement load");
                }
                case "ins" -> {
                    repl.validateNWrite(String.join(" ", Arrays.copyOfRange(input, 1, input.length)));
                }
                case "run" -> {
                    repl.run();
                }
                case "exit" ->{
                    break principal;
                }
                case "show" ->{
                    System.out.println(lines.toString());
                }
                default -> {
                    System.out.println("Invalid command");
                }
            }
        }
    }
}
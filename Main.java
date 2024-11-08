import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
// todo list:
// 1. corrigir falta de verificação nos parametros das instruções

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final LinkedList lines = new LinkedList();
        REPL repl = new REPL(lines);
        File file = null;
        principal: while (true) {
            System.out.print("> ");
            String[] input = scanner.nextLine().split(" ");
            
            switch(input[0].toLowerCase()) {
                case "load"-> {
                    try {
                        file = new File(input[1]);
                        if (!input[1].matches(".*\\.ed1$")) {
                            System.out.println("Error: File not supported");
                            break;
                        }
                        Scanner fileScanner = new Scanner(file);
                        lines.clear();
                        while (fileScanner.hasNextLine()) {
                            String[] line = fileScanner.nextLine().split(" ");
                            int lineNum = Integer.parseInt(line[0]);
                            if(line.length == 4){
                                lines.addByIndex(new Line(lineNum, line[1], new String[]{line[2], line[3]}), lineNum);
                                continue;
                            }
                            lines.addByIndex(new Line(lineNum, line[1], new String[]{line[2]}), lineNum);

                        }
                        fileScanner.close();
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
                case "ins" -> {
                    repl.validateNWrite(String.join(" ", Arrays.copyOfRange(input, 1, input.length)));
                }
                case "run" -> {
                    repl.run();
                }
                case "del" -> {
                    
                switch (input.length) {
                    case 2 -> {
                        lines.removeByIndex(Integer.parseInt(input[1]));
                    }
                    case 3 -> {
                        if (lines.getFirstNode() != null) {
                            int start = Integer.parseInt(input[1]);

                            while(start <= Integer.parseInt(input[2])){
                                lines.removeByIndex(start);
                                start++;
                            }
                        } else {
                            System.err.println("Error: No lines to delete");
                        }
                    }
                    default -> System.err.println("Error: Command not supported");
                }
                }
                case "save" -> {
                switch (input.length) {
                    case 2 -> {
                        if(input[1].matches(".*\\.ed1$")){
                            try {
                                lines.saveToFile(input[1]);
                                
                            } catch (Exception e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                        } else {
                            System.err.println("Error: File not supported");
                        }
                    }
                    case 1 -> {
                        if(file == null){
                            System.err.println("Error: No file loaded");
                        } else {
                            try {
                                lines.saveToFile(file.getName());
                                
                            } catch (Exception e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                        }
                    }
                    default -> System.err.println("Error: Command not supported");
                }
                }
                case "exit" ->{
                    break principal;
                }
                // for dev purposes
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

// links:
// https://www.geeksforgeeks.org/linked-list-in-java/
// https://www.javatpoint.com/how-to-open-a-file-in-java

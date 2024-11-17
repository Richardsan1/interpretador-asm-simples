import java.io.File;
import java.io.FileNotFoundException;
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
                        if(file != null){
                            if(findChanges(lines, file)){
                                boolean continueLoop = true;
                                while(continueLoop){
                                    System.out.print("\nError: Unsaved changes detected, do you want to save before continue? (y/n) ");
                                    switch (scanner.nextLine().toLowerCase()) {
                                        case "y" -> {
                                            lines.saveToFile(file.getAbsolutePath());
                                            continueLoop = false;
                                            break;
                                        }
                                        case "n" -> {
                                            continueLoop = false; 
                                            break;
                                        }
                                        default -> System.out.println("Invalid option");
                                    }
                                }
                            }
                        }
                        if (!input[1].matches(".*\\.ed1$")) {
                            System.out.println("Error: File not supported");
                            break;
                        }
                        file = new File(input[1]);
                        Scanner fileScanner = new Scanner(file);
                        lines.clear();
                        while (fileScanner.hasNextLine()) {
                            String[] line = fileScanner.nextLine().split(" ");
                            int lineNum = Integer.parseInt(line[0]);
                            if(line.length == 4){
                                lines.addByIndex(new Line(lineNum, line[1], new String[]{line[2], line[3]}), lineNum);
                                continue;
                            }
                            lines.addByIndex(new Line(lineNum, line[1], new String[]{line[2], ""}), lineNum);

                        }
                        fileScanner.close();
                        System.out.println("File loaded successfully");
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                        file = null;
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
                                    System.out.println("File saved successfully");
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
                                    System.out.println("File saved successfully");
                                } catch (Exception e) {
                                    System.err.println("Error: " + e.getMessage());
                                }
                            }
                        }
                        default -> System.err.println("Error: Command not supported");
                    }
                }
                case "list" -> {
                    if (lines.getFirstNode() == null) {
                        System.out.println("Error: No lines to list");
                        break;
                    }
                    int i = 0;
                    while(true){
                        if(lines.getLines(20, i)){
                            System.out.print("End of file, press enter to continue...");
                            scanner.nextLine();
                            break;
                        }
                        i += 20;
                        System.out.print("Press enter to continue or type 'exit' to stop: ");
                        if (scanner.nextLine().equals("exit")) {
                            break;
                        }
                    }
                }
                case "exit" ->{
                    try {
                        if(file != null){
                            if(findChanges(lines, file)){
                                boolean continueLoop = true;
                                while(continueLoop){
                                    System.out.print("\nUnsaved changes detected, do you want to save before exit? (y/n) ");
                                    switch (scanner.nextLine().toLowerCase()) {
                                        case "y" -> {
                                            lines.saveToFile(file.getAbsolutePath());
                                            continueLoop = false; 
                                            break;
                                        }
                                        case "n" -> {
                                            continueLoop = false; 
                                            break;
                                        }
                                        default -> System.out.println("Invalid option");
                                    }
                                }
                            }
                        }
                        
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
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
    public static boolean findChanges(LinkedList loaded, File file) throws FileNotFoundException{
        try (Scanner fileScanner = new Scanner(file)) {
            Node<Line> dummy = loaded.getFirstNode();
            while (fileScanner.hasNextLine()) {
                if(dummy == null){
                    return true;
                }
                String line = fileScanner.nextLine();
                if(!line.equals(dummy.getData().toString())){
                    return true;
                }
                dummy = dummy.getNext();
            }
            if(dummy != null){
                return true;
            }
        }
        return false;
    }
}

// links:
// https://www.geeksforgeeks.org/linked-list-in-java/
// https://www.javatpoint.com/how-to-open-a-file-in-java

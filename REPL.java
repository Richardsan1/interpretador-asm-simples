
import java.util.Scanner;

public class REPL{
    private int[] registers;
    private LinkedList<Line> lines;
    
    // read and validate the sentence
    private void validate(String sentence){
        String[] words = sentence.toLowerCase().split("\\s+");

        if(!words[0].matches("[0-9]+")){
            System.err.println("Error: not allowed");
            return;
        }
        if (words.length == 3 && words[2].matches("[a-z]+")){
            String[] vars = new String[1];
            vars[0] = words[2];
            lines.add(new Line(Integer.parseInt(words[0]), words[1], vars));
        } else if (words.length == 4 && words[2].matches("[a-z]+") && words[3].matches("[0-9]+") || words[3].matches("[a-z]+")){
            String[] vars = new String[2];
            vars[0] = words[2];
            vars[1] = words[3];
            lines.add(new Line(Integer.parseInt(words[0]), words[1], vars));

        } else {
            System.err.println("Error: not allowed");
            return;
        }
        // boolean err = 
        selectInstruction(words[1]);

    }

    private boolean selectInstruction(String instruction){
        switch (instruction) {
            case "mov" -> {
            }
            case "inc" ->{

            }
            case "dec" ->{

            }
            case "add" ->{

            }
            case "sub" ->{

            }
            case "mul" ->{

            }
            case "div" ->{

            }
            case "jnz" ->{

            }
            case "out" ->{

            }
            default ->{
                System.err.println("Error: command not permited");
                return true;
            }
        }
        return false;
    }

    public void run(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("> ");
            validate(scan.next());
        }
    } 
}
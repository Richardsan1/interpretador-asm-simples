
import java.util.Scanner;

public class REPL{
    private final int[] registers;
    private final LinkedList<Line> lines;
    
    public REPL(){
        this.registers = new int[26];
        this.lines = null;
    }
    
    public void run(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("> ");
            validate(scan.next());
        }
    } 
    
    // read and validate the sentence
    private void validate(String sentence){
        String[] words = sentence.toLowerCase().split("\\s+");

        if(!words[0].matches("[0-9]+")){
            System.err.println("Error: not allowed");
            return;
        }
        if (words.length == 3 && words[2].matches("[a-z]")){
            String[] vars = new String[1];
            vars[0] = words[2];
            lines.add(new Line(Integer.parseInt(words[0]), words[1], vars));
            selectInstruction(words[1], words[2], "");
        } else if (words.length == 4 && words[2].matches("[a-z]") && words[3].matches("[0-9]+") || words[3].matches("[a-z]")){
            String[] vars = new String[2];
            vars[0] = words[2];
            vars[1] = words[3];
            lines.add(new Line(Integer.parseInt(words[0]), words[1], vars));
            selectInstruction(words[1], words[2], words[3]);
        } else {
            System.err.println("Error: not allowed");
        }

    }

    private boolean selectInstruction(String instruction, String x, String y){
        switch (instruction) {
            case "mov" -> {
                mov(x,y);
            }
            case "inc" ->{
                inc(x);
            }
            case "dec" ->{
                dec(x);
            }
            case "add" ->{
                add(x,y);
            }
            case "sub" ->{
                sub(x,y);
            }
            case "mul" ->{
                mul(x,y);
            }
            case "div" ->{
                div(x,y);
            }
            case "jnz" ->{
                jnz(x,y);
            }
            case "out" ->{
                out(x);
            }
            default ->{
                System.err.println("Error: command not permited");
                return true;
            }
        }
        return false;
    }
    private void mov(String x, String y){
        this.registers[x.charAt(0)] = Integer.parseInt(y);
    }
    private void inc(String x){
        this.registers[x.charAt(0)] += 1;
    }
    private void dec(String x){
        this.registers[x.charAt(0)] -= 1;
    }
    private void add(String x, String y){
        this.registers[x.charAt(0)] += this.registers[y.charAt(0)];
    }
    private void sub(String x, String y){
        this.registers[x.charAt(0)] -= this.registers[y.charAt(0)];
    }
    private void mul(String x, String y){
        this.registers[x.charAt(0)] *= this.registers[y.charAt(0)];
    }
    private void div(String x, String y){
        this.registers[x.charAt(0)] /= this.registers[y.charAt(0)];
    }
    private void jnz(String x, String y){
        System.err.println("TODO: implement function");
    }
    private void out(String x){
        System.out.println(this.registers[x.charAt(0)]);
    }
}
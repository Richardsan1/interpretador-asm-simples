
public class REPL{
    private int[] registers;
    private final LinkedList<Line> lines;
    
    public REPL(int[] registers){
        this.registers = registers;
        this.lines = new LinkedList<>();
    }
    public REPL(){
        this.registers = new int[26];
        this.lines = new LinkedList<>();
    }
    
    // read and validate the sentence
    public void validate(String sentence){
        String[] words = sentence.toLowerCase().split("\\s+");

        if(!words[0].matches("[0-9]+")){
            System.err.println("Error: not allowed");
            return;
        }
        if (words.length == 3 && words[2].matches("[a-z]")){
            String[] vars = new String[1];
            vars[0] = words[2];
            newLine(Integer.parseInt(words[0]), words[1], vars);
            selectInstruction(words[1], words[2], "");
        } else if (words.length == 4 && words[2].matches("[a-z]") && words[3].matches("[0-9]+") || words[3].matches("[a-z]")){
            String[] vars = new String[2];
            vars[0] = words[2];
            vars[1] = words[3];
            newLine(Integer.parseInt(words[0]), words[1], vars);
            selectInstruction(words[1], words[2], words[3]);
        } else {
            System.err.println("Error: not allowed");
        }

    }
    public LinkedList<Line> getLines(){
        return this.lines;
    }
    public void newLine(int line, String instruction, String[] vars){
        this.lines.add(new Line(line, instruction, vars));
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
        int value = Integer.parseInt(y);
        this.registers[x.charAt(0) - 97] = value;
        System.out.println(this.registers[x.charAt(0) - 97]);
    }
    private void inc(String x){
        this.registers[x.charAt(0)-97] += 1;
    }
    private void dec(String x){
        this.registers[x.charAt(0)-97] -= 1;
    }
    private void add(String x, String y){
        this.registers[x.charAt(0)-97] += this.registers[y.charAt(0)-97];
        System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
    }
    private void sub(String x, String y){
        this.registers[x.charAt(0)-97] -= this.registers[y.charAt(0)-97];
    }
    private void mul(String x, String y){
        this.registers[x.charAt(0)-97] *= this.registers[y.charAt(0)-97];
    }
    private void div(String x, String y){
        this.registers[x.charAt(0)-97] /= this.registers[y.charAt(0)-97];
    }
    private void jnz(String x, String y){
        System.out.println("todo: implement jnz");
    }
    private void out(String x){
        System.out.println(this.registers[x.charAt(0)-97]);
    }
}
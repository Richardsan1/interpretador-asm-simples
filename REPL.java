


public class REPL{
    private int[] registers;
    private final LinkedList lines;
    
    public REPL(LinkedList lines){
        this.registers = new int[26];
        this.lines = lines;
    }
    
    public void validateNWrite(String sentence){
        String[] words = sentence.toLowerCase().split("\\s+");

        if(!words[0].matches("[0-9]+")){
            System.err.println("Error: line number must be an integer");
            return;
        }
        if (words.length == 3 && words[2].matches("[a-z]") ){
            if(!(words[1].equals("out") || words[1].equals("mov"))){
                System.err.println("Error: invalid arguments for instruction");
                return;
            }
            String[] vars = new String[1];
            vars[0] = words[2];
            newLine(Integer.parseInt(words[0]), words[1], vars, Integer.parseInt(words[0]));
        } else if (words.length == 4 && words[2].matches("[a-z]") && words[3].matches("[0-9]+") || words[3].matches("[a-z]")){
            String[] vars = new String[2];
            vars[0] = words[2];
            vars[1] = words[3];
            newLine(Integer.parseInt(words[0]), words[1], vars, Integer.parseInt(words[0]));
        } else {
            System.err.println("Error: no properly formatted instruction");
        }

    }

    public void newLine(int line, String instruction, String[] vars, int index){
        System.out.println(this.lines.addByIndex(new Line(line, instruction, vars), index));
    }
    public void run(){
        if(this.lines.getFirstNode() == null) {
            System.err.println("Error: no lines to run");
            return;
        }
        Node<Line> dummy = this.lines.getFirstNode();
        for (int i = 0; i < this.lines.getSize(); i++){
            Line line = dummy.getData();
            if (selectInstruction(line.getInstruction(), line.getVars()[0], line.getVars()[1], line.getId())){
                break;
            }

            dummy = dummy.getNext();
        }
        this.registers = new int[26];
    }
    private boolean selectInstruction(String instruction, String x, String y, int line){
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
                System.err.println("Error: non-existing command at line " + line);
                return true;
            }
        }
        return false;
    }
    private void mov(String x, String y){
        int value = Integer.parseInt(y);
        this.registers[x.charAt(0) - 97] = value;
        System.out.println(x + " = " + value);
    }
    private void inc(String x){
        this.registers[x.charAt(0)-97] += 1;
        System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
    }
    private void dec(String x){
        this.registers[x.charAt(0)-97] -= 1;
        System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
    }
    private void add(String x, String y){
        this.registers[x.charAt(0)-97] += this.registers[y.charAt(0)-97];
        System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
    }
    private void sub(String x, String y){
        this.registers[x.charAt(0)-97] -= this.registers[y.charAt(0)-97];
        System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
    }
    private void mul(String x, String y){
        this.registers[x.charAt(0)-97] *= this.registers[y.charAt(0)-97];
        System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
    }
    private void div(String x, String y){
        this.registers[x.charAt(0)-97] /= this.registers[y.charAt(0)-97];
        System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
    }
    private void jnz(String x, String y){
        System.out.println("todo: implement jnz");
    }
    private void out(String x){
        System.out.println(this.registers[x.charAt(0)-97]);
        System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
    }
}
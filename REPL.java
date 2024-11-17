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
        if(words[1].equals("inc") || words[1].equals("dec")|| words[1].equals("out")){
            if(words.length != 3){
                System.err.println("Error: invalid number of arguments");
                return;
            }
            if(!words[2].matches("[a-z]")){
                System.err.println("Error: invalid register");
                return;
            }

            newLine(Integer.parseInt(words[0]), words[1], new String[]{words[2], ""}, Integer.parseInt(words[0]));
        }
        else if(words[1].equals("add") || words[1].equals("sub") || words[1].equals("mul") || words[1].equals("div")){
            if(words.length != 4){
                System.err.println("Error: invalid number of arguments");
                return;
            }
            if(!words[2].matches("[a-z]")){
                System.err.println("Error: invalid register");
                return;
            }
            if(!words[3].matches("[a-z]")){
                System.err.println("Error: invalid register");
                return;
            }

            newLine(Integer.parseInt(words[0]), words[1], new String[]{words[2], words[3]}, Integer.parseInt(words[0]));
        }
        else if(words[1].equals("jnz") || words[1].equals("mov")){
            if(words.length != 4){
                System.err.println("Error: invalid number of arguments");
                return;
            }
            if(!words[2].matches("[a-z]")){
                System.err.println("Error: invalid register");
                return;
            }
            if(!words[3].matches("[0-9]+")){
                System.err.println("Error: invalid number");
                return;
            }

            newLine(Integer.parseInt(words[0]), words[1], new String[]{words[2], words[3]}, Integer.parseInt(words[0]));
        }
        else{
            System.err.println("Error: non-existing command");
        }

    }

    private void newLine(int line, String instruction, String[] vars, int index){
        System.out.println(this.lines.addByIndex(new Line(line, instruction, vars), index));
    }
    public void run(){
        if(this.lines.getFirstNode() == null) {
            System.err.println("Error: no lines to run");
            return;
        }
        Node<Line> dummy = this.lines.getFirstNode();
       while(dummy != null){
            Line line = dummy.getData();
            if(line.getInstruction().equals("jnz")){
                if(this.registers[line.getVars()[0].charAt(0)-97]!= 0){
                    System.out.println("foi para a linha: "+ this.lines.getNodeByIndex(Integer.parseInt(line.getVars()[1])).getData().getId());
                    dummy = this.lines.getNodeByIndex(Integer.parseInt(line.getVars()[1]));
                    continue;
                }
            }
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
                return false;
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
    }
    private void inc(String x){
        this.registers[x.charAt(0)-97] += 1;
    }
    private void dec(String x){
        this.registers[x.charAt(0)-97] -= 1;
    }
    private void add(String x, String y){
        this.registers[x.charAt(0)-97] += this.registers[y.charAt(0)-97];
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
    private void out(String x){
        System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
    }
}
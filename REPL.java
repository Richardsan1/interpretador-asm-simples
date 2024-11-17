public class REPL{
    private Integer[] registers;
    private final LinkedList lines;
    
    public REPL(LinkedList lines){
        this.registers = new Integer[26];
        this.lines = lines;
    }
    
    public void validateNWrite(String sentence){
        String[] words = sentence.toLowerCase().split("\\s+");

        if(!words[0].matches("[0-9]+")){
            System.err.println("Error: line number must be an integer");
            return;
        }
        switch (words[1]) {
            case "inc", "dec", "out" -> {
                if(words.length != 3){
                    System.err.println("Error: invalid number of arguments");
                    return;
                }   if(!words[2].matches("[a-z]")){
                    System.err.println("Error: invalid register");
                    return;
                }   newLine(Integer.parseInt(words[0]), words[1], new String[]{words[2], ""}, Integer.parseInt(words[0]));
            }
            case "add", "sub", "mul", "div", "mov", "jnz" -> {
                if(words.length != 4){
                    System.err.println("Error: invalid number of arguments");
                    return;
                }   if(!words[2].matches("[a-z]")){
                    System.err.println("Error: invalid register");
                    return;
                }   if(!words[3].matches("[a-z]") && !words[3].matches("[0-9]+")){
                    System.err.println("Error: invalid register");
                    return;
                }   newLine(Integer.parseInt(words[0]), words[1], new String[]{words[2], words[3]}, Integer.parseInt(words[0]));
            }
            default -> System.err.println("Error: non-existing command");
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
                System.err.println("Error: invalid register at line " + line.getId());
                break;
            }

            dummy = dummy.getNext();
        }
        this.registers = new Integer[26];
    }
    private boolean selectInstruction(String instruction, String x, String y, int line){
        if(this.registers[x.charAt(0)-97] == null && !instruction.matches("mov")){
            return true;
        } else if (!y.equals("") && !y.matches("[0-9]+") && this.registers[y.charAt(0)-97] == null){
            return true;
        }
        switch (instruction) {
            case "mov" -> {
                if(y.matches("[0-9]+")){
                    this.registers[x.charAt(0) - 97] = Integer.valueOf(y);
                } else {
                    this.registers[x.charAt(0) - 97] = this.registers[y.charAt(0) - 97];
                }
            }
            case "inc" ->{
                this.registers[x.charAt(0)-97] += 1;
            }
            case "dec" ->{
                this.registers[x.charAt(0)-97] -= 1;
            }
            case "add" ->{
                if(y.matches("[0-9]+")){
                    this.registers[x.charAt(0)-97] += Integer.valueOf(y);
                } else {
                    this.registers[x.charAt(0)-97] += this.registers[y.charAt(0)-97];
                }
            }
            case "sub" ->{
                if(y.matches("[0-9]+")){
                    this.registers[x.charAt(0)-97] -= Integer.valueOf(y);
                } else {
                    this.registers[x.charAt(0)-97] -= this.registers[y.charAt(0)-97];
                }
            }
            case "mul" ->{
                if(y.matches("[0-9]+")){
                    this.registers[x.charAt(0)-97] *= Integer.valueOf(y);
                } else {
                    this.registers[x.charAt(0)-97] *= this.registers[y.charAt(0)-97];
                }
            }
            case "div" ->{
                if(y.matches("[0-9]+")){
                    this.registers[x.charAt(0)-97] /= Integer.valueOf(y);
                } else {
                    this.registers[x.charAt(0)-97] /= this.registers[y.charAt(0)-97];
                }
            }
            case "jnz" ->{
                return false;
            }
            case "out" ->{
                System.out.println(x + " = " + this.registers[x.charAt(0)-97]);
            }
            default ->{
                System.err.println("Error: non-existing command at line " + line);
                return true;
            }
        }
        return false;
    }
}
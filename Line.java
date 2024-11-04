public class Line{
    private final int num;
    private final String instruction;
    private final String[] vars;
    
    public Line(int line, String instruction, String[] vars){
        this.num = line;
        this.instruction = instruction;
        this.vars = vars;
    }
    
    public int getNum(){
        return this.num;
    }
    
    public String getInstruction(){
        return this.instruction;
    }
    
    public String[] getVars(){
        return this.vars;
    }
}
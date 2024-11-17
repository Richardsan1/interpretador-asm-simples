public class Line{
    private final int id;
    private final String instruction;
    private final String[] vars;
    
    public Line(int line, String instruction, String[] vars){
        this.id = line;
        this.instruction = instruction;
        this.vars = vars;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getInstruction(){
        return this.instruction;
    }
    
    public String[] getVars(){
        return this.vars;
    }

    @Override
    public String toString(){
        if (this.vars[1].equals("")){
            return this.id + " " + this.instruction + " " + this.vars[0];
        }
        return this.id + " " + this.instruction + " " + String.join(" ", this.vars);
    }
}
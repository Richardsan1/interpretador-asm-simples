import java.io.FileWriter;
import java.io.IOException;

public class LinkedList{
    private Node<Line> firstNode;

    public LinkedList(){
        this.firstNode = null;
    }

    public Node<Line> getFirstNode(){
        return this.firstNode;
    }
    public Node<Line> getNodeByIndex(int index){
        Node<Line> dummy = this.firstNode;
        
        while(dummy != null){
            if(dummy.getData().getId() == index){
                break;
            }
            dummy = dummy.getNext();
        }
        return dummy;
    }
    public Node<Line> getNodeByPos(int index){
        if(index < 0) return null;
        if(index == 0) return this.firstNode;

        Node<Line> dummy = this.firstNode;
        
        for(int i = 0; i < index; i++){
            if(dummy == null) return null;
            dummy = dummy.getNext();
        }
        return dummy;
    }

    public String addByIndex(Line data, int index) {
        Node<Line> newNode = new Node<>(data);
        if (this.firstNode == null || this.firstNode.getData().getId() > index) {
            newNode.setNext(this.firstNode);
            this.firstNode = newNode;
            return "Nova linha adicionada";
        } else if (this.firstNode.getData().getId() == index) {
            newNode.setNext(this.firstNode.getNext());
            this.firstNode = newNode;
            return "Linha alterada";
        }

        Node<Line> dummy = this.firstNode;
        while (dummy.getNext() != null) {
            if (dummy.getNext().getData().getId() == index) {
                newNode.setNext(dummy.getNext().getNext());
                dummy.setNext(newNode);
                return "Linha alterada";
            } else if (dummy.getNext().getData().getId() > index) {
                newNode.setNext(dummy.getNext());
                dummy.setNext(newNode);
                return "Nova linha adicionada";
            }
            dummy = dummy.getNext();
        }

        dummy.setNext(newNode);
        return "Nova linha adicionada";
    }

    public void removeByIndex(int index){
        Node<Line> dummy = this.firstNode;
        if(dummy == null) {
            System.err.println("Error: Empty file");
            return;
        }
        if(dummy.getData().getId() == index){
            this.firstNode = dummy.getNext();
            return;
        }

        while(dummy != null){
            if(dummy.getNext() == null || dummy.getNext().getData().getId() > index){
                System.err.println("Error: Line "+ index +" not found");
                break;
            }
            if (dummy.getNext().getData().getId() == index) {
                dummy.setNext(dummy.getNext().getNext());
                break;
            } 

            dummy = dummy.getNext();
        }

    }
    
    public void clear(){
        this.firstNode = null;
    }

    public void saveToFile(String filepath) throws IOException{
        FileWriter writer = new FileWriter(filepath);
        Node<Line> dummy = this.firstNode;
        
        while (dummy != null) {
            writer.write(dummy.getData().toString());
            dummy = dummy.getNext();
        }
        writer.close();
    }

    public boolean getLines(int chunk, int start){
        Node<Line> dummy = getNodeByPos(start);
        int count = 1;
        while(dummy != null){
            System.out.print(dummy.getData().getId()+" "+dummy.getData().getInstruction()+ " ");
            System.out.print(String.join(" ", dummy.getData().getVars()));
            System.out.println();
            count++;
            if(count == chunk){
                return false;
            }
            dummy = dummy.getNext();
        }
        return true;

    }
    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    Node<Line> current = this.firstNode;
    while (current != null) {
        sb.append("{linha: ");
        sb.append(current.getData().getId());
        sb.append(", instrucao: ");
        sb.append(current.getData().getInstruction());
        sb.append("}");

        if (current.getNext() != null) {
            sb.append(" -> ");
        }
        current = current.getNext();
    }
    return sb.toString();
}
    
}
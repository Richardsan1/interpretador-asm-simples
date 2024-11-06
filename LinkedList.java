// não sei se vai ser necessário essa class, ou se ela está pronta, mas vai que
public class LinkedList{
    private int size;
    private Node<Line> firstNode;
    private Node<Line> lastNode;

    public LinkedList(){
        this.size = 0;
        this.firstNode = null;
    }

    public int getSize(){
        return this.size;
    }
    public Node<Line> getFirstNode(){
        return this.firstNode;
    }

    private void add(Line data){
        if(this.firstNode == null){
            this.firstNode = new Node<>(data);
            this.lastNode = this.firstNode;
            this.size++;
        }else{
            Node<Line> newNode = new Node<>(data);
            this.lastNode.setNext(newNode);
            this.lastNode = newNode;
            this.size++;
        }
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
        // index starts at 0
        if(index >= size || index < 0) return;
        if(index == 0){
            this.firstNode = this.firstNode.getNext();
            return;
        }
        
        Node<Line> dummy = this.firstNode;
        for(int i = 0; i < index-1; i++){
            dummy = dummy.getNext();
        }
        dummy.setNext(dummy.getNext().getNext());
        this.size--;
    }
    public void removeByLine(int index){
        // todo: implement
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
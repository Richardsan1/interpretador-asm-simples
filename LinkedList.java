// não sei se vai ser necessário essa class, ou se ela está pronta, mas vai que
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
        if(dummy == null) return;
        if(dummy.getData().getId() == index){
            this.firstNode = dummy.getNext();
            return;
        }

        while(dummy != null){
            if(dummy.getNext() == null || dummy.getNext().getData().getId() > index){
                break;
            }
            if (dummy.getNext().getData().getId() == index) {
                dummy.setNext(dummy.getNext().getNext());
                break;
            } 

            dummy = dummy.getNext();
        }

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
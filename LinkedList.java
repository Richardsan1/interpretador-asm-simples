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

    public void add(Line data){
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
    public String addByIndex(Line data, int index){
        Node<Line> dummy = this.firstNode;
        for(int i = 0; i <= size; i++){
            if(dummy == null || dummy.getNext() == null){
                add(data);
                return "Nova linha adicionada";
            }
            else if (dummy.getNext().getData().getId() == index) {
                Node<Line> newNode = new Node<>(data);
                newNode.setNext(dummy.getNext().getNext());
                dummy.setNext(newNode);
                return "Linha alterada";
            }

            dummy = dummy.getNext();
        }
        return "Error";
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
        sb.append(current.getData());
        if (current.getNext() != null) {
            sb.append(" -> ");
        }
        current = current.getNext();
    }
    return sb.toString();
}
    
}
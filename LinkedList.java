// não sei se vai ser necessário essa class, ou se ela está pronta, mas vai que
public class LinkedList<T>{
    private int size;
    private Node<T> firstNode;
    private Node<T> lastNode;

    public LinkedList(){
        this.size = -1;
        this.firstNode = null;
    }

    public int getSize(){
        return this.size;
    }
    public Node<T> getFirstNode(){
        return this.firstNode;
    }

    public void add(T data){
        if(this.firstNode == null){
            this.firstNode = new Node<>(data);
            this.lastNode = this.firstNode;
            this.size++;
        }else{
            Node<T> newNode = new Node<>(data);
            this.lastNode.setNext(newNode);
            this.lastNode = newNode;
            this.size++;
        }
    }

    public void remove(int index){
        // index starts at 0
        if(index > size || index < 0) return;
        if(index == 0){
            this.firstNode = this.firstNode.getNext();
            return;
        }
        
        Node<T> dummy = this.firstNode;
        for(int i = 0; i < index-1; i++){
            dummy = dummy.getNext();
        }
        dummy.setNext(dummy.getNext().getNext());
        this.size--;
    }

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    Node<T> current = this.firstNode;
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
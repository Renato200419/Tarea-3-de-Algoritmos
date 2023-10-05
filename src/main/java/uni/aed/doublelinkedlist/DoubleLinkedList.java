package uni.aed.doublelinkedlist;

public class DoubleLinkedList {
    DNodo head;
    DNodo ultimo;
    
    public void addFirst(int data){
        DNodo newNodo=new DNodo(data);
        if(head==null){
            head=newNodo;
            ultimo=newNodo;
        }else{
            newNodo.next=head;
            head.prev=newNodo;
            head=newNodo;
        }
    }
    public void addLast(int data){
        DNodo newNodo=new DNodo(data);
        if(ultimo==null){
            head=newNodo;
            ultimo=newNodo;            
        }else{
            newNodo.prev =ultimo;
            ultimo.next=newNodo;
            ultimo=newNodo;            
        }
    }
    public void remove(int valor){
        DNodo actual =head;
        while(actual!=null && actual.data!=valor){
            actual=actual.next;
        }
        if(actual!=null){
            if(actual==head){
                head=head.next;
                if(head!=null)
                    head.prev=null;
                else
                    ultimo=null;                
            }else if(actual==ultimo){
                ultimo=actual.prev;
                ultimo.next=null;
            }else{
                actual.prev.next=actual.next;
                actual.next.prev=actual.prev;
            }
        }
    }
    
    public void clear(){
        head=null;
        ultimo=null;
    }
    public boolean isEmpty() {
    return head == null;
}

    public String toString(){
        DNodo actual=head;
        String lista="";
        while(actual!=null){
            if(actual.prev==null)
                lista="null<-"+actual.data+"->"+actual.next.data;
            else if(actual.next==null)
                lista=lista+"||"+actual.prev.data+"<-"+
                        actual.data+"->null";
            else
                lista=lista+"||"+actual.prev.data+"<-"+actual.data+"->"+
                        actual.next.data;
            actual=actual.next;
        }
        return lista;
    }

    public void HeapSort() {
    int n = length(); // Obtiene la longitud de la lista
    if (n < 2) return; // No se necesita ordenar

    // Fase de construcción del Max Heap
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(n, i);
    }

    // Fase de ordenamiento
    for (int i = n - 1; i >= 0; i--) {
        // Intercambio del primer y último elemento
        swap(0, i);

        // Reajuste del heap
        heapify(i, 0);
    }
}

    private void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && getNodeAt(left).data > getNodeAt(largest).data) {
            largest = left;
        }

        if (right < n && getNodeAt(right).data > getNodeAt(largest).data) {
            largest = right;
        }

        if (largest != i) {
            swap(i, largest);
            heapify(n, largest);
        }
    }

    private void swap(int i, int j) {
        DNodo node_i = getNodeAt(i);
        DNodo node_j = getNodeAt(j);
        int temp = node_i.data;
        node_i.data = node_j.data;
        node_j.data = temp;
    }

    private DNodo getNodeAt(int index) {
        DNodo current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private int length() {
        int count = 0;
        DNodo current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}

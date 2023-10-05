package uni.aed.linkedlist;

public class ListaEnlazada {
    DNodo head;
    
    public void addFirst(int data){
        DNodo newNodo=new DNodo(data);
        newNodo.next=head;
        head=newNodo;
    }
    
    public void addLast(int data){
        DNodo newNodo=new DNodo(data);
        if(head==null){
            head=newNodo;
            return;
        }
        DNodo current =head;
        while(current.next!=null)
            current=current.next;        
        current.next=newNodo;
    }
    public void remove(int data){
        if(head==null)
            return;
        //si el elemento a eliminar es el primer nodo
        if(head.data==data){
            head=head.next;
            return;
        }
        //si el nodo a eliminar no es el primer nodo
        DNodo current=head;
        while(current.next!=null && current.next.data!=data)
            current=current.next;
        
        if(current.next!=null)
            current.next=current.next.next;
        
    }
    public void clear(){
        head=null;     
    }
    public boolean isEmpty() {
        return head == null;
    }
    public String toString(){
        String lista="";
        DNodo current =head;
        while(current!=null){
            if(lista.length()==0)
                lista=""+current.data;
            else
                lista=lista +"->"+current.data;
            current=current.next;
        }
        return lista;        
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

    private void swap(int i, int j) {
        DNodo node_i = getNodeAt(i);
        DNodo node_j = getNodeAt(j);
        int temp = node_i.data;
        node_i.data = node_j.data;
        node_j.data = temp;
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

    
    
    
}

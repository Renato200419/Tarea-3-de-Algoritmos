package uni.aed.linkedlistTDA;

public class LinkedlistTDA <E> implements ListTDA <E>{
    private Nodo head;
    private Nodo cola;
    private int count;
    public LinkedlistTDA(){
        clear();
    }
    
    
    @Override
    public void add(E elemento) {
        Nodo newNodo = new Nodo(elemento);
        if(count ==0)    //Cuenta cuantos elementos tiene la lista enlazada
            head=cola=newNodo;
        else{
            cola.setNext(newNodo); //Apunta el siguiente nodo al nuevo nodo
            cola=newNodo; //se iguala el ultimo nodo con el nuevo nodo
        }
        count++;
    }

    @Override
    public void add(int index, E elemento) throws IndexOutOfBoundsException {
        revisaPosInsercion(index);
        Nodo apt=head;
        Nodo newNodo = new Nodo(elemento);
        if(index==0){
            newNodo.setNext(head);
            head=newNodo;
        }else{
            for(int i=0;i<index;i++)
                apt=apt.getNext();
            newNodo.setNext(apt.getNext());
            apt.setNext(newNodo);
        }
        if(index==count)
            cola=newNodo;
        count++;
    }
    
    private void revisaPosInsercion(int index){
        if(index<0)
            throw new  IndexOutOfBoundsException("Indice ingresado es invalido");
        else if(index>size())
            throw new  IndexOutOfBoundsException("Indice es mayor al tamaño de la lista");
    }
    
    @Override
    public void clear() {
        head=null;
        cola=null;
        count=0;
    }

    @Override
    public boolean contain(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int indexOf(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E dele(int index) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(E elemento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E modify(int index, E elemento) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void imprimir() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

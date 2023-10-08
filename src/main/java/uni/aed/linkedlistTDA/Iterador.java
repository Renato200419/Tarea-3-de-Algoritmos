package uni.aed.linkedlistTDA;

public class Iterador <E> implements IteratorTDA <E>{
    private Nodo actual;
    public Iterador(Nodo nodo){
        this.actual=nodo;
    }
    @Override
    public boolean hasNext() {
        return actual!=null;
    }

    @Override
    public E next() throws NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

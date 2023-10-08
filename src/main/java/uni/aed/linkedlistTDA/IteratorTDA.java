package uni.aed.linkedlistTDA;

public interface IteratorTDA <E>{
    public boolean  hasNext();
    public E next()throws NoSuchElementException;
}

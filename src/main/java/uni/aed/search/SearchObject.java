package uni.aed.search;

public class SearchObject {
    
    public final int NO_ENCONTRADO = -1;
    public int Lineal(Object[] X,Object valor){ 
        int loc=0;                
        while(loc<X.length && ((Comparable)X[loc]).compareTo(valor)!=0){
            loc++;
        }
        if(loc==X.length)
            return NO_ENCONTRADO;
        else
            return loc;
    }
    
    public int Binaria(Object[] X,Object valor){ 
        int bajo=0,
            alto=X.length-1,
            medio=(bajo+alto)/2;        
        while(bajo<=alto && ((Comparable)X[medio]).compareTo(valor)!=0){            
            if(((Comparable)X[medio]).compareTo(valor)>0)
                alto=medio-1;                
            else
                bajo=medio+1;
            medio=(bajo+alto)/2;
        }
        if(bajo>alto)
            medio=NO_ENCONTRADO;
        return medio;
    }
    
}

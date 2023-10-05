package uni.aed.ordenamiento;
/**
 *
 * @author hp
 */
public class Ordenamiento {
    int nIntercambios=0;
    int nComparaciones=0;
    long tEjecucion=0;    

    public void setnIntercambios(int nIntercambios) {
        this.nIntercambios = nIntercambios;
    }
    public void setnComparaciones(int nComparaciones) {
        this.nComparaciones = nComparaciones;
    }
    public int getnIntercambios() {
        return nIntercambios;
    }
    public int getnComparaciones() {
        return nComparaciones;
    }    
    public void settEjecucion(long tEjecucion) {
        this.tEjecucion = tEjecucion;
    }
    public long gettEjecucion() {
        return tEjecucion;
    }
    public Integer[] burbuja(Integer[] X){                
        int aux=0;                
        int tintercambios=0,tcomparaciones=0;
        long tiempoIni=System.nanoTime();
        for(int i=0;i<X.length - 1; i++){            
            for(int j=0;j<X.length - 1; j++){
                tcomparaciones++;
                if(X[j]>X[j+1]){
                    aux=X[j];
                    X[j]=X[j+1];
                    X[j+1]=aux;
                    tintercambios++;
                }}}
        long tiempoFin=System.nanoTime();
        long tiempoEjecucion=tiempoFin-tiempoIni;
        this.setnIntercambios(tintercambios);
        this.setnComparaciones(tcomparaciones);
        this.settEjecucion(tiempoEjecucion);
        System.out.println("Tiempo de Ejecucion(ns):"+ tiempoEjecucion);        
        return X;
    }
    
    public Integer[] insercion(Integer[] X){        
        int aux,k;
        boolean sw=false;
        int tintercambios=0,tcomparaciones=0;
        long tiempoIni=System.nanoTime();
        for(int i=1; i<X.length;i++)
        {   aux=X[i];
            k=i-1;
            sw=false;
            while(sw==false && k>=0){
                tcomparaciones++;
                if(aux<X[k])
                { X[k+1]=X[k];
                  k--;}
                else
                    sw=true;
            }//end while
            tintercambios++;
            X[k+1]=aux;
        }//end for
        
        long tiempoFin=System.nanoTime();
        long tiempoEjecucion=tiempoFin-tiempoIni;
        this.setnIntercambios(tintercambios);
        this.setnComparaciones(tcomparaciones);
        this.settEjecucion(tiempoEjecucion);
        System.out.println("Tiempo de Ejecucion(ns):"+ tiempoEjecucion);        
        return X;
    }
    public Integer[] insercionBinaria(Integer[] X){
        int aux,p,u,c;
        int tintercambios=0,tcomparaciones=0;
        long tiempoIni=System.nanoTime();
        for(int i=1;i<X.length;i++)
        {   aux=X[i];
            p=0;
            u=i-1;
            while(p<=u){
                c=(p+u)/2;
                tcomparaciones++;
                if(aux<X[c])
                    u=c-1;
                else p=c+1;}//end while
            for(int k=i-1;k>=p;k--)                
                X[k+1]=X[k];
            tintercambios++;
            X[p]=aux;
        }//end for
        long tiempoFin=System.nanoTime();
        long tiempoEjecucion=tiempoFin-tiempoIni;
        this.setnIntercambios(tintercambios);
        this.setnComparaciones(tcomparaciones);
        this.settEjecucion(tiempoEjecucion);
        System.out.println("Tiempo de Ejecucion(ns):"+ tiempoEjecucion);        
        return X;
    }
    public Integer[] seleccion4c(Integer[] X){
        int aux,k;
        int tintercambios=0,tcomparaciones=0;
        long tiempoIni=System.nanoTime();
        for(int i=0; i<X.length-1;i++){
            aux=X[i];
            k=i;
            for(int j=i+1;j<X.length;j++){
                tcomparaciones++;
                if(X[j]<aux){
                    aux=X[j];
                    k=j;}                    
            }
            tintercambios++;
            X[k]=X[i];
            X[i]=aux;
        }        
        long tiempoFin=System.nanoTime();
        long tiempoEjecucion=tiempoFin-tiempoIni;
        this.setnIntercambios(tintercambios);
        this.setnComparaciones(tcomparaciones);
        this.settEjecucion(tiempoEjecucion);
        System.out.println("Tiempo de Ejecucion(ns):"+ tiempoEjecucion);        
        return X;
    }
    //Metodo Shell
    public Integer[] ShellSort(Integer X[]){
        int salto,N,j,k;
        N=X.length;
        salto=N/2;
        while(salto>0){
            for(int i=salto;i<N;i++)
            {
                j=i-salto;
                while(j>=0){
                    k=j+salto;
                    if(X[j]<=X[k])
                        j=0;
                    else
                        intercambio(X,j,k);
                    j=j-salto;
                }//end while                
            }//end for
            salto=salto/2;
        }//end while        
        return X;
    }
    
    private void intercambio(Integer X[],int p,int q){
        int temp=X[p];
        X[p]=X[q];
        X[q]=temp;       
    }
    
    //Metodo QuickSort    
    public Integer[] CallQuickSort(Integer[] X)
    {   Integer[] Y;
        long tiempoIni=System.nanoTime();
        Y=QuickSort(X, 0, X.length - 1);
        long tiempoFin=System.nanoTime();
        long tiempoEjecucion=tiempoFin-tiempoIni;        
        this.settEjecucion(tiempoEjecucion);
        System.out.println("Tiempo de Ejecucion(ns):"+ tiempoEjecucion);  
        return Y;
    }
    public Integer[] QuickSort(Integer[] X, int start, int end)
    {
        if(start<end){
            int pIndex=QuickSortPartition(X,start,end);
            QuickSort(X,start,pIndex-1);
            QuickSort(X,pIndex+1,end);
        }
        return X;
    }
    private int QuickSortPartition(Integer[] X, int start, int end){
        int pivot=X[end];
        int pIndex=start;
        for(int i=start; i<end; i++){
            if(X[i]<=pivot){
                intercambio(X,i,pIndex);
                pIndex++;
            }//end if
        }//end for
        intercambio(X,pIndex,end);
        return pIndex;
    }
    //Metodo MergeSort 
    public Integer[] CallMergeSort(Integer[] X){
        long tiempoIni=System.nanoTime();
        MergeSort(X);
        long tiempoFin=System.nanoTime();
        long tiempoEjecucion=tiempoFin-tiempoIni;        
        this.settEjecucion(tiempoEjecucion);
        System.out.println("Tiempo de Ejecucion(ns):"+ tiempoEjecucion);           
        return X;
    }
            
    private Integer[] MergeSort(Integer[] X)
    {
        int n=X.length;
        if(n < 2) return X;
        int mid = n/2;
        Integer[] left=new Integer[mid];
        Integer[] right=new Integer[n - mid];
        for(int i=0;i<mid;i++)
            left[i] = X[i];
        for(int i=mid;i<n;i++)
            right[i - mid] = X[i];        
        MergeSort(left);
        MergeSort(right);
        Merge(X,left,right );
        return X;
    }
    private void Merge(Integer[] X,Integer[] left,Integer[] right){
        int nL=left.length;
        int nR=right.length;
        int i=0,j=0,k=0;
        while(i<nL && j<nR){
            if(left[i] <= right[j]){
                X[k]=left[i];
                i++;
            }else{
                X[k]=right[j];
                j++;                
            }
            k++;                
        }
        while(i<nL){
            X[k]=left[i];
            i++;
            k++;
        }
        while(j<nR){
            X[k]=right[j];
            j++;
            k++;
        }
        
    }
    //Metodo HeapSort 
    public Integer[] HeapSort(Integer[] X){
        Integer[] Y=new Integer[X.length];
        HeapSortConstruct(X);//fase de construccion: contruir el heap
        Y=HeapSortExtract(X);//fase de extraccion los valores del nodo raiz        
        return Y;
    }
    private void HeapSortConstruct(Integer[] X){
        int current = 0, maxChildIndex;
        boolean hecho;
        for(int i=(X.length-2)/2;i>=0;i--)
        {
            current=i;
            hecho = false;
            while(!hecho){//2*i+1,2*i+2
                if(2*current+1 > X.length-1)
                    //nodo actual no tiene hijos
                    hecho=true;
                else//el nodo actual tiene al menos un hijo                    
                {
                    maxChildIndex=HeapSortMaxChild(X,current,X.length-1);
                    if(X[current]<X[maxChildIndex])//nodo padre con el maximo valor de los nodos hijo
                    {
                        intercambio(X,current,maxChildIndex);
                        current=maxChildIndex;
                    }else
                        hecho=true;
                }
            }//end while   
        }//end for
    }
    private int HeapSortMaxChild(Integer[] X, int loc,int end){
        int result,Izq,Der;        
        Izq=2*loc+1;
        Der=2*loc+2;
        if(Der<=end && X[Izq]<X[Der])
            result=Der;
        else
            result=Izq;
        return result;
    }
    private Integer[] HeapSortExtract(Integer[] X){
        Integer[] Y=new Integer[X.length];//sortList
        int current,maxChildIndex;
        boolean hecho;
        for(int i=X.length-1;i>=0;i--){
            Y[i]=X[0];//colocando el nodo raiz en la posicion actual del nuevo heap
            X[0]=X[i];//reemplazamo el valor del nodo raiz con el valor del ultimo nodo del heap
            current=0;
            hecho=false;
            while(!hecho){
                if(2*current+1 > i)
                    hecho=true;
                else{//si el nodo actual tiene al menos un hijo                    
                    maxChildIndex=HeapSortMaxChild(X,current,i);
                    if(X[current]<X[maxChildIndex])
                    {intercambio(X,current,maxChildIndex);
                        current=maxChildIndex;
                    }else//la restrccion de la relacion de valor se cumple, por tanto terminar
                        hecho=true;
                }
            }
        }
        return Y;
    }
    
}

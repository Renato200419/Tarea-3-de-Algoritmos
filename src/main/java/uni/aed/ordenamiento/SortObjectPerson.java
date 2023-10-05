package uni.aed.ordenamiento;

import uni.aed.model.Persona;

public class SortObjectPerson {
    public Persona[] Burbuja(Persona[] X,int attribute){
        if(!(attribute==Persona.NAME || attribute==Persona.AGE))
            throw new IllegalArgumentException();
        Persona p1,p2,aux;
        int comparisonResult;
        X[0].setCompareAttribute(attribute);
        for(int i=0;i<X.length - 1;i++){
            for(int j=0;j<X.length - 1;j++){
                p1=X[j];
                p2=X[j+1];
                comparisonResult=p1.compareTo(p2);
                if(comparisonResult>0){
                    aux=X[j];
                    X[j]=X[j+1];
                    X[j+1]=aux;                    
                }//end if
            }//end for
        }//end for
        return X;
    }//end burbuja
    
}//end clase

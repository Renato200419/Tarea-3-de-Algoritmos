package uni.aed.directorio;

import uni.aed.model.Persona;

public interface Directorio {
    public void add(Persona newPersona);
    public Persona[] sort(int attribute,String algoritmo);        
}

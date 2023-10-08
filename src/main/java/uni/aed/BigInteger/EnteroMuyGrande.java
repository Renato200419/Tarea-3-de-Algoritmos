package uni.aed.BigInteger;

public class EnteroMuyGrande {
    //Paso-->1
    private static final char MENOS = '-';
    private Nodo cabeza;
    private byte signo;

    public EnteroMuyGrande() {
        this("0");
    }

    public EnteroMuyGrande(long numero) {
        this("" + numero);
    }
    //Metodo para el ejercicio 16.13
    private boolean esFormatoValido(String numero){
        return numero.matches("-?\\d+"); //Verifica si la cadena coincide empezando por numero negativo,esta seguido de numeros(0-9) o no contiene ningún otro carácter
    }
    
    public EnteroMuyGrande(String numero) {
        //El if se agrega para responder el ejercicio 16.13
        if (!esFormatoValido(numero)) throw new IllegalArgumentException("El formato del parámetro EnteroMuyGrande es inválido");
        // no comprobación de error; suponga que el parámetro tiene un formato válido
        numero.trim();
        signo = +1;
        if (numero.charAt(0) == MENOS) {
            signo = -1;
            numero = numero.substring(1); // remueve el primer carácter
        }
        numero = extraeCerosPrecedentes(numero);

        if (numero.equals("0")) {
            signo = +1; // el patrón de entrada \\-0+ o \\+0+ se convierte a
                        // +0 internamente    
        }
        cabeza=new Nodo();
        Nodo cola = cabeza; 
        String digitos;
        while (!numero.equals("")) {
            int loc = Math.max(numero.length() - Nodo.DIGITOS_MAX, 0);

            digitos = numero.substring(loc); // corta los últimos 3 dígitos
                                             // corta todo si < 3 dígitos

            numero = numero.substring(0,loc);
            // si loc == 0, el número se convierte en ""

            Nodo bloque = new Nodo(digitos);

            cola.siguiente = bloque;
            cola = bloque;
        }
        cabeza = cabeza.siguiente; // remueve el nodo ficticio
    }
    public String aString() {
        StringBuffer strBuf = new StringBuffer("");
        String formato = "%0" + Nodo.DIGITOS_MAX + "d";
        Nodo p = cabeza;
        while (p.siguiente != null) {
            strBuf.insert(0, String.format(formato, p.valor));
            // rellena los 0 precedentes si los dígitos están
            // en medio del número
            p = p.siguiente;
        }
            strBuf.insert(0, p.valor); // procesa el nodo más significativo
                                       // no rellena los 0 precedentes para este nodo
            if(signo<0){
                strBuf.insert(0,"-");
            }
            return strBuf.toString();
    }
    
    //(Pregunta 16.14)Este método toma el separador que le das y lo coloca cada tres dígitos. Es importante notar que esto se hace contando 
    //desde los dígitos menos significativos, es decir, desde el final del número hacia el principio.
    public String aString(String separador) {
        if (cabeza == null) {
            return "0";
        }

        StringBuffer strBuf = new StringBuffer();
        String formato = "%0" + Nodo.DIGITOS_MAX + "d";
        Nodo p = cabeza;
        int contador = 0;  // para llevar la cuenta de los dígitos

        while (p.siguiente != null) {
            contador += String.valueOf(p.valor).length();
            if (contador >= 3) {  // cada tres dígitos inserta el separador
                strBuf.insert(0, separador + String.format(formato, p.valor));
                contador = 0;
            } else {
                strBuf.insert(0, String.format(formato, p.valor));
            }
            p = p.siguiente;
        }

        // Para el nodo más significativo
        strBuf.insert(0, p.valor); // No rellena los ceros precedentes para este nodo

        if (signo < 0) {
            strBuf.insert(0, "-");
        }

        return strBuf.toString();
    }

    //Pregunta 16.15
    public void incr() {
        if (cabeza == null) {
            // Si el entero actual es 0, simplemente lo cambiamos a 1.
            cabeza = new Nodo((short) 1);
            return;
        }

        Nodo actual = cabeza;  // Nodo actual que estamos procesando
        short acarreo = 1;        // Empezamos con un acarreo de 1 porque estamos incrementando

        while (actual != null && acarreo > 0) {  
            int result = actual.valor + acarreo;  // Suma el valor en el nodo actual con el acarreo
            actual.valor = (short) (result % Nodo.VALOR_MAX);  // Actualiza el valor en el nodo actual
            acarreo = (short) (result / Nodo.VALOR_MAX);         // Calcula el nuevo acarreo

            if (actual.siguiente == null && acarreo > 0) {
                // Si no hay un nodo siguiente y todavía hay un acarreo,
                // entonces crea un nuevo nodo
                actual.siguiente = new Nodo(acarreo);
                acarreo = 0;  // Resetea el acarreo ya que lo hemos manejado
            }

            actual = actual.siguiente;  // Avanza al siguiente nodo
        }
    }
    //Ejercicio 16.16
    public void decr() {
        if (cabeza == null) {
            cabeza = new Nodo((short) -1);
            return;
        }

        Nodo current = cabeza; // Nodo actual que estamos procesando
        short borrow = 1; // Empezamos con un préstamo de 1 porque estamos decrementando

        while (current != null && borrow > 0) {
            int result = current.valor - borrow;

            if (result < 0) {
                current.valor = (short) (result + Nodo.VALOR_MAX);
                borrow = 1;
            } else {
                current.valor = (short) result;
                borrow = 0;
            }

            if (current.siguiente == null && borrow > 0) {
                current.siguiente = new Nodo((short) -1);
                borrow = 0;
            }

            current = current.siguiente; // Avanza al siguiente nodo
        }

        // Elimina nodos que sean 0 al final, si es necesario para garantizar que no tengamos representaciones como "00023" o similares
        current = cabeza;
        Nodo prev = null;
        while (current != null && current.siguiente != null) {
            if (current.siguiente.valor == 0) {
                current.siguiente = current.siguiente.siguiente;
            } else {
                prev = current;
                current = current.siguiente;
            }
        }

        // Si al final del proceso, el valor más significativo es 0, se debe eliminar ese nodo.
        if (cabeza.valor == 0 && cabeza.siguiente != null) {
            cabeza = cabeza.siguiente;
        }
    }
  
        
    
    
    
    private static String extraeCerosPrecedentes(String str){
        StringBuffer strBuf = new StringBuffer(str);
        int length = strBuf.length();
        for(int i=0;i<length;i++){
            if(strBuf.charAt(0) == '0'){
                strBuf.deleteCharAt(0);
            }
        }
        if(strBuf.length() == 0){
            strBuf.append('0');
        }
        return strBuf.toString();
    }
    //---------------------------------
    //Clase interna: Nodo
    //---------------------------------
    class Nodo{
        /**Número de dígitos a almacenar en un bloque */
        private static final short DIGITOS_MAX=3;
        private short valor; //varía de 0 a RANGO_VALOR = 1
        private Nodo siguiente;
        private Nodo(){
            this("0");
        }
        private Nodo(String str){
            this(Short.parseShort(str));
        }
        private Nodo(short val){
            valor=val;
            siguiente=null;
        }
        //Esto se añadió en el paso 2:
        /** Intervalo de valores almacenados en un bloque  */
        private static final short VALOR_MAX=1000;        
    }    

    //Paso->2
        
    private EnteroMuyGrande (Nodo cabeza){
        this.cabeza=cabeza;
        this.signo=+1;
    }
    /*public EnteroMuyGrande suma(EnteroMuyGrande num){
        return this.sumaPos(num); //TEMP -- suma sólo dos valores positivos
    }*/
    private EnteroMuyGrande sumaPos(EnteroMuyGrande num) {
        Nodo p, q, r, t;

        p = this.cabeza;
        q = num.cabeza;

        t = new Nodo(); // nodo cabeza ficticio
        r = t;

        short acarreo = 0;

        while (p != null && q != null) {
            short sum = (short) (acarreo + p.valor + q.valor);

            r.siguiente = new Nodo();
            r = r.siguiente;

            r.valor = (short) (sum % Nodo.VALOR_MAX);
            acarreo = (short) (sum / Nodo.VALOR_MAX);

            p = p.siguiente;
            q = q.siguiente;
        }

        p = (p == null) ? q : p; // restablece p para apuntar a los bloques restantes

        while (p != null) {
            r.siguiente = new Nodo();
            r = r.siguiente;

            r.valor = (short) ((p.valor + acarreo) % Nodo.VALOR_MAX);
            acarreo = (short) ((p.valor + acarreo) / Nodo.VALOR_MAX);

            p = p.siguiente;
        }

        if (acarreo > 0) { // desbordamiento, acarreo final
            r.siguiente = new Nodo((short) acarreo);
        }

        return new EnteroMuyGrande(t.siguiente); // quita el nodo cabeza ficticio
    }
        //Paso->3
    public int comparaA(EnteroMuyGrande num){
        EnteroMuyGrande L = this;
        EnteroMuyGrande R = num;
        if(L.esPositivo() && R.esNegativo()){
            return +1;
        }
        if(L.esNegativo() && R.esNegativo()){
            return -1;
        }
        // L y R tienen el mismo signo, así que se les compara.
        // Se usará un truco, al convertir L y R
        // de vuelta a String y usar String comparaA
        String Lstr = L.toString();
        String Rstr = R.toString();

        int result;
        int lengthL = Lstr.length();
        int lengthR = Rstr.length();

        // primero verifica la magnitud
        if (lengthL == lengthR) {
            result = Lstr.compareTo(Rstr);
        } else {
            result = (lengthL < lengthR) ? -1 : +1;
        }

        // ahora comprueba el signo de dos muy grandes
        return L.signo * result; // Nota: Puesto que el String comparaA
        // podría regresar valores distintos a +1, 0 o -1,
        // así que hace este método como consecuencia
    }
    /*public EnteroMuyGrande resta(EnteroMuyGrande num) {
        return this.agregaPos(num);
        // TEMP - resta de dos positivos muy grandes
    }*/
    private EnteroMuyGrande extraeCerosPrecedentes() {
        String numStr = this.toString();
        String result = extraeCerosPrecedentes(numStr);

        if (result.equals("0")) {
            return new EnteroMuyGrande(0);
        } else if (result.length() < numStr.length()) {
            return new EnteroMuyGrande(result);
        } else {
            return this;
        }
    }
    private boolean esPositivo() {
        return signo > 0;
    }

    private boolean esNegativo() {
        return signo < 0;
    }

    private EnteroMuyGrande negativo() {
        signo = (byte) -signo; // el signo es int así que se necesita conversión
        return this;
    }

    private EnteroMuyGrande restaPos(EnteroMuyGrande num) {
        Nodo p, q, r, t;
        boolean esNegativo = false;

        // siempre resta el más pequeño del mayor.
        // así num es mayor, entonces el resultado es negativo
        if (this.comparaA(num) >= 0) { // this - num
            p = this.cabeza;
            q = num.cabeza;
        } else {  // -(num - this)
            p = num.cabeza;
            q = this.cabeza;
            esNegativo = true;
        }

        t = new Nodo(); // nodo cabeza ficticio
        r = t;

        short prestamo = 0, minuendo; // para L es un minuendo

        while (p != null && q != null) {
            r.siguiente = new Nodo();
            r = r.siguiente;

            minuendo = (short) (p.valor - prestamo);

            if (minuendo < q.valor) { // necesita prestar
                r.valor = (short) (Nodo.VALOR_MAX + minuendo - q.valor);
                prestamo = 1;
            } else { //no prestamo
                r.valor = (short) (minuendo - q.valor);
                prestamo = 0;
            }

            p = p.siguiente;
            q = q.siguiente;
        }
        p = (p==null) ? q: p;
        while(p!=null){
            r.siguiente = new Nodo();
            r = r.siguiente;
            r.valor = (short) (p.valor - prestamo);
            if(r.valor <0){
                r.valor += Nodo.VALOR_MAX;
                prestamo=1;
            }else{
                prestamo = 0;
            }
            p = p.siguiente;
        }
        EnteroMuyGrande result = new EnteroMuyGrande(t.siguiente); //remueve el nodo cabeza ficticio
        result = result.extraeCerosPrecedentes();
        if(esNegativo) result.negativo();
        
        return result;
    }
    // Paso ->4
    public EnteroMuyGrande(EnteroMuyGrande num) { // Constructor
        this.signo = num.signo;
        this.cabeza = new Nodo(); // nodo cabeza ficticio

        Nodo p = cabeza;
        Nodo q = num.cabeza;

        while (q != null) {
            p.siguiente = new Nodo(q.valor);
            p = p.siguiente;
            q = q.siguiente;
        }

        this.cabeza = this.cabeza.siguiente; // remueve el nodo cabeza ficticio
    }

    public EnteroMuyGrande suma(EnteroMuyGrande num) {
        /* Necesitamos considerar cuatro casos, y para cada uno
         * de los cuatro casos, convertimos la operación
         * en términos de sumaPos y restaPos.
         *
         * Resolvemos los cuatro casos del modo siguiente
         * 
         *   A +  B  --->  A + B
         *   A + -B  --->  A - B
         *  -A +  B  --->  B - A
         *  -A + -B  ---> -(A + B)
         */

        EnteroMuyGrande L = new EnteroMuyGrande(this);
        EnteroMuyGrande R = new EnteroMuyGrande(num);

        if (L.esPositivo() && R.esPositivo()) {
            return L.sumaPos(R);
        }

        if (L.esPositivo() && R.esNegativo()) {
            return L.restaPos(R.negativo());
        }
        if (L.esNegativo() && R.esPositivo()) {
            return R.restaPos(L.negativo());
        }

        // ambos negativos
        return L.negativo().sumaPos(R.negativo()).negativo();
    }

    public EnteroMuyGrande resta(EnteroMuyGrande num) {
        /* Necesitamos considerar cuatro casos, y para
         * cada uno de los cuatro casos, convertimos 
         * la operación en términos de addAbs y subAbs.
         *
         * Resolvemos los cuatro casos del modo siguiente:
         *
         *   A -  B  --->   A - B
         *   A - -B  --->   A + B
         *  -A -  B  ---> -(A + B)
         *  -A - -B  --->   B - A
         */

        EnteroMuyGrande L = new EnteroMuyGrande(this);
        EnteroMuyGrande R = new EnteroMuyGrande(num);

        if (L.esPositivo() && R.esPositivo()) {
            return L.restaPos(R);
        }

        if (L.esPositivo() && R.esNegativo()) {
            return L.sumaPos(R.negativo());
        }

        if (L.esNegativo() && R.esPositivo()) {
            return L.negativo().sumaPos(R).negativo();
        }

        // ambos negativos
        return R.negativo().restaPos(L.negativo());
    }
    
    //Ejercicio 16.17
    public EnteroMuyGrande valorAbsoluto() {
        EnteroMuyGrande resultado = new EnteroMuyGrande(this); // Copia el número actual
        resultado.signo = +1; // Cambia el signo a positivo
        return resultado;
    }
    //continuacion del ejercicio 16.17
    public EnteroMuyGrande multiplicacionSimplista(EnteroMuyGrande R) {
        EnteroMuyGrande L =this;
        // Comprobamos que el valor absoluto de L es mayor que o igual al valor absoluto de R
        if (L.comparaA(R) < 0) {
            EnteroMuyGrande temp = L;
            L = R;
            R = temp;
        }

        EnteroMuyGrande producto = new EnteroMuyGrande(); // inicialmente 0       
        EnteroMuyGrande term = L.valorAbsoluto();
        EnteroMuyGrande limit = R.valorAbsoluto();
        EnteroMuyGrande contador = new EnteroMuyGrande();
        EnteroMuyGrande uno = new EnteroMuyGrande(1);

        while (contador.comparaA(limit) < 0) {
            producto = producto.suma(term);
            contador.incr();
        }

        // Si L y R tienen signos diferentes y el producto no es 0, el producto es negativo
        if (L.signo != R.signo && !producto.aString().equals("0")) {
            producto.signo = -1;
        }

        return producto;
    }
    
    //Ejercicio 16.18
    public EnteroMuyGrande multiplicacionLarga(EnteroMuyGrande otro) {
        EnteroMuyGrande resultado = new EnteroMuyGrande("0");
        EnteroMuyGrande multiplicador = this.valorAbsoluto(); 
        EnteroMuyGrande multiplicando = otro.valorAbsoluto();

        // Convertimos a string para poder operar cada dígito individualmente
        String multiplicadorStr = multiplicador.aString();
        String multiplicandoStr = multiplicando.aString();

        int desplazamiento = 0;
        for (int i = multiplicandoStr.length() - 1; i >= 0; i--) {
            char c1 = multiplicandoStr.charAt(i);
            EnteroMuyGrande sumaParcial = new EnteroMuyGrande("0");

            int acarreo = 0;
            for (int j = multiplicadorStr.length() - 1; j >= 0; j--) {
                char c2 = multiplicadorStr.charAt(j);

                int prod = (c1 - '0') * (c2 - '0') + acarreo;
                acarreo = prod / 10;
                int digito = prod % 10;

                sumaParcial = new EnteroMuyGrande(String.valueOf(digito)).sumaPos(multiplicacionSimplista(new EnteroMuyGrande("10")));
            }

            if (acarreo > 0) {
                sumaParcial = sumaParcial.sumaPos(new EnteroMuyGrande(String.valueOf(acarreo)));
            }


            for (int k = 0; k < desplazamiento; k++) {
                sumaParcial = sumaParcial.multiplicacionSimplista(new EnteroMuyGrande("10"));
            }


            resultado = resultado.sumaPos(sumaParcial);

            desplazamiento++;
        }

        // Aplicamos el signo adecuado al producto resultante.
        if (this.signo != otro.signo && !resultado.aString().equals("0")) {
            resultado.signo = -1;
        }

        return resultado;
    }
    // Ejercicio 16.19
    public EnteroMuyGrande divisionSimplista(EnteroMuyGrande divisor) {
        try {
            if (divisor.aString().equals("0")) {
                throw new ArithmeticException("División por cero.");
            }

            EnteroMuyGrande cociente = new EnteroMuyGrande("0");
            EnteroMuyGrande residuo = this.valorAbsoluto();
            EnteroMuyGrande divisorAbs = divisor.valorAbsoluto();

            while (residuo.comparaA(divisorAbs) >= 0) {
                residuo = residuo.resta(divisorAbs);
                cociente.incr();
            }

            // Establecemos el signo del cociente
            if (this.signo != divisor.signo && !cociente.aString().equals("0")) {
                cociente.signo = -1;
            }

            return cociente;

        } catch (ArithmeticException e) {
            System.out.println("Excepción: " + e.getMessage());
            return null;  
        }
    }
    
    //Ejercicio 16.12
    public void invertir() {
        Nodo prev = null;
        Nodo current = cabeza;
        Nodo next = null;

        while (current != null) {
            next = current.siguiente;  // Guarda el siguiente nodo
            current.siguiente = prev;  // Cambia la referencia del siguiente nodo al nodo previo (invierte la liga)
            prev = current;            // Mueve el nodo previo al nodo actual
            current = next;            // Avanza al siguiente nodo
        }

        cabeza = prev;  // Una vez que hemos terminado, el nodo previo se convierte en la nueva cabeza
    }
    
    //Ejercicio 16.20
    public EnteroMuyGrande divisionLarga(EnteroMuyGrande divisor) {
        if (divisor.aString().equals("0")) {
            throw new ArithmeticException("División por cero.");
        }

        EnteroMuyGrande dividendo = new EnteroMuyGrande(this.aString());
        EnteroMuyGrande cociente = new EnteroMuyGrande("0");
        EnteroMuyGrande residuo = new EnteroMuyGrande("0");
        EnteroMuyGrande diez = new EnteroMuyGrande("10");

        for (int i = 0; i < dividendo.aString().length(); i++) {
            residuo = multiplicacionSimplista(diez);
            String siguienteDigito = String.valueOf(dividendo.aString().charAt(i));
            residuo = residuo.suma(new EnteroMuyGrande(siguienteDigito));

            EnteroMuyGrande cocienteParcial = new EnteroMuyGrande("0");
            while (residuo.comparaA(divisor) >= 0) {
                residuo = residuo.resta(divisor);
                cocienteParcial.incr();
            }
            cociente = multiplicacionSimplista(diez).suma(cocienteParcial);
        }

        if (this.signo != divisor.signo && !cociente.aString().equals("0")) {
            cociente.signo = -1;
        }

        return cociente;
    }


}
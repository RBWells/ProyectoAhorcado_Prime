public class Pila {
    private char[] datos;
    private int cima;

    //Crear pila
    public Pila(int tam){
        datos=new char[tam];
        cima=-1;
    }

    //2. Insertar
    public void insertar(char dato){
        if (!pilaLlena()) {
            cima++;
            datos[cima]=dato;
        }else{
            System.out.println("La pila esta llena...");
        }
    }
    //3. Eliminar
    public void eliminar(){
        if (!pilaVacia()) {
            datos[cima]='\0';
            cima--;
        }
    }
    //4.Mostrar cima
    public char mostrarCima(){
        if (!pilaVacia()) {
            return datos[cima];
        }
        return '\0';
    }
    //5. Cantidad de elementos
    public int elementosExistentes(){
        return cima+1;
    }

    public boolean verificarLetraIngresadaNorepetitiva(char letra){
        for (int i = 0; i <= cima; i++) {
            if (datos[i]==letra) {
                return true;
            }
        }
        return false;
    }

    public char[] obtenerLetrasAlmaceadas_Prime(){
        char[] letras= new char[cima +1];

        for (int i = 0; i <= cima; i++) {
            letras[i]=datos[i];
        }
        return letras;
    }


    //Metodo auxiliar
    private boolean pilaLlena(){
        return cima==datos.length -1;
    }
    //Metodo auxiliar: Verificar vacia
    private boolean pilaVacia(){
        return cima==-1;
    }





}

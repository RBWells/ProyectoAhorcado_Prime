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
        if (cima <datos.length -1) {
            cima++;
            datos[cima]=dato;
        }else{
            System.out.println("La pila esta llena...");
        }
    }
    //3. Eliminar
    public void eliminar(){
        if (cima >=0) {
            datos[cima]='\0';
            cima--;
        }
    }
    //4.Mostrar cima
    public char mostrarCima(){
        if (cima >=0) {
            return datos[cima];
        }
        return '\0';
    }
    //5. Cantidad de elementos
    public int elementosExistentes(){
        return cima+1;
    }
    //6. Vaciar Pila
    public void vaciar(){
        if (cima > -1) {
            cima=-1;
        }else{
            System.out.println("La pila esta vacia, no puedes eliminar lo que no ha existido");
        }
    }

    public boolean verificarLetraIngresadaNorepetitiva(char letra){
        for (int i = 0; i <= cima; i++) {
            if (datos[i]==letra) {
                return true;
            }
        }
        return false;
    } 
    public char[] obtenerLetrasAlmacenadas() {
        char[] letras = new char[cima + 1];

        for (int i = 0; i <= cima; i++) {
            letras[i] = datos[i];
        }
        return letras;
    }
}

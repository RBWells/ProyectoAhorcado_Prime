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
            
        }
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

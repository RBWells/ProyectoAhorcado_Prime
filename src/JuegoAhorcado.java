import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class JuegoAhorcado {
    public static char[] alphabeto={ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ñ' };
    Scanner nrg=new Scanner(System.in);
    Random thegrefg=new Random();

    public String elegirPalabra(String[] palabras){
        return palabras[thegrefg.nextInt(palabras.length)];
    }






    



    public boolean seAdivinoLaPalabra(String palabraSecreta, char[] letrasIngresadas){
        for (int i = 0; i < palabraSecreta.length(); i++) {
            char c=Character.toLowerCase(palabraSecreta.charAt(i));

            boolean find=false;
            for (int j = 0; j < letrasIngresadas.length; j++) {
                if (c==letrasIngresadas[j]) {
                    find=true;
                    break;
                }
            }
            if (!find) {
                return false;
            }
        }
        return true;
    }











    public String obtenerPalabraAdivinada(String palabraAdivinada, char[] letrasIngresadas){
        String palabra="";

        for (int i = 0; i < palabraAdivinada.length(); i++) {
            char c=palabraAdivinada.charAt(i);

            boolean find=false;
            for (int j = 0; j < letrasIngresadas.length; j++) {
                if (Character.toLowerCase(c)==letrasIngresadas[j]) {
                    find=true;
                    break;
                }
            }
            if (find) {
                palabra+=c+" ";
            }else{
                palabra+="_ ";
            }
        }
        return palabra;
    }














    public String obtenerLetrasDisponibles(char[] letrasIngresadas){
        String letrasDis="";

        for (int i = 0; i < alphabeto.length; i++) {
            boolean find=false;

            for (int j = 0; j < letrasIngresadas.length; j++) {
                if (alphabeto[i] == letrasIngresadas[j]) {
                    find=true;
                    break;
                }
            }
            if (find) {
                letrasDis+="_ ";
            } else{
                letrasDis+=alphabeto[i]+" ";
            }
        }
        return letrasDis;
    }














    public void inicioAhorcado(String palabraElejida){
        String wordSecret=palabraElejida;

        if (wordSecret.startsWith("Es: ") || wordSecret.startsWith("En: ")) {
            wordSecret=wordSecret.substring(4);
        }
        wordSecret=wordSecret.toUpperCase();
        Oportunidades trys =new Oportunidades();
        Pila letrasIngresadas=new Pila(27);

        //Copia del alphabeto para no perder el original
        char[] alphabetoCopy= Arrays.copyOf(alphabeto, alphabeto.length);

        System.out.println("=== INICIO DEL JUEGO ===");
        System.out.println("La palabra contiene: "+wordSecret.length() +"Letras ");

        while (trys.getIntentos() > 0 && !seAdivinoLaPalabra(wordSecret, letrasIngresadas.obtenerLetrasAlmacenadas())) {
            System.out.println("Te quedan: "+trys.getIntentos()+ " Oportunidades para adivinar la palarbra");
            System.out.println("Letras Disponibles: "+obtenerLetrasDisponibles(letrasIngresadas.obtenerLetrasAlmacenadas()));
            System.out.println("Palabra: "+obtenerPalabraAdivinada(wordSecret, letrasIngresadas.obtenerLetrasAlmacenadas()));
            

            System.out.println("\nDime una letra del Alphabeto: ");
            String letraUsu=nrg.nextLine().trim().toLowerCase();

            //Validacion para que solo sea una letra
            if (letraUsu.length() !=1) {
                System.out.println("Ingresa SOLO UNA LETRAS");
                continue;
            }
            char letra=letraUsu.charAt(0);

            //Validacion para que sea del alphabeti
            if (binarySearch(alphabeto, letra)==-1) {
                System.out.println("Eso no es una letra, Ingresa una que si lo sea");
                continue;
            }

            //Buscar letra en las que quedan
            int pos=binarySearch(alphabetoCopy, letra);

            if (pos==-1) {
                System.out.println("Ya ingresaste esa letra, Prueva con una diferentes");
                continue;
            }
            


            //Elimina letra del alphabeto
            char[] newLetras=new char[alphabetoCopy.length -1];
            for (int i = 0, j=0; i < alphabetoCopy.length; i++) {
                if (i!=pos) {
                    newLetras[j]=alphabetoCopy[j];
                    j++;
                }
            }
            alphabetoCopy=newLetras;
            
            //Guardar letra en la pila
            letrasIngresadas.insertar(letra);

            


            //Comprobar la existencia de la palabra
            if (wordSecret.indexOf(Character.toUpperCase(letra)) != -1) {
                System.out.println("Exelente, esa letra si estaba en la palabra: "+obtenerPalabraAdivinada(wordSecret, letrasIngresadas.obtenerLetrasAlmacenadas()));
                trys.descontarIntentos();
            }else{
                System.out.println("Esa letra no estaba en la palabra secreta: "+obtenerPalabraAdivinada(wordSecret, letrasIngresadas.obtenerLetrasAlmacenadas()));
                trys.descontarIntentos();
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        }

        //Comprobar si gano o perdio
        if (seAdivinoLaPalabra(wordSecret, letrasIngresadas.obtenerLetrasAlmacenadas())) {
            System.out.println("\nGanaste, BRAVOOO!!");
            System.out.println("La palabra hera: "+wordSecret);
        } else{
            System.out.println("\n You loss, Te quedate sin Oportunidades....");
            System.out.println("La palabra secreta hera: "+wordSecret);
        }
    }




















    public int binarySearch(char[] arreglo, char letra){
        int inicio=0;
        int tfin=arreglo.length -1;
        
        while (inicio <= tfin) {
            int middle=(inicio+ tfin)/2;

            if (arreglo[middle]== letra) {
                return middle;
            }
            else if (arreglo[middle] > letra) {
                tfin=middle-1;
            }else{
                inicio=middle+1;
            }
        }
        return -1;
    }










}

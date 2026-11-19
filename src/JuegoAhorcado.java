import java.util.Scanner;
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
        return null;
    }














    public String obtenerLetrasDisponibles(){
        return null;
    }














    public void inicioAhorcado(String palabraElejida){
        
    }











}

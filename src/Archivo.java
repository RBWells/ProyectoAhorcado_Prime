import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Archivo {
    Scanner nrg=new Scanner(System.in);
    File archivo=new File("./Archivo/Words.txt");

    public static String spanish="Es";
    public static String inglish="En";

    public void menu() {
        String op = "";

        while (!op.equals("0")) {
            System.out.println("=== MENU DEL JUEGO ===");
            System.out.println("1. Verificar Archivo.");
            System.out.println("2. Llenar Archivo con palabras.");
            System.out.println("3. Borrar Archivo.");
            System.out.println("4. Jugar");
            System.out.println("---------------------------------");
            System.out.println("0. Salir...");
            op = nrg.next();

            switch (op) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":

                case "4":

                    break;
                case "0":
                    System.out.println("Saliste del menu...");
                    break;
                default:
                    System.out.println("Esa no es una opsion valida");
                    break;
            }
        }
    }














    public void verificarArchivo(){
        try {
            if (archivo.exists()) {
                int cantEs=0;
                int cantEn=0;
                BufferedReader br=new BufferedReader(new FileReader(archivo));
                String linea;

                while ((linea=br.readLine()) !=null) {
                    linea=linea.trim();
                    if (linea.startsWith(spanish +": ")) {
                        cantEs++;
                    } else if (linea.startsWith(inglish +": ")) {
                        cantEn++;
                    }
                }
                br.close();

                System.out.println("Verificacion exitosa...");
                System.out.println("Plabras en español: "+cantEs);
                System.out.println("Palabras en ingles: "+cantEn);
            }else{
                archivo.createNewFile();
                System.out.println("El archivo no existe...");
            }
        } catch (Exception e) {
            System.out.println("Error al verificar el archivo...");
        }
    }









    public void llenarArchivo(){
        
    }












    public void borrarArchivo(){
        if (archivo.exists()) {
            System.out.println("Seguro que quieres eliminar el archivo (Si/no)??");
            String res=nrg.next();

            if (res.equalsIgnoreCase("Si")) {
                PrintWriter pw=null;
                try {
                    pw=new PrintWriter(archivo);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (pw != null) {
                    pw.close();
                }
                System.out.println("Archivo Borrado");
            }else{
                System.out.println("Accion cancelada");
            }
        }else{
            System.out.println("No hay un archivo existente");
        }
    }














    public String[] cargarPalabras(){
        String[] palabras = new String[0];

        try {
            if (archivo.exists()) {
                int cont = 0;
                String linea;
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                while ((linea=br.readLine()) != null) {
                    linea=linea.trim();
                    if (linea.startsWith(spanish+": ")|| linea.startsWith(inglish+": ")) {
                        cont++;
                    }
                }
                br.close();

                palabras=new String[cont];
                int k=0;
                br=new BufferedReader(new FileReader(archivo));
                while ((linea=br.readLine()) != null) {
                    linea=linea.trim();
                    if (linea.startsWith(spanish+": ")|| linea.startsWith(inglish+": ")) {
                        palabras[k++]=linea;
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo....");
        }
        return palabras;
    }


    //Metodo/ Algoritmo de Ordenamiento-----------------
    public void shellSort(String[] words){
        int saltos= words.length/2;
        while (saltos > 0) {
            for (int i = saltos; i < words.length; i++) {
                String aux=words[i];
                int j=i;
                while (j>saltos && words[j-saltos].substring(4).compareTo(aux.substring(4)) >0) {
                    words[j]=words[j-saltos];
                    j-=saltos;
                }
                words[j]=aux;
            }
            saltos=saltos/2;
        }
    }
}

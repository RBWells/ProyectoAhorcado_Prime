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
                System.out.println("El archivo no existe");
            }
        } catch (Exception e) {
            System.out.println("Error al verificar el archivo...");
        }
    }
    public void llenarArchivo(){
          try {
            // Cantidad de palanras a ingresar
            int cantWords = 0;
            while (cantWords <= 0) {
                System.out.println("Cuantas palabras quieres ingresar??: ");
                try {
                    cantWords = nrg.nextInt();
                } catch (NumberFormatException e) {
                    cantWords = 0;
                }
                if (cantWords <= 0) {
                    System.out.println("Ingresa un numero mayor que 0");
                }
            }

            // Contar las palabras ya existentes
            int cont = 0;
            String linea;
            if (archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                while ((linea = br.readLine()) != null) {
                    linea = linea.trim();
                    if (linea.startsWith(spanish + ": ") || linea.startsWith(inglish + ": ")) {
                        cont++;
                    }
                }
                br.close();
            }

            // Vector para las palabras existentes y las nuevas
            String[] all = new String[cont + cantWords];
            int n = 0;
            if (archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                while ((linea = br.readLine()) != null) {
                    linea = linea.trim();
                    if (linea.startsWith(spanish + ": ") || linea.startsWith(inglish + ": ")) {
                        all[n++] = linea;
                    }
                }
                br.close();
            }

            // Palabra e idioma de la palanrsa
            for (int i = 1; i <= cantWords; i++) {
                String idioma = "";
                while (idioma.equals("")) {
                    System.out.println("Dime la palabra numero: " + i + " y en que idioma la quieres ingresar");
                    System.out.println("1. Español");
                    System.out.println("2. Ingles");
                    String op = nrg.nextLine().trim();

                    switch (op) {
                        case "1":
                            idioma = spanish;
                            break;
                        case "2":
                            idioma = inglish;
                            break;
                        default:
                            System.out.println("Opsion incorrecta...");
                            break;
                    }
                }
                String palabra = "";
                while (palabra.equals("")) {
                    if (idioma.equals(spanish)) {
                        System.out.println("Dime la palabra en español: ");
                    } else {
                        System.out.println("Dime la palabra en ingles: ");
                    }
                    String word = nrg.nextLine().trim().toUpperCase();
                    if (word.matches("[A-ZÑ]+")) {
                        palabra = word;
                    } else {
                        System.out.println("Solo ingresa letras de la (A-Z)");
                    }
                }
                all[n++] = idioma + ": " + palabra;
            }
            //
            // Ordenar con shell y guardar en archivo
            shellSort(all);
            PrintWriter pw = new PrintWriter(archivo);
            for (int i = 0; i < all.length; i++) {
                pw.println(all[i]);
            }
            pw.close();
            System.out.println("Palabras guardas en el archuvo: " + all.length);
        } catch (Exception e) {
            System.out.println("Error al llenar el archivo.....");
        }
    }

    public void borrarArchivo(){
        if (archivo.exists()) {
            System.out.println("Seguro que quieres eliminar el archivo??");
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
    public String[] cargarPalabras(String idioma){
        String prefinco = idioma + ": ";
        String[] palabras = new String[0];

        try {
            if (archivo.exists()) {
                int cont = 0;
                String linea;
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String lectLinea = (linea = br.readLine());
                while (lectLinea != null) {
                    if ((linea.trim().startsWith(prefinco))) {
                        cont++;
                    }
                }
                br.close();

                palabras=new String[cont];
                int k=0;
                br=new BufferedReader(new FileReader(archivo));
                while ((linea=br.readLine()) != null) {
                    linea=linea.trim();
                    if (linea.startsWith(prefinco)) {
                        palabras[k++]=linea.substring(prefinco.length());
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo....");
        }
        return palabras;
    }


    //Metodo AUXILIAR -----------------
    public void shellSort(String[] vector){
        int saltos= vector.length/2;
        while (saltos > 0) {
            for (int i = saltos; i < vector.length; i++) {
                String aux=vector[i];
                int j=i;
                while (j>saltos && vector[j-saltos].compareTo(aux) >0) {
                    vector[j]=vector[j-saltos];
                    j-=saltos;
                }
                vector[j]=aux;
            }
            saltos=saltos/2;
        }
    }
}

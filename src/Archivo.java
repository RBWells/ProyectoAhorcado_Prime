import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Archivo {
    Scanner nrg = new Scanner(System.in);
    File archivo = new File("./Proyect_Prime/Archivo/Words.txt");

    public static String spanish = "Es";
    public static String inglish = "En";

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
                    verificarArchivo();
                    break;
                case "2":
                    llenarArchivo();
                    break;
                case "3":
                    borrarArchivo();
                case "4":
                    JuegoAhorcado game=new JuegoAhorcado();
                    String[] palabras=cargarPalabras();

                    //Verificacion de archivo vacio
                    if (palabras.length ==0) {
                        System.out.println("El arahcivo esta vacio.. Llenalo primero");
                        break;
                    }

                    System.out.println("Cargando lista de palabras desde Archivo.... ");
                    System.out.println("+ "+palabras.length +" Palabras Cargadas");
                    String palabra=game.elegirPalabra(palabras);
                    game.inicioAhorcado(palabra);
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


















    public void verificarArchivo() {
        try {
            if (archivo.exists()) {
                int cantEs = 0;
                int cantEn = 0;
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;

                while ((linea = br.readLine()) != null) {
                    linea = linea.trim();
                    if (linea.startsWith(spanish + ": ")) {
                        cantEs++;
                    } else if (linea.startsWith(inglish + ": ")) {
                        cantEn++;
                    }
                }
                br.close();

                System.out.println("Verificacion exitosa...");
                System.out.println("Plabras en español: " + cantEs);
                System.out.println("Palabras en ingles: " + cantEn);
            } else {
                archivo.createNewFile();
                System.out.println("El archivo no existe...");
            }
        } catch (Exception e) {
            System.out.println("Error al verificar el archivo...");
        }
    }


















    public void llenarArchivo() {
        try {
            int cantWords = 0;

            while (cantWords <= 0) {
                System.out.println("Cuantas palabras quieres ingresar al archivo??");
                try {
                    cantWords = nrg.nextInt();
                } catch (NumberFormatException e) {
                    System.out.println("Ingresa un numero valido ");
                    cantWords = 0;
                }
                if (cantWords <= 0) {
                    System.out.println("Ingresa un numero mayor que 0");
                }
            }

            // contar palabras existentes en el archvio
            int contWords = 0;
            String linea;
            if (archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));

                linea = br.readLine();
                while (linea != null) {
                    linea = linea.trim();

                    if (linea.startsWith(spanish + ": ") || linea.startsWith(inglish + ": ")) {
                        contWords++;
                    }
                }
                br.close();
            }

            // Nuevo arreglo para las palabras yta existentes
            String[] wordsAll = new String[contWords + cantWords];
            int n = 0;

            if (archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                linea = br.readLine();

                while (linea!=null) {
                    if (linea.startsWith(spanish + ": ") || linea.startsWith(inglish + ": ")) {
                        wordsAll[n++] = linea;
                    }
                }
                br.close();
            }


            

            //Lectura de la palabra e idioma
            for (int i = 1; i <= cantWords; i++) {
                boolean save=false;

                while (!save) {
                    System.out.println("Dime la palabra numero "+i+ " Y en que idioma la quieres ingresar: ");
                    System.out.println("1. Español");
                    System.out.println("2. Ingles");
                    String ops=nrg.next();

                    switch (ops) {
                        case "1":
                            String wordEs="";

                            while (wordEs.equals("")) {
                                System.out.println("Dime la plabra en español");
                                String word=nrg.nextLine().trim().toUpperCase();

                                if (word.matches("[A-ZÑ]+")) {
                                    wordEs=word;
                                }else{
                                    System.out.println("Ingresa solo letras de la (A-Z)");
                                }
                            }

                            wordsAll[n++]=spanish+": "+wordEs;  //Palabra Guardada con el prefijo Es: 
                            save=true;
                            break;
                        case "2":
                            String wordEn="";

                            while (wordEn.equals("")) {
                                System.out.println("Dime la palabra en ingles: ");
                                String word=nrg.nextLine().trim().toUpperCase();

                                if (word.matches("[A-Z]+")) {
                                    wordEn=word;
                                }else{
                                    System.out.println("Solo ingresa letrs de la (A-Z)");
                                }
                            }
                            wordsAll[n++]=inglish+": "+wordEn; //Palabra guardada con su prefij En: 
                            save=true;
                            break;
                        default:
                            System.out.println("Esa opcion no esta en el menu");
                            break;
                    }
                }
            }


            // ordenar y Guardar en el archivo 
            shellSort(wordsAll);
            PrintWriter pw=new PrintWriter(archivo);
            for (int j = 0; j < wordsAll.length; j++) {
                pw.println(wordsAll[j]);
            }
            pw.close();
            System.out.println("Plabaras Guardadas en el archivo de texto: "+wordsAll.length);
        } catch (Exception e) {
            System.out.println("ERROR al llenar el archivo... ");
        }
    }










    public void borrarArchivo() {
        if (archivo.exists()) {
            System.out.println("Seguro que quieres eliminar el archivo (Si/no)??");
            String res = nrg.next();

            if (res.equalsIgnoreCase("Si")) {
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(archivo);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (pw != null) {
                    pw.close();
                }
                System.out.println("Archivo Borrado");
            } else {
                System.out.println("Accion cancelada");
            }
        } else {
            System.out.println("No hay un archivo existente");
        }
    }










    public String[] cargarPalabras() {
        String[] palabras = new String[0];

        try {
            if (archivo.exists()) {
                int cont = 0;
                String linea;
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                while ((linea = br.readLine()) != null) {
                    linea = linea.trim();
                    if (linea.startsWith(spanish + ": ") || linea.startsWith(inglish + ": ")) {
                        cont++;
                    }
                }
                br.close();

                palabras = new String[cont];
                int k = 0;
                br = new BufferedReader(new FileReader(archivo));
                while ((linea = br.readLine()) != null) {
                    linea = linea.trim();
                    if (linea.startsWith(spanish + ": ") || linea.startsWith(inglish + ": ")) {
                        palabras[k++] = linea;
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo....");
        }
        return palabras;
    }













    // Metodo/ Algoritmo de Ordenamiento-----------------
    public void shellSort(String[] words) {
        int saltos = words.length / 2;
        while (saltos > 0) {
            for (int i = saltos; i < words.length; i++) {
                String aux = words[i];
                int j = i;
                while (j > saltos && words[j - saltos].substring(4).compareTo(aux.substring(4)) > 0) {
                    words[j] = words[j - saltos];
                    j -= saltos;
                }
                words[j] = aux;
            }
            saltos = saltos / 2;
        }
    }
}

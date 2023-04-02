package com.grupo10_150;

/**
 * Pronosticos Deportivos
 *    GRUPO 10 - 150
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static  void main(String[] args) throws Exception {
        //tomar los archivos a leer desde args();
        File resuFile;
        File pronFile;        
        if (args.length == 2) {
            if (args[0] == "resultados.cvs" ){
                resuFile = new File(args[0]);
                pronFile = new File(args[1]);           
            } else {
                resuFile = new File(args[1]);
                pronFile = new File(args[0]);
            }

        }else{
            System.out.print("\033[H\033[2J");
            System.out.println("============================================");  
            System.out.println("              SIN ARGUMENTOS");  
            System.out.println("============================================");  
            System.out.println("** Alguno de los archivos no se declaran  **");
            System.out.println("** Se toman los archivos de muestra desde **");
            System.out.println("**    src\\test\\resources en resources     **");
            System.out.println("********************************************");
            resuFile = new File(".\\src\\test\\resources\\resultados.csv");
            pronFile = new File(".\\src\\test\\resources\\pronostico.csv");
        }
        // inicializo variables

        // Leo los resultados y obtengo un array de los pronosticos
        List<Partido> partidosList = leerResultados(resuFile);
        System.out.println("Partidos: " + partidosList.size());
        List<Pronostico> pronosticosList = leerPronosticos(partidosList, pronFile);
        // Calculo los puntos
        double puntos= 0;
        if (pronosticosList != null) {
            for (Pronostico p : pronosticosList ) {
                puntos = puntos + p.getPuntos();
            }
            System.out.println("============================================");
            System.out.println("Puntos obtenidos: " + String.valueOf(Math.round(puntos)+" puntos\n")); 
            System.out.println("============================================"); 
        }else{
            System.out.println("No se pudo leer el archivo de pronosticos");
        }
    }

    static List<Partido> leerResultados(File resuFile) {
        // inicializo variables
        int nroDeRonda = 1; 
        int nroDeMatch = 0; // inicializo en 0, por def el archivo viene en orden
       // Ronda ronda = new Ronda(nroDeRonda, null);
        Scanner scResu = null;
        String[] datos = null;    
        // leer archivo resultados.cvs y lo guardo en una coleccion de objetos Partido
        //preparoelarchi para leerlo



        List<Partido> partidosList = new ArrayList<Partido>();


        try {
            scResu = new Scanner(resuFile);
            scResu.nextLine(); // salteo encabezados
              //  Ejemplo: 1;Argentina;Seleccionado;1;2;2;Arabia Saudita;Seleccionado
                int id_equip1 = 0;
                String equipo1 = "";
                String descrip1 = "";
                int goles1 = 0;
                int goles2 = 0;
                int id_equip2 = 0;
                String equipo2 = "";
                String descrip2 = "";
                //preparo 2 variables auxiliares para crear los objetos de los equipos
                Equipo equipo1Aux;
                Equipo equipo2Aux;
  
            while (scResu.hasNextLine()) {
                nroDeMatch++;        
                // Objetivo1, crear los objetos de la info que se encuentran en el archivo resultados.csv
                datos = scResu.nextLine().split(";");
                // leo datos
                id_equip1 = Integer.parseInt(datos[0]);
                equipo1 = datos[1];
                descrip1 = datos[2];
                goles1 = Integer.parseInt(datos[3]);
                goles2 = Integer.parseInt(datos[4]);
                id_equip2 = Integer.parseInt(datos[5]);
                equipo2 = datos[6];
                descrip2 = datos[7];    
                // agrego el partido al array de partidos
                equipo1Aux = new Equipo(id_equip1,equipo1,descrip1);
                equipo2Aux = new Equipo(id_equip2,equipo2,descrip2);
                partidosList.add(new Partido( equipo1Aux, equipo2Aux, goles1, goles2, nroDeRonda, nroDeMatch));
            }
                scResu.close();     
   
            return partidosList;
        } 
          catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } 
    }

      static List<Pronostico> leerPronosticos(List<Partido> partidosList, File pronFile) {

      // inicializo variables
      int nroDeRonda = 1;
      int nroDeMatch = 0;
     // File pronosticos = new File("C:\\Users\\Willy\\Desktop\\VSCProjects\\PronosticosDeportivos\\src\\App\\pronostico.csv");
    
      Scanner scPron = null;        
      List<Pronostico> pronosticosList = new ArrayList<Pronostico>();
      try {
          
          scPron = new Scanner(pronFile);
          scPron.nextLine(); // salteo encabezados
          // Equipo 1 - id;Gana 1;Empata;Gana 2;Equipo 2 - id
          // ejemplo 1;X;;;2 
          int id_equip1;
          String  gana1;
          String empata;
          String  gana2;
          int id_equip2;
          int indice = 0;
          //preparo 2 variables auxiliares para crear los objetos de los equipos
          String[] datos;
          while (scPron.hasNextLine()) {
              nroDeMatch++;             
              // Objetivo1, crear los objetos de la info que se encuentran en el archivo resultados.csv
              datos = scPron.nextLine().split(";");

              id_equip1 = Integer.parseInt(datos[0]);
              gana1 = datos[1];
              empata = datos[2];
              gana2 = datos[3];
              id_equip2 = Integer.parseInt(datos[4]);
          
              // busco en el array de resultados ya leido
              for (Partido p : partidosList) {
                  if(p.getMatchNro() == nroDeMatch){
                  
                        if(datos[1].equals("X")){
                            indice++;
                        // si gana1 = x;    
                        pronosticosList.add(new Pronostico(p,  p.getEquipo1(), ResultadoEnum.GANADOR, nroDeRonda , nroDeMatch));
                        pronosticosList.add(new Pronostico(p,  p.getEquipo2(), ResultadoEnum.PERDEDOR, nroDeRonda, nroDeMatch));
                        }
                        if(datos[2].equals("X")){
                        // empate = true;

                        pronosticosList.add(new Pronostico(p,  p.getEquipo1(), ResultadoEnum.EMPATE, nroDeRonda, nroDeMatch));
                        pronosticosList.add(new Pronostico(p,  p.getEquipo2(), ResultadoEnum.EMPATE, nroDeRonda, nroDeMatch));
                        }
                        if(datos[3].equals("X")){
                        // gana2 = true;
                        indice++;
                        pronosticosList.add(new Pronostico(p,  p.getEquipo1(), ResultadoEnum.PERDEDOR, nroDeRonda, nroDeMatch));
                        indice++;
                        pronosticosList.add(new Pronostico(p,  p.getEquipo2(), ResultadoEnum.GANADOR, nroDeRonda, nroDeMatch));
                        }
                      }
                }
            }
          
          scPron.close(); 
          return pronosticosList;  

      } catch (FileNotFoundException e) {
          e.printStackTrace();
          return null;
      } 

    }

}

package com.grupo10_150;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class LectorCsv {
    String resuFile;
    String pronFile;
    List<PartidoBind> lineasResultados;
    List<PronosticoBind> lineasProde;

    public LectorCsv(String resuFile, String pronFile) {
        this.resuFile = resuFile;
        this.pronFile = pronFile;
        this.lineasResultados = new ArrayList<>();
        this.lineasProde = new ArrayList<>();
        // System.out.println("Archivo de resultados: " + pronFile);
        // System.out.println("Archivo de resultados: " + this.pronFile);
    }




    public void parsearResultados() {

        List<PartidoBind> ListaDeResultados = null;
  
        try {
            // En esta primera línea definimos el archivos que va a ingresar
            ListaDeResultados = new CsvToBeanBuilder(new FileReader(this.resuFile))
                    // con esta configuración podemos skipear la primera línea de nuestro archivo CSV
                    .withSkipLines(1)
                    // con esta configuración podemos elegir cual es el caracter que vamos a usar para delimitar
                    .withSeparator(';')
                    // Es necesario definir el tipo de dato que va a generar el objeto que estamos queriendo parsear a partir del CSV
                    .withType(PartidoBind.class)
                    .build()
                    .parse();

        } catch (IOException e) {
        e.printStackTrace();
        }
        this.lineasResultados=ListaDeResultados;
        // System.out.println("Cantidad de partidos: " + this.lineasResultados.size());
        }

    public void parsearProde() {
        List<PronosticoBind> ListaDePronosticos = null;

        try {
            // En esta primera línea definimos el archivos que va a ingresar
            ListaDePronosticos = new CsvToBeanBuilder(new FileReader(this.pronFile))
                    // con esta configuración podemos skipear la primera línea de nuestro archivo CSV
                    .withSkipLines(1)
                    // con esta configuración podemos elegir cual es el caracter que vamos a usar para delimitar
                     .withSeparator(';')
                    // Es necesario definir el tipo de dato que va a generar el objeto que estamos queriendo parsear a partir del CSV
                    .withType(PronosticoBind.class)
                    .build()
                    .parse();

        } catch (IOException e) {
        e.printStackTrace();
    }
        this.lineasProde=ListaDePronosticos;
        // System.out.println("Cantidad de partidos: " + ListaDePronosticos);
        // System.out.println("Cantidad de pronosticos: " + this.lineasProde.size());

    }

// Crear resultados de partidos
    public ArrayList<Partido> crearResultados() {
        ArrayList<Partido> partidosList = new ArrayList<Partido>();
        Equipo equipo1Aux;
        Equipo equipo2Aux;
        int nroRonda = 1; // una ronda, primer entrega
        int i=0; // nro de match (vienen en orden)
//     int nroDeMatch = 0; // inicializo en 0, por def el archivo viene en orden
        for ( PartidoBind r : this.lineasResultados) {
            // agrego el partido al array de partidos
            i++;
            // System.out.println("entre al for");
            // System.out.println("Equipo 1: " + r.getEquipo1_nombre() + " - " + r.getEquipo1_goles());
            // System.out.println("Equipo 2: " + r.getEquipo2_nombre() + " - " + r.getEquipo2_goles());
            equipo1Aux = new Equipo(r.getEquipo1_id(),r.getEquipo1_nombre(),r.getEquipo1_descripcion());
            equipo2Aux = new Equipo(r.getEquipo2_id(),r.getEquipo2_nombre(),r.getEquipo2_descripcion());
            partidosList.add(new Partido(equipo1Aux, equipo2Aux, r.getEquipo1_goles(), r.getEquipo2_goles(), nroRonda, i));
     //     partidosList.add(new Partido(new Equipo(r.getEquipo1_id(),r.getEquipo1_nombre(),r.getEquipo1_descripcion()), new Equipo(r.getEquipo2_id(),r.getEquipo2_nombre(),r.getEquipo2_descripcion()), r.getEquipo1_goles(), r.getEquipo2_goles(), nroRonda, i));
        }
        // System.out.println("Cantidad de partidos: " + partidosList.size());
        return partidosList;
    }

   // Crear pronosticos
   public ArrayList<Pronostico> crearPronosticos(ArrayList<Partido> partidosList){
        ArrayList<Pronostico> pronosticosList = new ArrayList<Pronostico>(); 
        int i=0;   
        for (PronosticoBind pb : this.lineasProde) {
            // agrego el partido al array de partidos
            i++;
            if (pb.getGana1().equals("X")) {
                // busco equipo1_id en partidosList
                for (Partido p : partidosList) {
                    if (p.getEquipo1().getId() == pb.getEquipo1_id() &&
                        p.getEquipo2().getId() == pb.getEquipo2_id()) {
                    pronosticosList.add(new Pronostico(1, i, p, p.getEquipo1(), ResultadoEnum.GANADOR));
                    pronosticosList.add(new Pronostico(1, i, p, p.getEquipo2(), ResultadoEnum.PERDEDOR));
                    }
                }
            }
            if (pb.getGana2().equals("X")) {
                // busco equipo2_id en partidosList
                for (Partido p : partidosList) {
                    if (p.getEquipo2().getId() == pb.getEquipo2_id() &&
                        p.getEquipo1().getId() == pb.getEquipo1_id()) {
                        pronosticosList.add(new Pronostico(1, i, p, p.getEquipo2(), ResultadoEnum.GANADOR));
                        pronosticosList.add(new Pronostico(1, i, p, p.getEquipo1(), ResultadoEnum.PERDEDOR));
                    }
                }
            }
            if (pb.getEmpata().equals("X")) {
                // busco equipo1_id en partidosList
                for (Partido p : partidosList) {
                    if (p.getEquipo1().getId() == pb.getEquipo1_id() &&
                        p.getEquipo2().getId() == pb.getEquipo2_id()) {
                        pronosticosList.add(new Pronostico(1, i, p, p.getEquipo1(), ResultadoEnum.EMPATE));
                        pronosticosList.add(new Pronostico(1, i, p, p.getEquipo2(), ResultadoEnum.EMPATE));
                    }
                }
            }
        }
    return pronosticosList;
   }
        
}

package com.grupo10_150;

public class Pronostico {

    private int rondaNro;
    private int matchNro; 
    private Partido partido;
    private Equipo equipo;
    private ResultadoEnum resultadoPronosticado;



    public Pronostico(int rondaNro, int matchNro, Partido partido, Equipo equipo, ResultadoEnum resultadoPronosticado) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultadoPronosticado = resultadoPronosticado;
        this.rondaNro = rondaNro;
        this.matchNro = matchNro;
    }
    public double getPuntos() {
        double puntos = 0;
        if (partido.getGana() == this.equipo.getId() && this.resultadoPronosticado == ResultadoEnum.GANADOR){
            puntos = 0.5;
        }
        if (partido.getGana() != this.equipo.getId() && this.resultadoPronosticado == ResultadoEnum.PERDEDOR){
            puntos =   0.5;
            }
        if (partido.getGana() == 0 && this.resultadoPronosticado == ResultadoEnum.EMPATE){
             puntos = 0.5;
        }

        return puntos;
        }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    public void setRondaNro(int rondaNro) {
        this.rondaNro = rondaNro;
    }
    public void setMatchNro(int matchNro) {
        this.matchNro = matchNro;
    }
    
    public Partido getPartido() {
        return this.partido;
    }
    public Equipo getEquipo() {
        return this.equipo;
    }
    public ResultadoEnum getResultadoPronosticado() {
        return this.resultadoPronosticado;
    } 
    public void setResultadoPronosticado(ResultadoEnum resultadoPronosticado) {
        this.resultadoPronosticado = resultadoPronosticado;
    }
    public int getRonda() {
        return this.rondaNro;
    }
    public int getMatchNro() {
        return this.matchNro;
    }
}


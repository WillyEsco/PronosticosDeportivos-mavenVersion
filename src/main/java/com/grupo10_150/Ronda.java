package com.grupo10_150;
public class Ronda {
    private int nro;
    private Partido[] partidos;

    public Ronda(int  nro, Partido[] partidos) {
        this.nro = nro;
        this.partidos = partidos;
    }
    public int getNro() {
        return this.nro;
    }
    public Partido[] getPartidos() {
        return this.partidos;
    }
    
    public void setNro(int nro) {
        this.nro = nro;
    }
    public void setPartidos(Partido[] partidos) {
        this.partidos = partidos;
    }
    
}

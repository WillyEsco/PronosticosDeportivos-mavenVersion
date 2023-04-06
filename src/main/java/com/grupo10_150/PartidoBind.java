package com.grupo10_150;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
//Lombok
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor  




public class PartidoBind {
    @CsvBindByPosition(position = 0) 
    private int equipo1_id;
    @CsvBindByPosition(position = 1)
    private String equipo1_nombre;
    @CsvBindByPosition(position = 2)
    private String equipo1_descripcion;
    @CsvBindByPosition(position = 3)
    private int equipo1_goles;
    @CsvBindByPosition(position = 4)
    private int equipo2_goles;
    @CsvBindByPosition(position = 5)
    private int equipo2_id;
    @CsvBindByPosition(position = 6)
    private String equipo2_nombre;
    @CsvBindByPosition(position = 7)
    private String equipo2_descripcion;

   
}
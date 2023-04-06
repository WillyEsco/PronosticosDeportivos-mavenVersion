package com.grupo10_150;
import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
//Lombok
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data

@Setter
@Getter

@NoArgsConstructor  

public class PronosticoBind {
    @CsvBindByPosition(position = 0)
    private int equipo1_id;
    @CsvBindByPosition(position = 1)
    private String gana1; 
    @CsvBindByPosition(position = 2)
    private String empata;
    @CsvBindByPosition(position = 3)
    private String gana2;
    @CsvBindByPosition(position = 4)
    private int equipo2_id;


    public PronosticoBind(int equipo1_id, String gana1, String empata, String gana2, int equipo2_id) {
        this.equipo1_id = equipo1_id;
        this.gana1 = gana1;
        this.empata = empata;
        this.gana2 = gana2;
        this.equipo2_id = equipo2_id;
    }
   
}


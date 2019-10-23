package com.examplev2.libreria.Mapper;

import com.examplev2.libreria.DTO.DTOAutore;
import com.examplev2.libreria.entity.Autore;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@Data
public class MapperAutore {


    private String nome;
    private String cognome;
    private LocalDate annoDiNascita;


    public static Autore autoreToDTO(DTOAutore dtoAutore) {
       Autore autore = new Autore();
       autore.setNome(dtoAutore.getNome());
       autore.setCognome(dtoAutore.getCognome());
       autore.setAnnoDiNascita(dtoAutore.getAnnoDiNascita());
        return autore;


    }


}

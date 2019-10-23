package com.examplev2.libreria.Mapper;

import com.examplev2.libreria.DTO.DTOAutore;
import com.examplev2.libreria.DTO.DTOLibro;
import com.examplev2.libreria.entity.Autore;
import com.examplev2.libreria.entity.GeneriLibri;
import com.examplev2.libreria.entity.Libro;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class MapperLibro {


    public Libro fromDTOtoBook(DTOLibro dtoBook) {
        Libro libro = new Libro();

            libro.setIsbn(dtoBook.getIsbn());



            libro.setTitolo(dtoBook.getTitolo());



            libro.setAutore(converterAutori(dtoBook.getAutore()));


            libro.setAnnoDiPubblicazione(dtoBook.getAnnoDiPubblicazione());


            libro.setGenere(convertGeneriString(dtoBook.getGenere()));

        return libro;
    }

    public List<Autore> converterAutori(List<DTOAutore> dtoAutoreList) {
        List<Autore> autoreList = new ArrayList<>();
        for (DTOAutore dtoAutore : dtoAutoreList) {
            autoreList.add(MapperAutore.autoreToDTO(dtoAutore));
        }
        return autoreList;
    }

    public List<GeneriLibri> convertGeneriString(List<String> genere) {
        List<GeneriLibri> generiLibriList = new ArrayList<>();
        for (String a : genere) {
            if (a.charAt(0) == 'G') {
                a = "GIALLO";
            } else if (a.charAt(0) == 'A') {
                a = "AVVENTURA";
            } else if (a.charAt(0) == 'F') {
                a = "FANTASY";
            } else if (a.charAt(0) == 'N') {
                a = "NARRATIVA";
            } else if (a.charAt(0) == 'S') {
                a = "STORICA";
            }
            switch (a.toUpperCase()) {

                case "GIALLO":
                    generiLibriList.add(GeneriLibri.GIALLO);
                    break;
                case "AVVENTURA":
                    generiLibriList.add(GeneriLibri.AVVENTURA);
                    break;
                case "FANTASY":
                    generiLibriList.add(GeneriLibri.FANTASY);
                    break;
                case "NARRATIVA":
                    generiLibriList.add(GeneriLibri.NARRATIVA);
                    break;
                case "STORICA":
                    generiLibriList.add(GeneriLibri.STORICA);
                    break;

            }
        }
        return generiLibriList;
    }


}



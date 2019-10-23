package com.examplev2.libreria.DTO;

import com.examplev2.libreria.entity.GeneriLibri;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DTOLibro {

    private String isbn;
    private String titolo;
    private List<DTOAutore> autore;
    private LocalDate annoDiPubblicazione;
    private List<String> genere;

    public DTOLibro() {
        setIsbn(isbn);
        setTitolo(titolo);
        setAutore(autore);
        setAnnoDiPubblicazione(annoDiPubblicazione);
        setGenere(genere);
    }

}

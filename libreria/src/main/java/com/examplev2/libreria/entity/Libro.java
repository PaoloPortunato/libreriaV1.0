package com.examplev2.libreria.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Document(collection = "libro")
public class Libro {
    @Id
    private String isbn;
    private String titolo;
    private List<Autore> autore = new ArrayList<>();
    @DateTimeFormat
    private LocalDate annoDiPubblicazione;
    private List<GeneriLibri> genere = new ArrayList<GeneriLibri>();

    public enum PropertiesLibro {
        ISBN("isbn"),

        TITOLO("titolo"),

        AUTORE("autore"),

        ANNODIPUBBLICAZIONE("annoDiPubblicazione"),

        GENERE("genere");

        private String value;

        PropertiesLibro(String value) {  this.value = value; }

        public String getValue() { return this.value; }

    }
    public Libro() {

    }

    public Libro(String isbn, String titolo, List<Autore> autore, LocalDate annoDiPubblicazione, List<GeneriLibri> genere) {
        setIsbn(isbn);
        setTitolo(titolo);
        setAutore(autore);
        setAnnoDiPubblicazione(annoDiPubblicazione);
        setGenere(genere);
    }

}




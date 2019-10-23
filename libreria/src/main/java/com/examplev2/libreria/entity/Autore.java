package com.examplev2.libreria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@AllArgsConstructor
public class Autore {

    private String nome;
    private String cognome;
    @DateTimeFormat
    private LocalDate annoDiNascita;

    public enum PropertiesAutore {
        NOME("nome"),
        COGNOME("cognome"),
        ANNODINASCITA("annoDiNascita");

        private String value;

        PropertiesAutore(String value) { this.value = value; }

        public String getValue() { return this.value; }
    }
    public Autore(String nome,String cognome,String annoDiNascita) {
        setNome(nome);
        setCognome(cognome);
        setAnnoDiNascita(annoDiNascita);
    }
    public  Autore() {


    }
    public void setAnnoDiNascita(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.annoDiNascita = LocalDate.parse(data,formatter);
    }


}

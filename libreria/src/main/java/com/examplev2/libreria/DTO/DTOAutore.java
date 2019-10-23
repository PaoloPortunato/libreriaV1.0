package com.examplev2.libreria.DTO;

import lombok.Data;

@Data
public class DTOAutore {

    private String nome;
    private String cognome;
    private String annoDiNascita;

    public DTOAutore() {
        setNome(nome);
        setCognome(cognome);
        setAnnoDiNascita(annoDiNascita);
    }
}

package com.examplev2.libreria.MyHTTPException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class NullPointValuesException extends Exception {

    public NullPointValuesException() {
        super("NullPointValues");
    }

    /*@GetMapping(value = "/libri/autore")
    public List<Libro> cercaPerAutore(@RequestParam(defaultValue = "") String nome, @RequestParam(defaultValue = "") String cognome) {
       if(!libreriaService.trovaLibriDaAutore(nome,cognome).isEmpty()) {
           return libreriaService.trovaLibriDaAutore(nome, cognome);
       }throw new ResponseStatusException(HttpStatus.NOT_FOUND,"BookNotFound");

    }*/
}

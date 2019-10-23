package com.examplev2.libreria.controller;


import com.examplev2.libreria.DTO.DTOLibro;
import com.examplev2.libreria.MyHTTPException.BookNotFoundException;
import com.examplev2.libreria.MyHTTPException.NullPointValuesException;
import com.examplev2.libreria.entity.Libro;
import com.examplev2.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/libreria")
public class ProvaController {

    @Autowired
    LibroService libroService;

    //fatto
    @GetMapping(value = "/libri")
    public List<Libro> stampaLibreria() throws BookNotFoundException {

        return libroService.stampaLibreria();
    }

    //fatto
    @GetMapping(value = "/libri/genere/{genere}")
    public List<Libro> cercaPerGenere(@PathVariable String genere) {


        return libroService.trovaLibriPerGenere(genere);


    }

    //fatto
    @GetMapping(value = "/libri/data/{anno}")
    public List<Libro> cercaPerData(@PathVariable int anno) {

        return libroService.trovaLibriPrimaData(anno);

    }

    //fatto
    @GetMapping(value = "/libri/titolo/{titolo}")

    public List<Libro> cercaPerTitolo(@PathVariable String titolo) {

        return libroService.trovaLibroDaTitolo(titolo);

    }


    @GetMapping(value = "/libri/autore")
    public List<Libro> cercaPerAutore(@RequestParam(defaultValue = "") String nome, @RequestParam(defaultValue = "") String cognome) throws BookNotFoundException {
        return libroService.trovaLibriDaAutore(nome, cognome);
    }

    //fatto
    @PostMapping(value = "/libri/aggiungi")
    public String creaLibro(@RequestBody DTOLibro dtoLibro) {
        return libroService.createBook(dtoLibro);
    }

    @PutMapping(value = "/libri/aggiorna/{isbn}")
    public String aggiornaLibro(@PathVariable("isbn") String isbn, @RequestBody DTOLibro dtoLibro) throws NullPointValuesException {
        return libroService.modBook(isbn, dtoLibro);
    }

    //fatto
    @DeleteMapping(value = "/elimina/{isbn}")
    public String deleteLibro(@PathVariable("isbn") String isbn) {

        return libroService.deleteBook(isbn);

    }

    //fatto
    @PostMapping(value = "/trova")
    public List<Libro> trovadacampi(@RequestBody DTOLibro dtoLibro) {
        return libroService.trovaLibroPerValori(dtoLibro);
    }

    @PostMapping(value = "/trovastream")
    public List<Libro> trovaperstream(@RequestBody Map<String, Object> m) {
        return libroService.trovaLibroPerStream(m);
    }


}

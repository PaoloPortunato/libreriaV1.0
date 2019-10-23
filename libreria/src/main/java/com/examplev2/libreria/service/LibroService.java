package com.examplev2.libreria.service;

import com.examplev2.libreria.DTO.DTOLibro;
import com.examplev2.libreria.MyHTTPException.BookNotFoundException;
import com.examplev2.libreria.entity.Autore;
import com.examplev2.libreria.entity.Libro;

import java.util.List;
import java.util.Map;

public interface LibroService {
    public List<Libro> stampaLibreria() throws BookNotFoundException;

    public List<Libro> trovaLibroDaTitolo(String titolo);

    public List<Libro> trovaLibriDaAutore(String autoreNome, String autoreCognome) throws BookNotFoundException;

    public List<Libro> trovaLibriPerGenere(String genere);

    public List<Libro> trovaLibriPrimaData(int year);

    public String createBook(DTOLibro dtoLibro);

    public String modBook(String isbn, DTOLibro dtoLibro);

    public String deleteBook(String isbn);

    public List<Libro> trovaLibroPerValori(DTOLibro dtoLibro);

    public List<Libro> trovaLibroPerStream(Map<String, Object> m);

}

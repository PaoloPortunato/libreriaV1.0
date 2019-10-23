package com.examplev2.libreria.serviceImpl;

import com.examplev2.libreria.DTO.DTOLibro;
import com.examplev2.libreria.Mapper.MapperLibro;
import com.examplev2.libreria.entity.Autore;
import com.examplev2.libreria.entity.GeneriLibri;
import com.examplev2.libreria.entity.Libro;
import com.examplev2.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceImpl implements LibroService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    MapperLibro mapperLibro;


    /**
     * Stampa la libreria in formato JSON
     */
    @Override
    public List<Libro> stampaLibreria() {
        if (mongoTemplate.findAll(Libro.class).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libreria vuota");
        }
        return mongoTemplate.findAll(Libro.class);


    }
    /**
     * Trova libri da determinato Titolo
     *
     * @param titolo ==> Titolo libro
     * @return ==> ritorna una lista di libri
     */
    @Override

    public List<Libro> trovaLibroDaTitolo(String titolo) {

        return mongoTemplate.find(new Query(Criteria.where(Libro.PropertiesLibro.TITOLO.getValue()).is(titolo)), Libro.class);

    }

    /**
     * Trova libri da determinato autore
     *
     * @param autoreNome    ==> Nome dell'autore
     * @param autoreCognome ==> Cognome dell'autore
     * @return ==> ritorna una lista di libri
     */
    @Override

    public List<Libro> trovaLibriDaAutore(String autoreNome, String autoreCognome) {
        Query query = new Query();

        query.addCriteria(Criteria.where(Autore.PropertiesAutore.NOME.getValue()).is(autoreNome));
        return mongoTemplate.find(query, Libro.class);
    }

    /**
     * Trova libri da determinato genere
     *
     * @param genere ==> Genere del libro
     * @return ==> ritorna una lista di libri
     */
    @Override
    public List<Libro> trovaLibriPerGenere(String genere) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Libro.PropertiesLibro.GENERE.getValue()).is(genere));
        return mongoTemplate.find(query, Libro.class);
    }

    /**
     * Trova libri pubblicati prima di una determinata data
     *
     * @param year ==> Anno da cui cercare in precedenza
     * @return ==> ritorna una lista di libri
     */
    @Override
    public List<Libro> trovaLibriPrimaData(int year) {
        Query query = new Query();
        LocalDate localDate = LocalDate.of(year, 1, 1);
        query.addCriteria(Criteria.where(Libro.PropertiesLibro.ANNODIPUBBLICAZIONE.getValue()).lt(localDate));
        return mongoTemplate.find(query, Libro.class);
    }

    /**
     * @param
     * @return
     */
    @Override
    public String createBook(DTOLibro dtoLibro) {
        Libro libro = mapperLibro.fromDTOtoBook(dtoLibro);
        mongoTemplate.save(libro);
        return "Libro Aggiunto";
    }

    /**
     * @param isbn
     * @return
     */
    @Override
    public String deleteBook(String isbn) {

        mongoTemplate.remove(mongoTemplate.findById(isbn, Libro.class));


        return "Libro Eliminato";
    }

    /**
     * @param isbn
     * @param
     * @return
     */
    @Override
    public String modBook(String isbn, DTOLibro dtoLibro) {
        Libro libro = mongoTemplate.findById(isbn, Libro.class);


       if (dtoLibro.getTitolo() != null) {
            libro.setTitolo(mapperLibro.fromDTOtoBook(dtoLibro).getTitolo());
        }else libro.setTitolo(libro.getTitolo());

        if (dtoLibro.getAutore() != null) {
            libro.setAutore(mapperLibro.fromDTOtoBook(dtoLibro).getAutore());
        }else libro.setAutore(libro.getAutore());
        if (dtoLibro.getAnnoDiPubblicazione() != null) {
            libro.setAnnoDiPubblicazione(mapperLibro.fromDTOtoBook(dtoLibro).getAnnoDiPubblicazione());
        }else libro.setAnnoDiPubblicazione(libro.getAnnoDiPubblicazione());
        if (dtoLibro.getGenere() != null) {
            libro.setGenere(mapperLibro.fromDTOtoBook(dtoLibro).getGenere());
        }else libro.setGenere(libro.getGenere());


        mongoTemplate.save(libro);




        return "Libro Aggiornato";
    }

    /**
     * @param
     * @return
     */
    @Override
    public List<Libro> trovaLibroPerValori(DTOLibro dtoLibro) {
        Query query = new Query();
        Libro libro = mapperLibro.fromDTOtoBook(dtoLibro);

        List<Criteria> listaGeneri = new ArrayList<>();
        List<Criteria> listQuery = new ArrayList<>();


        if (libro.getIsbn() != null) {

            listQuery.add(Criteria.where(Libro.PropertiesLibro.ISBN.getValue()).
                    is(libro.getIsbn()));
        }else
            //================================================================//
            if (libro.getTitolo() != null) {

                listQuery.add(Criteria.where(Libro.PropertiesLibro.TITOLO.getValue()).
                        regex(libro.getTitolo()));
            }
        //================================================================//
        if (libro.getAutore() != null) {

            for (Autore a : libro.getAutore()) {
                if (a.getNome() != null) {

                    listQuery.add(new Criteria().orOperator(Criteria.where(Libro.PropertiesLibro.AUTORE.getValue() + "."
                            + Autore.PropertiesAutore.NOME.getValue()).regex((String)
                            a.getNome())));
                }
                if (a.getCognome() != null) {
                    listQuery.add((new Criteria().orOperator(Criteria.where(Libro.PropertiesLibro.AUTORE.getValue() + "."
                            + Autore.PropertiesAutore.COGNOME.getValue()).regex((String)
                            a.getCognome()))));
                }
            }

        }
        //================================================================//
        if (libro.getAnnoDiPubblicazione() != null) {

            listQuery.add(Criteria.where(Libro.PropertiesLibro.ANNODIPUBBLICAZIONE.getValue()).lte(
                   libro.getAnnoDiPubblicazione()));
        }
        //================================================================//

            if (libro.getGenere() != null) {


                for (GeneriLibri a : libro.getGenere()) {
                    listQuery.add(Criteria.where(Libro.PropertiesLibro.GENERE.getValue()).regex(a.toString(), "i"));
                }
            }
        query.addCriteria(new Criteria().andOperator(listQuery.toArray(new Criteria[listQuery.size()])));
        return mongoTemplate.find(query, Libro.class);
    }

    @Override
    public List<Libro> trovaLibroPerStream(Map<String, Object> m) {
        List<Libro> auxLibro1 = null;

        return auxLibro1;
    }

    /**
     * @param genere
     * @param generi
     */
    public void convertGeneriString(List<String> genere,List<GeneriLibri> generi) {

        for (String a : genere) {

            switch (a.toUpperCase()) {
                case "GIALLO":
                    generi.add(GeneriLibri.GIALLO);
                    break;
                case "AVVENTURA":
                    generi.add(GeneriLibri.AVVENTURA);
                    break;
                case "FANTASY":
                    generi.add(GeneriLibri.FANTASY);
                    break;
                case "NARRATIVA":
                    generi.add(GeneriLibri.NARRATIVA);
                    break;
                case "STORICA":
                    generi.add(GeneriLibri.STORICA);
                    break;

            }
        }

    }
}


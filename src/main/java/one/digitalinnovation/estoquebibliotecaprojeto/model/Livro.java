package one.digitalinnovation.estoquebibliotecaprojeto.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Os atributos desse modelo foram criados com base em atributos de um livro
 *
 * @author gucabal
 */

@Entity
@Getter
@Setter
public class Livro {

    @Id
    private String titulo;
    private String autor;
    private String editora;
    private int paginas;
    private int quantidade;

}

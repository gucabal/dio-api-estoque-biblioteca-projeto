package one.digitalinnovation.estoquebibliotecaprojeto.service;

import one.digitalinnovation.estoquebibliotecaprojeto.model.Livro;
import org.springframework.http.ResponseEntity;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de Estoque. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 * @author gucabal
 */
public interface EstoqueService {

    Iterable<Livro> buscarLivros();

    ResponseEntity<Object> buscarPorTitulo(String titulo);

    Livro inserir(Livro livro);

    ResponseEntity<Object> atualizar(Livro livro);

    ResponseEntity<Object> deletar(String nome);

}

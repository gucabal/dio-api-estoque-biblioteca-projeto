package one.digitalinnovation.estoquebibliotecaprojeto.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Interface responsável pelo banco de dados
 *
 * @author gucabal
 */
@Repository
public interface EstoqueRepository extends CrudRepository<Livro, String> {
}

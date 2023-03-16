package one.digitalinnovation.estoquebibliotecaprojeto.service.Impl;

import one.digitalinnovation.estoquebibliotecaprojeto.model.EstoqueRepository;
import one.digitalinnovation.estoquebibliotecaprojeto.model.Livro;
import one.digitalinnovation.estoquebibliotecaprojeto.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link EstoqueService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 *
 * @author gucabal
 */
@Service
public class EstoqueServiceImpl implements EstoqueService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    EstoqueService estoqueService;


    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Livro> buscarLivros() {
        //buscando todos livros da base de dados.
        return estoqueRepository.findAll();
    }

    @Override
    public ResponseEntity<Object> buscarPorTitulo(String titulo) {
        return validaLivro(titulo);

    }

    @Override
    public Livro inserir(Livro livro) {
        // Salva o livro no banco de dados
        return inserirLivro(livro);

    }


    @Override
    public ResponseEntity<Object> atualizar(Livro livro) {
        // Buscar Livro por Titulo, e validar se existe.
        return atualizaLivro(livro);


    }

    @Override
    public ResponseEntity<Object> deletar(String titulo) {
        // Deletar Livro por Titulo, e validar se existe.
        return deletaLivro(titulo);
    }


    public ResponseEntity<Object> validaLivro(String titulo) {
        // Buscar Livro por Titulo.
        ResponseEntity<Object> livro = null;
        Optional<Livro> livroExistente = estoqueRepository.findById(titulo);
        if (!livroExistente.isPresent()) {
            ResponseEntity<Object> livroNull = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado com o título: " + titulo);
            livro = livroNull;
        } else {
            ResponseEntity<Object> livroOk = ResponseEntity.ok(estoqueRepository.findById(titulo).get());
            livro = livroOk;
        }
        return livro;

    }

    public ResponseEntity<Object> deletaLivro(String titulo) {
        // Buscar Livro por Titulo.
        ResponseEntity<Object> livro = null;
        Optional<Livro> livroExistente = estoqueRepository.findById(titulo);
        if (!livroExistente.isPresent()) {
            ResponseEntity<Object> livroNull = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado com o título: " + titulo);
            livro = livroNull;
        } else {
            // Deleta livro
            estoqueRepository.deleteById(titulo);
            ResponseEntity<Object> deletarLivro = ResponseEntity.status(HttpStatus.ACCEPTED).body("Livro: " + titulo + " deletado do estoque!");
            livro = deletarLivro;
        }
        return livro;

    }

    public ResponseEntity<Object> atualizaLivro(Livro livroIn) {
        // Buscar Livro por Titulo.
        ResponseEntity<Object> livro = null;
        Optional<Livro> livroExistente = estoqueRepository.findById(livroIn.getTitulo());
        if (!livroExistente.isPresent()) {
            ResponseEntity<Object> livroNull = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado com o título: " + livroIn.getTitulo());
            livro = livroNull;
        } else {
            estoqueRepository.deleteById(livroIn.getTitulo());
            estoqueRepository.save(livroIn);
            ResponseEntity<Object> livroOk = ResponseEntity.status(HttpStatus.ACCEPTED).body("Livro: " + livroIn.getTitulo() + " atualizado no estoque!");

            livro = livroOk;
        }
        return livro;

    }

    public Livro inserirLivro(Livro livroIn) {
        //valido se o livro já existe, caso exista adiciono a nova quantidade ao estoque.
        Livro livro = null;
        Optional<Livro> livroExistente = estoqueRepository.findById(livroIn.getTitulo());
        if (!livroExistente.isPresent()) {
            estoqueRepository.save(livroIn);
        } else {
            Livro livro2 = estoqueRepository.findById(livroIn.getTitulo()).get();
            int soma = livroIn.getQuantidade() + livro2.getQuantidade();
            livroIn.setQuantidade(soma);
            livroIn.setAutor(livro2.getAutor());
            livroIn.setPaginas(livro2.getPaginas());
            livroIn.setEditora(livro2.getEditora());
            estoqueRepository.save(livroIn);
        }
        return livroIn;

    }

}

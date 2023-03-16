package one.digitalinnovation.estoquebibliotecaprojeto.rest.controller;

import one.digitalinnovation.estoquebibliotecaprojeto.model.Livro;
import one.digitalinnovation.estoquebibliotecaprojeto.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("biblioteca/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<Iterable<Livro>> buscarTodos() {
        return ResponseEntity.ok(estoqueService.buscarLivros());
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Object> buscarPorId(@PathVariable String titulo) {
        return estoqueService.buscarPorTitulo(titulo);

    }

    @PostMapping
    public ResponseEntity<Livro> inserir(@RequestBody Livro livro) {
        return ResponseEntity.ok(estoqueService.inserir(livro));

    }


    @PutMapping("")
    public ResponseEntity<Object> atualizar(@RequestBody Livro livro) {
        return estoqueService.atualizar(livro);

    }

    @DeleteMapping("/{titulo}")
    public ResponseEntity<Object> deletar(@PathVariable String titulo) {
        return estoqueService.deletar(titulo);
    }


}

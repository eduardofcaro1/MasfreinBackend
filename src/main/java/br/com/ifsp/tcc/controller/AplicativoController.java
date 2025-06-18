package br.com.ifsp.tcc.controller;

import br.com.ifsp.tcc.model.Aplicativo;
import br.com.ifsp.tcc.repository.AplicativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aplicativos")
@CrossOrigin(origins = "*")
public class AplicativoController {
    @Autowired
    AplicativoRepository repo;

    @GetMapping
    public List<Aplicativo> buscaTodosAplicativos(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Aplicativo> buscaAplicativo(@PathVariable Long id){
        return repo.findById(id);
    }

    @PostMapping
    public ResponseEntity adicionaAplicativo(@RequestBody Aplicativo app){
        try{
            Aplicativo cadastrado = repo.save(app);
            return new ResponseEntity(cadastrado, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void deletaAplicativo(@RequestBody Aplicativo app ){
            repo.delete(app);
    }
}

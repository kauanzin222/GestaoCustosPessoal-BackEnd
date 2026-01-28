package com.gestaocustos.gestpro.resources;

import java.net.URI;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gestaocustos.gestpro.models.Abastecimento;
import com.gestaocustos.gestpro.services.AbastecimentoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin
public class AbastecimentoController {
    @Autowired
    public AbastecimentoService abastecimentoService;

    @PostMapping("Abastecimentos")
    public ResponseEntity<Abastecimento> save(@RequestBody Abastecimento abastecimento) {
        abastecimento = abastecimentoService.save(abastecimento);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(abastecimento.getId())
                .toUri();

        return ResponseEntity.created(location).body(abastecimento);
    }

    @GetMapping("Abastecimentos/{id}")
    public ResponseEntity<Abastecimento> getAbastecimento(@PathVariable int id) {
        Abastecimento abastecimento = abastecimentoService.getById(id);
        return ResponseEntity.ok(abastecimento);
    }

    @GetMapping("Abastecimentos")
    public List<Abastecimento> getAbastecimentos() {
        return abastecimentoService.getAll();
    }

    @DeleteMapping("Abastecimentos/{id}")
    public ResponseEntity<Void> removeAbastecimento(@PathVariable int id) {
        abastecimentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("Abastecimento/{id}")
    public ResponseEntity<Void> updateAbastecimento(@PathVariable int id, @RequestBody Abastecimento abastecimentoUpdate) {
        abastecimentoService.update(id, abastecimentoUpdate);
        return ResponseEntity.ok().build();
    }
}

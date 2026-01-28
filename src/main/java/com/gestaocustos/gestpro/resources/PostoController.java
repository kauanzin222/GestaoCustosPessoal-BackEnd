package com.gestaocustos.gestpro.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gestaocustos.gestpro.models.Posto;
import com.gestaocustos.gestpro.services.PostoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin
public class PostoController {
    @Autowired
    public PostoService postoService;

    @PostMapping("Postos")
    public ResponseEntity<Posto> save(@RequestBody Posto posto) {
        posto = postoService.save(posto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(posto.getId())
                .toUri();

        return ResponseEntity.created(location).body(posto);
    }

    @GetMapping("Postos")
    public List<Posto> getPostos() {
        return postoService.getAll();
    }

    @GetMapping("Postos/{id}")
    public ResponseEntity<Posto> getPosto(@PathVariable int id) {
        Posto posto = postoService.getById(id);
        return ResponseEntity.ok(posto);
    }

    @DeleteMapping("Postos/{id}")
    public ResponseEntity<Void> deletePosto(@PathVariable int id) {
        postoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("Postos/{id}")
    public ResponseEntity<Void> updatePosto(@PathVariable int id, @RequestBody Posto postoUpdate) {
        postoService.update(id, postoUpdate);
        return ResponseEntity.ok().build();
    }
}

package com.gestaocustos.gestpro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestaocustos.gestpro.repositories.PostoRepo;
import com.gestaocustos.gestpro.models.Abastecimento;
import com.gestaocustos.gestpro.models.Posto;

@Service
public class PostoService {
    @Autowired
    public PostoRepo postoRepo;

    public Posto getById(int id) {
        Posto posto = postoRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Posto não encontrado!"));
        return posto;
    }

    public Posto getByAbastecimento(Abastecimento abastecimento) {
        Posto posto = postoRepo.findById(abastecimento.getPosto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Posto não encontrado!"));
        return posto;
    }

    public List<Posto> getAll() {
        return postoRepo.findAll();
    }

    public Posto save(Posto posto) {
        return postoRepo.save(posto);
    }

    public void deleteById(int id) {
        postoRepo.delete(getById(id));
    }

    public void update (int id, Posto postoUpdate) {
        Posto posto = getById(id);
        posto.setName(postoUpdate.getName());

        postoRepo.save(posto);
    }
}

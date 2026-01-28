package com.gestaocustos.gestpro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestaocustos.gestpro.models.Abastecimento;
import com.gestaocustos.gestpro.models.Posto;
import com.gestaocustos.gestpro.repositories.AbastecimentoRepo;

@Service
public class AbastecimentoService {
    @Autowired
    public AbastecimentoRepo abastecimentoRepo;
    @Autowired
    public PostoService postoService;

    public Abastecimento getById(int id) {
        Abastecimento abastecimento = abastecimentoRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abastecimento não encontrado!"));

        return abastecimento;
    }

    public List<Abastecimento> getAll() {
        return abastecimentoRepo.findAll();
    }

    public Abastecimento save(Abastecimento abastecimento) {
        return abastecimentoRepo.save(abastecimento);
    }

    public void deleteById(int id) {
        abastecimentoRepo.delete(getById(id));
    }

    public void update(int id, Abastecimento abastecimentoUpdate) {
        Abastecimento abastecimento = getById(id);

        if (abastecimentoUpdate.getPosto() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posto não pode estar vazio!");

        Posto posto = postoService.getByAbastecimento(abastecimentoUpdate);
        abastecimento.setPosto(posto);

        abastecimento.setDate(abastecimentoUpdate.getDate());
        abastecimento.setPrice(abastecimentoUpdate.getPrice());
        abastecimento.setStatusPay(abastecimentoUpdate.isStatusPay());

        abastecimentoRepo.save(abastecimento);
    }
}

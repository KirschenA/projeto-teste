package com.escola.core.aluno.domain.model;

import com.escola.core.model.PessoaId;

import java.util.Optional;


public interface AlunoDomainRepository {

    public Optional<Aluno> findById(PessoaId id);

}

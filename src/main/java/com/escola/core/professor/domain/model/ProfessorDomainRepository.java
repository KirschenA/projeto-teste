package com.escola.core.professor.domain.model;

import com.escola.core.model.PessoaId;

import java.util.Optional;

public interface ProfessorDomainRepository  {

    Optional<Professor> findById(PessoaId id);

}

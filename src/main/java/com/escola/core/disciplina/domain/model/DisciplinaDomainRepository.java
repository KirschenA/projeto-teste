package com.escola.core.disciplina.domain.model;

import java.util.Optional;

public interface DisciplinaDomainRepository {

    public Optional<Disciplina> findById(DisciplinaId id);

}

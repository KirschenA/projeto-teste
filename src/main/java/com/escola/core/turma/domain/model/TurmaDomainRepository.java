package com.escola.core.turma.domain.model;

import java.util.Optional;

public interface TurmaDomainRepository {

    public Optional<Turma> findById(TurmaId id);

}

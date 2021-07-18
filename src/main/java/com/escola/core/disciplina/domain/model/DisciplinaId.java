package com.escola.core.disciplina.domain.model;

import com.escola.util.DomainObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public class DisciplinaId extends DomainObjectId<UUID> {


    private static final long serialVersionUID = -7924298761422991790L;

    private DisciplinaId(final UUID id) {
        super(id);
    }

    public static DisciplinaId generate() {
        return new DisciplinaId(UUID.randomUUID());
    }

    @JsonCreator
    public static DisciplinaId from(final String id) {
        return id != null ? new DisciplinaId(UUID.fromString(id)) : null;
    }
}
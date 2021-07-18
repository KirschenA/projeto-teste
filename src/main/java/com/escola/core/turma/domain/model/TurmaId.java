package com.escola.core.turma.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.escola.util.DomainObjectId;

import java.util.UUID;

public class TurmaId extends DomainObjectId<UUID> {

    private static final long serialVersionUID = -7645374587698756453L;

    private TurmaId(final UUID id) {
        super(id);
    }

    public static TurmaId generate() {
        return new TurmaId(UUID.randomUUID());
    }

    @JsonCreator
    public static TurmaId from(final String id) {
        return id != null ? new TurmaId(UUID.fromString(id)) : null;
    }
}
package com.escola.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.escola.util.DomainObjectId;

import java.util.UUID;

public class PessoaId extends DomainObjectId<UUID> {

    private static final long serialVersionUID = -8045379945508321936L;

    private PessoaId(final UUID id) {
        super(id);
    }

    public static PessoaId generate() {
        return new PessoaId(UUID.randomUUID());
    }

    @JsonCreator
    public static PessoaId from(final String id) {
        return id != null ? new PessoaId(UUID.fromString(id)) : null;
    }
}
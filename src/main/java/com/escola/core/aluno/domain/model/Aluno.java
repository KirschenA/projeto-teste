package com.escola.core.aluno.domain.model;

import com.escola.core.aluno.domain.events.AlunoCriadoEvent;
import com.escola.core.model.PessoaId;
import com.escola.util.AbstractDomainEvent;
import lombok.*;

@Data
public class Aluno extends AbstractDomainEvent {

    private PessoaId id;
    private String nome;
    private String email;
    private String cpf;
    private Integer matricula;
    private String formaIngresso;

    @Builder
    public Aluno(final @NonNull PessoaId id,
                 @NonNull String nome,
                 String email,
                 @NonNull String cpf,
                 @NonNull Integer matricula,
                 String formaIngresso
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.matricula = matricula;
        this.formaIngresso = formaIngresso;

        this.registerEvent(AlunoCriadoEvent.from(this));
    }

}


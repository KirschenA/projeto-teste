package com.escola.core.professor.domain.model;

import com.escola.core.model.PessoaId;
import com.escola.core.professor.domain.events.ProfessorCriadoEvent;
import com.escola.util.AbstractDomainEvent;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class Professor extends AbstractDomainEvent {

    private PessoaId id;
    private String nome;
    private String email;
    private String cpf;
    private String titulacao;

    @Builder
    public Professor(final @NonNull PessoaId id,
                     @NonNull String nome,
                     String email,
                     @NonNull String cpf,
                     String titulacao) {


        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.titulacao = titulacao;

        this.registerEvent(ProfessorCriadoEvent.from(this));
    }
}

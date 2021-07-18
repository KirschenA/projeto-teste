package com.escola.core.professor.domain.events;

import com.escola.core.professor.domain.model.Professor;
import com.escola.util.DomainEvent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class ProfessorCriadoEvent implements DomainEvent {

    private String id;

    private String nome;

    private String email;

    private String cpf;

    private String titulacao;

    public static ProfessorCriadoEvent from(final Professor professor) {
        return ProfessorCriadoEvent.builder()
                .id(professor.getId().toString())
                .nome(professor.getNome())
                .email(professor.getEmail())
                .cpf(professor.getCpf())
                .titulacao(professor.getTitulacao())
                .build();

    }
}
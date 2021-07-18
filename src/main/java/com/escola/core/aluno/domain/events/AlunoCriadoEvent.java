package com.escola.core.aluno.domain.events;

import com.escola.util.DomainEvent;
import com.escola.core.aluno.domain.model.Aluno;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AlunoCriadoEvent implements DomainEvent {

    private String id;

    private String nome;

    private String email;

    private String cpf;

    private Integer matricula;

    private String formaIngresso;

    public static AlunoCriadoEvent from(final Aluno aluno) {
        return AlunoCriadoEvent.builder()
                .id(aluno.getId().toString())
                .nome(aluno.getNome())
                .email(aluno.getEmail())
                .cpf(aluno.getCpf())
                .matricula(aluno.getMatricula())
                .formaIngresso(aluno.getFormaIngresso())
                .build();

    }
}
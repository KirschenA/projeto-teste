package com.escola.core.disciplina.domain.events;

import com.escola.core.disciplina.domain.model.Disciplina;
import com.escola.core.model.PessoaId;
import com.escola.util.DomainEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class DisciplinaCriadaEvent implements DomainEvent {

    private String id;

    private String descricao;

    private String sigla;

    private Integer cargaHoraria;

    private final Set<DisciplinaProfessorEvent> professores;

    public static DisciplinaCriadaEvent from(final Disciplina disciplina) {
        return DisciplinaCriadaEvent.builder()
                .id(disciplina.getId().toString())
                .descricao(disciplina.getDescricao())
                .sigla(disciplina.getSigla())
                .cargaHoraria(disciplina.getCargaHoraria())
                .professores(disciplina.getProfessores()
                        .stream()
                        .map(DisciplinaProfessorEvent::of)
                        .collect(toSet()))
                .build();

    }


    @Data
    @Builder(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(staticName = "of")
    public static final class DisciplinaProfessorEvent {
        String id;


        public static final DisciplinaProfessorEvent of(PessoaId disciplinaProfessor) {
            return DisciplinaProfessorEvent.builder()
                    .id(disciplinaProfessor.getId().toString())
                    .build();
        }

    }

}

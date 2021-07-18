package com.escola.core.turma.domain.events;

import com.escola.util.DomainEvent;
import com.escola.core.disciplina.domain.model.DisciplinaId;
import com.escola.core.model.PessoaId;
import com.escola.core.turma.domain.model.Turma;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class TurmaCriadaEvent implements DomainEvent {

    private String id;

    private String descricao;

    private Integer anoLetivo;

    private Integer periodoLetivo;

    private Integer numeroVagas;

    private final Set<TurmaDisciplinaEvent> disciplinas;

    private final Set<TurmaAlunoEvent> alunos;

    public static TurmaCriadaEvent from(final Turma turma) {
        return TurmaCriadaEvent.builder()
                .id(turma.getId().toString())
                .descricao(turma.getDescricao())
                .anoLetivo(turma.getAnoLetivo())
                .periodoLetivo(turma.getPeriodoLetivo())
                .numeroVagas(turma.getNumeroVagas())
                .disciplinas(turma.getDisciplinas()
                        .stream()
                        .map(TurmaDisciplinaEvent::from)
                        .collect(toSet()))
                .alunos(turma.getAlunos()
                        .stream()
                        .map(TurmaAlunoEvent::from)
                        .collect(toSet()))
                .build();

    }

    @Data
    @Builder(access = AccessLevel.PRIVATE)
    public static final class TurmaDisciplinaEvent {
        private String id;

        public static final TurmaDisciplinaEvent from(DisciplinaId turmaDisciplina) {
            return TurmaDisciplinaEvent.builder()
                    .id(turmaDisciplina.getId().toString())
                    .build();
        }

    }

    @Data
    @Builder(access = AccessLevel.PRIVATE)
    public static final class TurmaAlunoEvent {
        private String id;

        public static final TurmaAlunoEvent from(PessoaId turmaAluno) {
            return TurmaAlunoEvent.builder()
                    .id(turmaAluno.getId().toString())
                    .build();
        }

    }

}

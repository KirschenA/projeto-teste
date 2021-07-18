package com.escola.core.turma.domain.model;

import com.escola.core.disciplina.domain.model.DisciplinaId;
import com.escola.core.model.PessoaId;
import com.escola.core.turma.domain.events.TurmaCriadaEvent;
import com.escola.util.AbstractDomainEvent;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class Turma extends AbstractDomainEvent {

    private TurmaId id;

    private String descricao;

    private Integer anoLetivo;

    private Integer periodoLetivo;

    private Integer numeroVagas;

    private Collection<DisciplinaId> disciplinas = new HashSet<>();

    private Collection<PessoaId> alunos = new HashSet<>();

    @Builder
    public Turma(final @NonNull TurmaId id,
                 @NonNull String descricao,
                 Integer anoLetivo,
                 Integer periodoLetivo,
                 Integer numeroVagas,
                 Set<DisciplinaId> disciplinas,
                 Set<PessoaId> alunos) {

        this.id = id;
        this.descricao = descricao;
        this.anoLetivo = anoLetivo;
        this.periodoLetivo = periodoLetivo;
        this.numeroVagas = numeroVagas;
        this.disciplinas = disciplinas;
        this.alunos = alunos;

        this.registerEvent(TurmaCriadaEvent.from(this));
    }
    
}

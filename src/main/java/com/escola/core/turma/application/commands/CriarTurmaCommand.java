package com.escola.core.turma.application.commands;

import com.escola.core.disciplina.domain.model.DisciplinaId;
import com.escola.core.model.PessoaId;
import com.escola.core.turma.domain.model.TurmaId;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public final class CriarTurmaCommand {

    private final TurmaId id;
    private final String descricao;
    private final Integer anoLetivo;
    private final Integer periodoLetivo;
    private final Integer numeroVagas;
    private final Set<DisciplinaId> disciplinas;
    private final Set<PessoaId> alunos;
}

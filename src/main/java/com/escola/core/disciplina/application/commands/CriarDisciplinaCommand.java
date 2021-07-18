package com.escola.core.disciplina.application.commands;

import com.escola.core.model.PessoaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Set;

@Data(staticConstructor = "of")
public final class CriarDisciplinaCommand {
    private final String descricao;
    private final String sigla;
    private final Integer cargaHoraria;
    private final Set<CriarDisciplinaProfessorCommand> professores;

    @Getter
    @EqualsAndHashCode
    @AllArgsConstructor(staticName = "from")
    public static final class CriarDisciplinaProfessorCommand {
        private final PessoaId id;
    }

}

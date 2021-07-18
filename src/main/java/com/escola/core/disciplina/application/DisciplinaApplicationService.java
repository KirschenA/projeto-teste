package com.escola.core.disciplina.application;

import com.escola.core.model.PessoaId;
import com.escola.core.disciplina.application.commands.CriarDisciplinaCommand;
import com.escola.core.disciplina.domain.model.Disciplina;
import com.escola.core.disciplina.domain.model.DisciplinaDomainRepository;
import com.escola.core.disciplina.domain.model.DisciplinaId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DisciplinaApplicationService {
    private final DisciplinaDomainRepository disciplinaDomainRepository;

    public Disciplina handle(final CriarDisciplinaCommand cmd) {

        var disciplina = Disciplina.builder()
                .id(DisciplinaId.generate())
                .descricao(cmd.getDescricao())
                .sigla(cmd.getSigla())
                .cargaHoraria(cmd.getCargaHoraria())
                .professores(professores(cmd.getProfessores()))
                .build();

        //disciplinaDomainRepository.insert(disciplina);

        return disciplina;
    }


    private Set<PessoaId> professores(Set<CriarDisciplinaCommand.CriarDisciplinaProfessorCommand> professores) {
        return professores.stream().map(CriarDisciplinaCommand.CriarDisciplinaProfessorCommand::getId).collect(Collectors.toSet());
    }

}

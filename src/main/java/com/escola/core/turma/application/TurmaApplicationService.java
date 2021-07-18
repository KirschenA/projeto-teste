package com.escola.core.turma.application;

import com.escola.core.turma.application.commands.CriarTurmaCommand;
import com.escola.core.turma.domain.model.Turma;
import com.escola.core.turma.domain.model.TurmaDomainRepository;
import com.escola.core.turma.domain.model.TurmaId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TurmaApplicationService {
    private final TurmaDomainRepository turmaDomainRepository;

    public Turma handle(final CriarTurmaCommand cmd) {
        var turma = Turma.builder()
                .id(TurmaId.generate())
                .descricao(cmd.getDescricao())
                .anoLetivo(cmd.getAnoLetivo())
                .periodoLetivo(cmd.getPeriodoLetivo())
                .numeroVagas(cmd.getNumeroVagas())
                .disciplinas(cmd.getDisciplinas())
                .alunos(cmd.getAlunos())
                .build();

        //turmaDomainRepository.insert(turma) insere banco se tiver;

        return turma;
    }

}

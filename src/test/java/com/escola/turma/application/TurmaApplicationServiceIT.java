package com.escola.turma.application;

import com.escola.config.Config;
import com.escola.core.disciplina.domain.model.DisciplinaId;
import com.escola.core.model.PessoaId;
import com.escola.core.turma.application.TurmaApplicationService;
import com.escola.core.turma.application.commands.CriarTurmaCommand;
import com.escola.core.turma.domain.events.TurmaCriadaEvent;
import com.escola.util.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@DisplayName("Turma - Processamento comandos application service ")
class TurmaApplicationServiceIT extends Config {

    @Autowired
    private TurmaApplicationService service;

    @Test
    @DisplayName("Deve processar comando para criar turma no banco de dados e disparar evento")
    void deveCriarTurma() {

        // given
        var cmd = CriarTurmaCommand.builder()
                .descricao("desc")
                .alunos(Set.of(PessoaId.generate()))
                .disciplinas(Set.of(DisciplinaId.generate()))
                .periodoLetivo(1)
                .anoLetivo(1)
                .numeroVagas(1)
                .build();


        // when
        var id = service.handle(cmd);

        TestUtils.assertThatEventHasBeenDispatch(id.getEvents().stream().collect(Collectors.toList()), Map.of(TurmaCriadaEvent.class, 1));

    }
}

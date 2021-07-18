package com.escola.disciplina.application;

import com.escola.config.Config;
import com.escola.core.disciplina.api.dto.CriarDisciplinaDTO;
import com.escola.core.disciplina.application.DisciplinaApplicationService;
import com.escola.core.disciplina.application.commands.CriarDisciplinaCommand;
import com.escola.core.disciplina.domain.events.DisciplinaCriadaEvent;
import com.escola.util.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.escola.core.disciplina.api.DisciplinaController.professores;


@DisplayName("Disciplina - Processamento comandos application service ")
class DisciplinaApplicationServiceIT extends Config {

    @Autowired
    private DisciplinaApplicationService service;

    final Set<CriarDisciplinaDTO.CriarDisciplinaProfessorDTO> professores = new HashSet<>();

    @Test
    @DisplayName("Deve processar comando para criar disciplina no banco de dados e disparar evento")
    void deveCriarDisciplina() {

        // given
        var cmd = CriarDisciplinaCommand.of("nome", "email", 10, professores(professores));

        // when
        var id = service.handle(cmd);


        TestUtils.assertThatEventHasBeenDispatch(id.getEvents().stream().collect(Collectors.toList()), Map.of(DisciplinaCriadaEvent.class, 1));

    }
}

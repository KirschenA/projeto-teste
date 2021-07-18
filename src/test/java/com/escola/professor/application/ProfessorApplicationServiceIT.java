package com.escola.professor.application;

import com.escola.config.Config;
import com.escola.core.professor.application.ProfessorApplicationService;
import com.escola.core.professor.application.commands.CriarProfessorCommand;
import com.escola.core.professor.domain.events.ProfessorCriadoEvent;
import com.escola.util.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;

@DisplayName("Professor - Processamento comandos application service ")
class ProfessorApplicationServiceIT extends Config {

    @Autowired
    private ProfessorApplicationService service;

    @Test
    @DisplayName("Deve processar comando para criar professor no banco de dados e disparar evento")
    void deveCriarProfessor() {

        // given
        var cmd = CriarProfessorCommand.of("nome", "email", "cpf", "professor");

        // when
        var id = service.handle(cmd);

        TestUtils.assertThatEventHasBeenDispatch(id.getEvents().stream().collect(Collectors.toList()), Map.of(ProfessorCriadoEvent.class, 1));
    }
}

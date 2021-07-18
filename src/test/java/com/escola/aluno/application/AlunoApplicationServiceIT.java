package com.escola.aluno.application;

import com.escola.config.Config;
import com.escola.core.aluno.application.AlunoApplicationService;
import com.escola.core.aluno.application.commands.CriarAlunoCommand;
import com.escola.core.aluno.domain.events.AlunoCriadoEvent;
import com.escola.util.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;

@DisplayName("Aluno - Processamento comandos application service ")
class AlunoApplicationServiceIT extends Config {

	@Autowired
	private AlunoApplicationService service;

	@Test
	@DisplayName("Deve processar comando para criar aluno no banco de dados e disparar evento")
	void deveCriarAluno() {

		// given
		var cmd = CriarAlunoCommand
				.builder()
				.nome("nome")
				.email("email")
				.cpf("cpf")
				.matricula(123)
				.formaIngresso("formaIngresso")
				.build();

		// when
		var id = service.handle(cmd);

		TestUtils.assertThatEventHasBeenDispatch(id.getEvents().stream().collect(Collectors.toList()), Map.of(AlunoCriadoEvent.class, 1));

	}
}

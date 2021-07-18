package com.escola.professor.domain.model;

import com.escola.core.model.PessoaId;
import com.escola.core.professor.domain.events.ProfessorCriadoEvent;
import com.escola.core.professor.domain.model.Professor;
import com.escola.professor.ProfessorTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static com.escola.professor.domain.asserts.ProfessorCriadoEventAssert.assertThatEvent;
import static com.escola.util.TestUtils.getEvent;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Aluno - Domínio.")
class ProfessorTest {

	@Test
	@DisplayName("Deve buildar com sucesso uma instância.")
	void deveBuildarAcessorio() {

		// given
		//CriarAlunoCommand.of("nome", "email", "cpf", 123, "formaIngresso");
		var id = ProfessorTestFactory.umPessoaId();
		var nome = "nome";
		var email = "email";
		var cpf = "cpf";
		var titulacao = "titulacao";
	
		var professor = Professor.builder().id(id).nome(nome).email(email).cpf(cpf).titulacao(titulacao).build();
		
		// then
		assertNotNull(professor);
		assertEquals(id, professor.getId());
		assertEquals(nome, professor.getNome());
		assertEquals(email, professor.getEmail());
		assertEquals(cpf, professor.getCpf());
		assertEquals(titulacao, professor.getTitulacao());

		assertEquals(1, professor.getEvents().size());
		var event = getEvent(professor.getEvents(), ProfessorCriadoEvent.class);
		assertThatEvent(event).hasInformationAccordingTo(professor);
	}
	
	@Nested
	@DisplayName("Teste de falha de criação de uma instância.")
	class CaminhoInfelizCriacao {
		@Test
		@DisplayName("Não deve criar uma instância válida quando id não for informado.")
		void naoDeveBuildarProfessorSemId() {

			// when
			assertThrows(NullPointerException.class, () -> Professor.builder().
					id(PessoaId.from(null))
					.nome("nome")
					.cpf("112313")
					.build());

			assertThrows(NullPointerException.class, () -> Professor.builder()
					.nome("nome")
					.cpf("112313")
					.build());

			Executable action = () -> ProfessorTestFactory.umProfessorBuilder().id(null).build();
			
			// then
			assertThrows(NullPointerException.class, action);
		}

		@Test
		@DisplayName("Não deve criar uma instância válida quando nome não for informado.")
		void naoDeveBuildarProfessorSemNome() {

			// then
			assertThrows(NullPointerException.class, () -> Professor.builder().
					id(ProfessorTestFactory.umPessoaId())
					.cpf("112313")
					.build());

			assertThrows(NullPointerException.class, () -> Professor.builder()
					.id(ProfessorTestFactory.umPessoaId())
					.nome(null)
					.cpf("112313")
					.build());
		}

		@Test
		@DisplayName("Não deve criar uma instância válida quando cpf não for informado.")
		void naoDeveBuildarProfessorSemCPF() {

			// then
			assertThrows(NullPointerException.class, () -> Professor.builder()
					.id(ProfessorTestFactory.umPessoaId())
					.nome("nome")
					.build());

			assertThrows(NullPointerException.class, () -> Professor.builder()
					.id(ProfessorTestFactory.umPessoaId())
					.nome("nome")
					.cpf(null)
					.build());
		}
	}
}
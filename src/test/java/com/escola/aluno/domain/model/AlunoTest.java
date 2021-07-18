package com.escola.aluno.domain.model;

import com.escola.aluno.AlunoTestFactory;
import com.escola.core.aluno.domain.events.AlunoCriadoEvent;
import com.escola.core.aluno.domain.model.Aluno;
import com.escola.core.model.PessoaId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static com.escola.aluno.domain.asserts.AlunoCriadoEventAssert.assertThatEvent;
import static com.escola.util.TestUtils.getEvent;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Aluno - Domínio.")
class AlunoTest {

    @Test
    @DisplayName("Deve buildar com sucesso uma instância.")
    void deveBuildarAluno() {

        // given
        //CriarAlunoCommand.of("nome", "email", "cpf", 123, "formaIngresso");
        var id = AlunoTestFactory.umPessoaId();
        var nome = "nome";
        var email = "email";
        var cpf = "cpf";
        var matricula = 123;
        var formaIngresso = "formaIngresso";

        var aluno = Aluno.builder()
                .id(id)
                .nome(nome)
                .email(email)
                .cpf(cpf)
                .matricula(matricula)
                .formaIngresso(formaIngresso)
                .build();

        // then
        assertNotNull(aluno);
        assertEquals(id, aluno.getId());
        assertEquals(nome, aluno.getNome());
        assertEquals(email, aluno.getEmail());
        assertEquals(cpf, aluno.getCpf());
        assertEquals(matricula, aluno.getMatricula());
        assertEquals(formaIngresso, aluno.getFormaIngresso());

        assertEquals(1, aluno.getEvents().size());
        var event = getEvent(aluno.getEvents(), AlunoCriadoEvent.class);
        assertThatEvent(event).hasInformationAccordingTo(aluno);
    }



    @Nested
    @DisplayName("Teste de falha de criação de uma instância.")
    class CaminhoInfelizCriacao {
        @Test
        @DisplayName("Não deve criar uma instância válida quando id não for informado.")
        void naoDeveBuildarAlunoSemId() {

            // when
            assertThrows(NullPointerException.class, () -> Aluno.builder().id(PessoaId.from(null)).build());
            assertThrows(NullPointerException.class, () -> Aluno.builder().nome("nome aluno").build());
            Executable action = () -> AlunoTestFactory.umAlunoBuilder().id(null).build();

            // then
            assertThrows(NullPointerException.class, action);
        }

        @Test
        @DisplayName("Não deve criar uma instância válida quando nome não for informado.")
        void naoDeveBuildarAlunoSemNome() {

            // then
            assertThrows(NullPointerException.class, () -> Aluno.builder().id(AlunoTestFactory.umPessoaId()).cpf("123")
                    .matricula(123).build());
            assertThrows(NullPointerException.class, () -> Aluno.builder().id(AlunoTestFactory.umPessoaId()).cpf("123")
                    .matricula(123).nome(null).build());
        }

        @Test
        @DisplayName("Não deve criar uma instância válida quando cpf não for informado.")
        void naoDeveBuildarAlunoSemCPF() {

            // then
            assertThrows(NullPointerException.class, () -> Aluno.builder().id(AlunoTestFactory.umPessoaId()).nome("nome")
                    .matricula(123).build());
            assertThrows(NullPointerException.class, () -> Aluno.builder().id(AlunoTestFactory.umPessoaId()).nome("nome")
                    .matricula(123).cpf(null).build());
        }

        @Test
        @DisplayName("Não deve criar uma instância válida quando matricula não for informada.")
        void naoDeveBuildarAlunoSemMatricula() {

            // then
            assertThrows(NullPointerException.class, () -> Aluno.builder().id(AlunoTestFactory.umPessoaId()).nome("nome")
                    .cpf("123").build());
            assertThrows(NullPointerException.class, () -> Aluno.builder().id(AlunoTestFactory.umPessoaId()).nome("nome")
                    .cpf("123").matricula(null).build());
        }
    }
}
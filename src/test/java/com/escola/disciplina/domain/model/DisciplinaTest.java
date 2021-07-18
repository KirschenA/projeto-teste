package com.escola.disciplina.domain.model;

import com.escola.core.disciplina.domain.events.DisciplinaCriadaEvent;
import com.escola.core.disciplina.domain.model.Disciplina;
import com.escola.core.disciplina.domain.model.DisciplinaId;
import com.escola.core.model.PessoaId;
import com.escola.disciplina.DisciplinaTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Set;

import static com.escola.disciplina.domain.asserts.DisciplinaCriadaEventAssert.assertThatEvent;
import static com.escola.util.TestUtils.getEvent;
import static com.escola.util.Utils.unmodifiableNullSafe;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Disciplina - Domínio.")
class DisciplinaTest {

    @Test
    @DisplayName("Deve buildar com sucesso uma instância.")
    void deveBuildarDisciplina() {

        // given
        //CriarAlunoCommand.of("nome", "email", "cpf", 123, "formaIngresso");
        var id = DisciplinaTestFactory.umDisciplinaId();
        var descricao = "desc";
        var sigla = "sigla";
        var cargaHoraria = 0;
        var professores = Set.of(PessoaId.generate());

        var disciplina = Disciplina.builder()
                .id(id)
                .descricao(descricao)
                .sigla(sigla)
                .cargaHoraria(cargaHoraria)
                .professores(unmodifiableNullSafe(professores))
                .build();

        // then
        assertNotNull(disciplina);
        assertEquals(id, disciplina.getId());
        assertEquals(descricao, disciplina.getDescricao());
        assertEquals(sigla, disciplina.getSigla());
        assertEquals(cargaHoraria, disciplina.getCargaHoraria());
        assertEquals(professores, disciplina.getProfessores());

        assertEquals(1, disciplina.getEvents().size());
        var event = getEvent(disciplina.getEvents(), DisciplinaCriadaEvent.class);
        assertThatEvent(event).hasInformationAccordingTo(disciplina);
    }

    @Nested
    @DisplayName("Teste de falha de criação de uma instância.")
    class CaminhoInfelizCriacao {
        @Test
        @DisplayName("Não deve criar uma instância válida quando id não for informado.")
        void naoDeveBuildarDisciplinaSemId() {

            // when
            assertThrows(NullPointerException.class, () -> Disciplina.builder().id(DisciplinaId.from(null)).build());
            assertThrows(NullPointerException.class, () -> Disciplina.builder().sigla("sigla").build());
            Executable action = () -> DisciplinaTestFactory.umDisciplinaBuilder().id(null).build();

            // then
            assertThrows(NullPointerException.class, action);
        }

        @Test
        @DisplayName("Não deve criar uma instância válida quando sigla não for informada.")
        void naoDeveBuildarDisciplinaSemSigla() {

            // when
            assertThrows(NullPointerException.class, () -> Disciplina.builder()
                    .id(DisciplinaTestFactory.umDisciplinaId())
                    .build());

            assertThrows(NullPointerException.class, () -> Disciplina.builder()
                    .id(DisciplinaTestFactory.umDisciplinaId())
                    .sigla(null)
                    .build());
        }
    }
}
package com.escola.turma.domain.model;

import com.escola.core.disciplina.domain.model.DisciplinaId;
import com.escola.core.model.PessoaId;
import com.escola.core.turma.domain.events.TurmaCriadaEvent;
import com.escola.core.turma.domain.model.Turma;
import com.escola.core.turma.domain.model.TurmaId;
import com.escola.turma.TurmaTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.HashSet;


import static com.escola.turma.domain.asserts.TurmaCriadaEventAssert.assertThatEvent;
import static com.escola.util.TestUtils.getEvent;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Aluno - Domínio.")
class TurmaTest {

    @Test
    @DisplayName("Deve buildar com sucesso uma instância.")
    void deveBuildarTurma() {

        // given
        var id = TurmaTestFactory.umTurmaId();
        var descricao = "desc";
        var anoLetivo = 1;
        var periodoLetivo = 1;
        var numeroVagas = 1;
        var disciplinas = new HashSet<DisciplinaId>();
        var alunos = new HashSet<PessoaId>();

        var turma = Turma.builder()
                .id(id)
                .descricao(descricao)
                .anoLetivo(anoLetivo)
                .periodoLetivo(periodoLetivo)
                .numeroVagas(numeroVagas)
                .disciplinas(disciplinas)
                .alunos(alunos)
                .build();

        // then
        assertNotNull(turma);
        assertEquals(id, turma.getId());
        assertEquals(descricao, turma.getDescricao());
        assertEquals(anoLetivo, turma.getAnoLetivo());
        assertEquals(periodoLetivo, turma.getPeriodoLetivo());
        assertEquals(numeroVagas, turma.getNumeroVagas());
        assertEquals(disciplinas, turma.getDisciplinas());
        assertEquals(alunos, turma.getAlunos());

        assertEquals(1, turma.getEvents().size());
        var event = getEvent(turma.getEvents(), TurmaCriadaEvent.class);
        assertThatEvent(event).hasInformationAccordingTo(turma);
    }

    @Nested
    @DisplayName("Teste de falha de criação de uma instância.")
    class CaminhoInfelizCriacao {
        @Test
        @DisplayName("Não deve criar uma instância válida quando id não for informado.")
        void naoDeveBuildarTurmaSemId() {

            // when
            assertThrows(NullPointerException.class, () -> Turma.builder().id(TurmaId.from(null)).build());
            assertThrows(NullPointerException.class, () -> Turma.builder().numeroVagas(0).build());
            Executable action = () -> TurmaTestFactory.umaTurmaBuilder().id(null).build();

            // then
            assertThrows(NullPointerException.class, action);
        }

        @Test
        @DisplayName("Não deve criar uma instância válida quando descrição não for informado.")
        void naoDeveBuildarTurmaSemDesc() {

            // when
            assertThrows(NullPointerException.class, () -> Turma.builder().id(TurmaId.generate()).descricao(null).build());
            assertThrows(NullPointerException.class, () -> Turma.builder().id(TurmaId.generate()).build());
            Executable action = () -> TurmaTestFactory.umaTurmaBuilder().id(TurmaId.generate()).descricao(null).build();

            // then'
            assertThrows(NullPointerException.class, action);
        }
    }
}
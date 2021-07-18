package com.escola.turma.api;

import com.escola.config.Config;
import com.escola.core.disciplina.domain.model.DisciplinaId;
import com.escola.core.model.PessoaId;
import com.escola.core.turma.api.TurmaController;
import com.escola.core.turma.api.dto.CriarTurmaDTO;
import com.escola.core.turma.application.commands.CriarTurmaCommand;
import com.escola.util.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Turma - Recebimento solicitação REST API.")
class TurmaControllerIT extends Config {

    @Autowired
    private MockMvc mock;

    private final String disciplinaId = DisciplinaId.generate().toString();
    private final String alunoId = PessoaId.generate().toString();

    @Test
    @DisplayName("Deve receber solicitação para criar Turma e disparar comando")
    void deveReceberSolicitacaoEDispararComandoParaCriarTurma() throws Exception {

        // given
        var dto = CriarTurmaDTO.builder()
                .descricao("descrição")
                .anoLetivo(1)
                .periodoLetivo(1)
                .numeroVagas(1)
                .alunos(Set.of(CriarTurmaDTO.CriarTurmaAlunoDTO.of(alunoId)))
                .disciplinas(Set.of(CriarTurmaDTO.CriarTurmaDisciplinaDTO.of(disciplinaId)))
                .build();

        var cmdEsperado = CriarTurmaCommand.builder()
                .descricao(dto.getDescricao())
                .anoLetivo(dto.getAnoLetivo())
                .periodoLetivo(dto.getPeriodoLetivo())
                .numeroVagas(dto.getNumeroVagas())
                .alunos(Set.of(PessoaId.from(alunoId)))
                .disciplinas(Set.of(DisciplinaId.from(disciplinaId)))
                .build();

        Mockito.when(turmaApplicationService.handle(cmdEsperado));

        // when
        mock.perform(request(HttpMethod.POST, TurmaController.PATH)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Utils.objectToJson(dto)))
                .andExpect(status().is2xxSuccessful());

        // then
        Mockito.verify(turmaApplicationService).handle(cmdEsperado);
    }

    public static Set<PessoaId> getAlunos(Set<CriarTurmaDTO.CriarTurmaAlunoDTO> alunos) {
        return alunos.stream()
                .map(alunoDTO -> PessoaId.from(alunoDTO.getId()))
                .collect(Collectors.toSet());
    }

    public static Set<DisciplinaId> getDisciplinas(Set<CriarTurmaDTO.CriarTurmaDisciplinaDTO> disciplinas) {
        return disciplinas.stream()
                .map(disciplinaDTO -> DisciplinaId.from(disciplinaDTO.getId()))
                .collect(Collectors.toSet());
    }

    @Nested
    @DisplayName("Teste de falha de criação.")
    class CaminhoInfelizCriacao {

        @Test
        @DisplayName("Não deve processar solicitação para criar Turma sem informar descricao")
        void naoDeveReceberSolicitacaoCriacaoSemNome() throws Exception {

            // given
            var dto = CriarTurmaDTO.builder()
                    .descricao(null)
                    .disciplinas(new HashSet<>())
                    .alunos(new HashSet<>())
                    .anoLetivo(0)
                    .numeroVagas(0)
                    .periodoLetivo(0)
                    .build();


            var violations = List.of("CriarTurmaDTO.descricao.NotNull");

            // when
            var result = getRequestResult(dto);

            // then
            assertThat(result.getResponse().getContentAsString()).contains(violations);
        }

        private MvcResult getRequestResult(CriarTurmaDTO dto) throws Exception {
            return mock.perform(request(HttpMethod.POST,
                    TurmaController.PATH)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(Utils.objectToJson(dto)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.details.length()", is(1)))
                    .andReturn();
        }
    }
}

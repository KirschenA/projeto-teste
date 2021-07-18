package com.escola.disciplina.api;

import com.escola.config.Config;
import com.escola.core.disciplina.api.DisciplinaController;
import com.escola.core.disciplina.api.dto.CriarDisciplinaDTO;
import com.escola.core.disciplina.application.commands.CriarDisciplinaCommand;
import com.escola.core.disciplina.domain.model.DisciplinaId;
import com.escola.core.model.PessoaId;
import com.escola.util.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.escola.core.disciplina.api.DisciplinaController.professores;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Disciplina - Recebimento solicitação REST API.")
class DisciplinaControllerIT extends Config {

    @Autowired
    private MockMvc mock;

    final Set<CriarDisciplinaDTO.CriarDisciplinaProfessorDTO> professores = new HashSet<>();
    final String professorId = PessoaId.generate().toString();

    @Test
    @DisplayName("Deve receber solicitação para criar disciplina e disparar comando")
    void deveReceberSolicitacaoEDispararComandoParaCriarDisciplina() throws Exception {

        var professor = CriarDisciplinaDTO.CriarDisciplinaProfessorDTO.from(professorId);

        professores.add(professor);

        // given
        var dto = CriarDisciplinaDTO.of("desc", "sigla", 10, professores);

        var cmdEsperado = CriarDisciplinaCommand.of(
                dto.getDescricao(),
                dto.getSigla(),
                dto.getCargaHoraria(),
                professores(dto.getProfessores()));

        Mockito.when(disciplinaApplicationService.handle(cmdEsperado));

        // when
        mock.perform(request(HttpMethod.POST, DisciplinaController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Utils.objectToJson(dto)))
                .andExpect(status().is2xxSuccessful());

        // then
        Mockito.verify(disciplinaApplicationService).handle(cmdEsperado);
    }

    @Test
    @DisplayName("Deve receber solicitação para criar disciplina e disparar comando com lsita professores nula")
    void deveReceberSolicitacaoEDispararComandoParaCriarDisciplinaComListaProfessoresNull() throws Exception {

        // given
        var dto = CriarDisciplinaDTO.of("desc", "sigla", 10, null);

        var cmdEsperado = CriarDisciplinaCommand.of(dto.getDescricao(), dto.getSigla(), dto.getCargaHoraria(), professores(null));

        Mockito.when(disciplinaApplicationService.handle(cmdEsperado));

        // when
        mock.perform(request(HttpMethod.POST, DisciplinaController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Utils.objectToJson(dto)))
                .andExpect(status().is2xxSuccessful());

        // then
        Mockito.verify(disciplinaApplicationService).handle(cmdEsperado);
    }

    @Nested
    @DisplayName("Teste de falha de criação.")
    class CaminhoInfelizCriacao {

        @Test
        @DisplayName("Não deve processar solicitação para criar disciplina sem informar sigla")
        void naoDeveReceberSolicitacaoCriacaoSemSigla() throws Exception {

            // given
            var dto = CriarDisciplinaDTO.of("desc", null, 10, professores);

            var violations = List.of("CriarDisciplinaDTO.sigla.NotNull");

            // when
            var result = mock.perform(request(HttpMethod.POST,
                    DisciplinaController.PATH)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(Utils.objectToJson(dto)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.details.length()", is(1)))
                    .andReturn();

            // then
            assertThat(result.getResponse().getContentAsString()).contains(violations);
        }
    }
}

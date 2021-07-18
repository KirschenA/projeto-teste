package com.escola.professor.api;

import com.escola.config.Config;
import com.escola.core.professor.api.ProfessorController;
import com.escola.core.professor.api.dto.CriarProfessorDTO;
import com.escola.core.professor.application.commands.CriarProfessorCommand;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Professor - Recebimento solicitação REST API.")
class ProfessorControllerIT extends Config {

    @Autowired
    private MockMvc mock;

    @Test
    @DisplayName("Deve receber solicitação para criar professor e disparar comando")
    void deveReceberSolicitacaoEDispararComandoParaCriarProfessor() throws Exception {

        // given
        var dto = CriarProfessorDTO.of("nome", "email", "cpf", "titulacao");

        var cmdEsperado = CriarProfessorCommand.of(dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getTitulacao());

        Mockito.when(professorApplicationService.handle(cmdEsperado));

        // when
        mock.perform(request(HttpMethod.POST, ProfessorController.PATH)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Utils.objectToJson(dto)))
                .andExpect(status().is2xxSuccessful());

        // then
        Mockito.verify(professorApplicationService).handle(cmdEsperado);
    }

    @Nested
    @DisplayName("Teste de falha de criação.")
    class CaminhoInfelizCriacao {

        @Test
        @DisplayName("Não deve processar solicitação para criar professor sem informar nome")
        void naoDeveReceberSolicitacaoCriacaoSemNome() throws Exception {

            // given
            var dto = CriarProfessorDTO.of(null, "email", "21312312", "titulacao");

            var violations = List.of("CriarProfessorDTO.nome.NotNull");

            // when
            var result = getRequestResult(dto);

            // then
            assertThat(result.getResponse().getContentAsString()).contains(violations);
        }

        @Test
        @DisplayName("Não deve processar solicitação para criar professor sem informar cpf")
        void naoDeveReceberSolicitacaoCriacaoSemCPF() throws Exception {

            // given
            var dto = CriarProfessorDTO.of("nome", "email", null, "titulacao");

            var violations = List.of("CriarProfessorDTO.cpf.NotNull");

            // when
            var result = getRequestResult(dto);

            // then
            assertThat(result.getResponse().getContentAsString()).contains(violations);
        }

        private MvcResult getRequestResult(CriarProfessorDTO dto) throws Exception {
            return mock.perform(request(HttpMethod.POST,
                    ProfessorController.PATH)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(Utils.objectToJson(dto)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.details.length()", is(1)))
                    .andReturn();
        }
    }
}

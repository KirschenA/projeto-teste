package com.escola.aluno.api;

import com.escola.config.Config;
import com.escola.core.aluno.api.AlunoController;
import com.escola.core.aluno.api.dto.CriarAlunoDTO;
import com.escola.core.aluno.application.commands.CriarAlunoCommand;
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

@DisplayName("Aluno - Recebimento solicitação REST API.")
class AlunoControllerIT extends Config {

    @Autowired
    private MockMvc mock;

    @Test
    @DisplayName("Deve receber solicitação para criar aluno e disparar comando")
    void deveReceberSolicitacaoEDispararComandoParaCriarAluno() throws Exception {

        // given
        var dto = CriarAlunoDTO
                .builder()
                .nome("nome")
                .email("email")
                .cpf("cpf")
                .matricula(123)
                .formaIngresso("formaIngresso")
                .build();

        var cmdEsperado = CriarAlunoCommand
                .builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .matricula(dto.getMatricula())
                .formaIngresso(dto.getFormaIngresso())
                .build();

        Mockito.when(alunoApplicationService.handle(cmdEsperado));

        // when
        mock.perform(request(HttpMethod.POST, AlunoController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(Utils.objectToJson(dto)))

                .andExpect(status().is2xxSuccessful());

        // then
        Mockito.verify(alunoApplicationService).handle(cmdEsperado);
    }

    @Nested
    @DisplayName("Teste de falha de criação.")
    class CaminhoInfelizCriacao {

        @Test
        @DisplayName("Não deve processar solicitação para criar acessório sem informar nome")
        void naoDeveReceberSolicitacaoCriacaoSemNome() throws Exception {

            // given
            var dto = CriarAlunoDTO.
                    builder()
                    .nome(null)
                    .email("email")
                    .cpf("cpf")
                    .matricula(123)
                    .formaIngresso("formaIngresso")
                    .build();

            var violations = List.of("CriarAlunoDTO.nome.NotNull");

            // when
            var result = getRequestResult(dto);

            // then
            assertThat(result.getResponse().getContentAsString()).contains(violations);
        }

        @Test
        @DisplayName("Não deve processar solicitação para criar acessório sem informar cpf")
        void naoDeveReceberSolicitacaoCriacaoSemCpf() throws Exception {

            // given
            var dto = CriarAlunoDTO
                    .builder()
                    .nome("nome")
                    .email("email")
                    .cpf(null)
                    .matricula(123)
                    .formaIngresso("formaIngresso")
                    .build();

            var violations = List.of("CriarAlunoDTO.cpf.NotNull");

            // when
            var result = getRequestResult(dto);

            // then
            assertThat(result.getResponse().getContentAsString()).contains(violations);
        }

        @Test
        @DisplayName("Não deve processar solicitação para criar acessório sem informar matricula")
        void naoDeveReceberSolicitacaoCriacaoSemMatricula() throws Exception {

            // given
            var dto = CriarAlunoDTO
                    .builder()
                    .nome("nome")
                    .email("email")
                    .cpf("cpf")
                    .matricula(null)
                    .formaIngresso("formaIngresso")
                    .build();

            var violations = List.of("CriarAlunoDTO.matricula.NotNull");

            // when
            var result = getRequestResult(dto);

            // then
            assertThat(result.getResponse().getContentAsString()).contains(violations);
        }


        private MvcResult getRequestResult(CriarAlunoDTO dto) throws Exception {
            return mock.perform(request(HttpMethod.POST,
                    AlunoController.PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(Utils.objectToJson(dto)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.details.length()", is(1)))
                    .andReturn();
        }
    }
}

package com.escola.core.turma.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@EqualsAndHashCode
@ApiModel(description = "Informações para criação de uma nova turma.")
public final class CriarTurmaDTO {

    @ApiModelProperty(value = "Descrição da turma.", required = true)
    @NotNull(message = "{CriarTurmaDTO.descricao.NotNull}")
    private final String descricao;

    @ApiModelProperty(value = "Ano letivo da turma.", required = false)
    private final Integer anoLetivo;

    @ApiModelProperty(value = "Periodo letivo da turma.", required = false)
    private final Integer periodoLetivo;

    @ApiModelProperty(value = "Quantidade de vagas da turma.", required = false)
    private final Integer numeroVagas;

    @ApiModelProperty(value = "Lista Disciplinas da turma.", required = false)
    private final Set<CriarTurmaDisciplinaDTO> disciplinas;

    @ApiModelProperty(value = "Lista Alunos da turma.", required = false)
    private final Set<CriarTurmaAlunoDTO> alunos;


    @Getter
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(force = true)
    @ApiModel(description = "aluno.")
    public static final class CriarTurmaAlunoDTO {

        @ApiModelProperty(value = "ID do aluno.", required = false)
        private final String id;

    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(force = true)
    @ApiModel(description = "Informações para criação de uma nova disciplina da turma.")
    public static final class CriarTurmaDisciplinaDTO {

        @ApiModelProperty(value = "ID da disciplina.", required = false)
        private final String id;
    }

}
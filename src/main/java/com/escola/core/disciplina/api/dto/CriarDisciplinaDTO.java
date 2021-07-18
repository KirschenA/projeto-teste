package com.escola.core.disciplina.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@EqualsAndHashCode
@ApiModel(description = "Informações para criação de uma nova disciplina.")
public final class CriarDisciplinaDTO {

    @ApiModelProperty(value = "Descrição da disciplina.", required = false)
    private final String descricao;

    @ApiModelProperty(value = "Sigla da disciplina.", required = true)
    @NotNull(message = "{CriarDisciplinaDTO.sigla.NotNull}")
    private final String sigla;

    @ApiModelProperty(value = "Carga horaria da disciplina.", required = false)
    private final Integer cargaHoraria;

    @ApiModelProperty(value = "Professores da disciplina.", required = false)
    private final Set<CriarDisciplinaProfessorDTO> professores;

    @Getter
    @AllArgsConstructor(staticName = "from")
    @NoArgsConstructor(force = true)
    @ApiModel(description = "Professor.")
    public static final class CriarDisciplinaProfessorDTO {

        @ApiModelProperty(value = "ID do professor.", required = false)
        private final String id;
    }

}
package com.escola.core.aluno.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@EqualsAndHashCode
@Builder
@ApiModel(description = "Informações para cadastro de um novo aluno.")
public final class CriarAlunoDTO {

    @ApiModelProperty(value="Nome do aluno", required = true)
    @NotNull(message = "{CriarAlunoDTO.nome.NotNull}")
    private final String nome;

    @ApiModelProperty(value="Email do aluno", required = false)
    private final String email;

    @ApiModelProperty(value="cpf do aluno", required = true)
    @NotNull(message = "{CriarAlunoDTO.cpf.NotNull}")
    private final String cpf;

    @ApiModelProperty(value="Matricula do aluno", required = true)
    @NotNull(message = "{CriarAlunoDTO.matricula.NotNull}")
    private final Integer matricula;

    @ApiModelProperty(value="Forma de ingresso do aluno", required = false)
    private final String formaIngresso;
}
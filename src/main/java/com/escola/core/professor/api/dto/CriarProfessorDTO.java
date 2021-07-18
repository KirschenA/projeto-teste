package com.escola.core.professor.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ApiModel(description = "Informações para cadastro de um novo professor.")
public final class CriarProfessorDTO {

    @ApiModelProperty(value="Nome do professor", required = true)
    @NotNull(message = "{CriarProfessorDTO.nome.NotNull}")
    private final String nome;

    @ApiModelProperty(value="Email do professor", required = false)
    private final String email;

    @ApiModelProperty(value="cpf do professor", required = true)
    @NotNull(message = "{CriarProfessorDTO.cpf.NotNull}")
    private final String cpf;

    @ApiModelProperty(value="Titulação do professor", required = false)
    private final String titulacao;
}
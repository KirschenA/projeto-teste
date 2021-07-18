package com.escola.core.aluno.application.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CriarAlunoCommand {
    private final String nome;
    private final String email;
    private final String cpf;
    private final Integer matricula;
    private final String formaIngresso;
}

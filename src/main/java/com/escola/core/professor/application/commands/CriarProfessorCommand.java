package com.escola.core.professor.application.commands;

import lombok.Data;

@Data(staticConstructor = "of")
public class CriarProfessorCommand {
    private final String nome;
    private final String email;
    private final String cpf;
    private final String titulacao;
}

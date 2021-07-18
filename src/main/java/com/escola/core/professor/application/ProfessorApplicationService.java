package com.escola.core.professor.application;

import com.escola.core.model.PessoaId;
import com.escola.core.professor.application.commands.CriarProfessorCommand;
import com.escola.core.professor.domain.model.Professor;
import com.escola.core.professor.domain.model.ProfessorDomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfessorApplicationService {

    private final ProfessorDomainRepository repository;

    public Professor handle(final CriarProfessorCommand cmd) {
        var professor = Professor.builder().id(PessoaId.generate())
                .nome(cmd.getNome())
                .email(cmd.getEmail())
                .cpf(cmd.getCpf())
                .titulacao(cmd.getTitulacao())
                .build();

        //repository.insert(professor) insere no banco;

        return professor;
    }

}
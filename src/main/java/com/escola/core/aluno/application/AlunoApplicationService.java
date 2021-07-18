package com.escola.core.aluno.application;

import com.escola.core.aluno.domain.model.AlunoDomainRepository;
import com.escola.core.model.PessoaId;
import com.escola.core.aluno.application.commands.CriarAlunoCommand;
import com.escola.core.aluno.domain.model.Aluno;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlunoApplicationService {

//    private final AlunoDomainRepository alunoDomainRepository;

    public Aluno handle(final CriarAlunoCommand cmd) {

        var aluno = Aluno.builder()
                .id(PessoaId.generate())
                .nome(cmd.getNome())
                .email(cmd.getEmail())
                .cpf(cmd.getCpf())
                .matricula(cmd.getMatricula())
                .formaIngresso(cmd.getFormaIngresso())
                .build();

        //alunoDomainRepository.insert(aluno) inserir no banco se houver um;

        return aluno;
    }

}
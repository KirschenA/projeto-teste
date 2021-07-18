package com.escola.turma;

import com.escola.core.turma.domain.model.Turma;
import com.escola.core.turma.domain.model.TurmaId;

import java.util.HashSet;

public class TurmaTestFactory {

    public static TurmaId umTurmaId() {
        return TurmaId.generate();
    }

    public static Turma umAluno() {
        return umaTurmaBuilder().build();
    }

    public static Turma.TurmaBuilder umaTurmaBuilder() {
        return Turma.builder()
                .id(umTurmaId())
                .alunos(new HashSet<>())
                .disciplinas(new HashSet<>())
                .anoLetivo(0).descricao("")
                .numeroVagas(0)
                .periodoLetivo(0);
    }

//    public static Turma persistir(TurmaRepository repository, Turma turma) {
//        repository.insert(turma);
//        return turma;
//    }
}

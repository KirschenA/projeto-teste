package com.escola.disciplina;

import com.escola.core.disciplina.domain.model.Disciplina;
import com.escola.core.disciplina.domain.model.DisciplinaId;

import java.util.HashSet;

public class DisciplinaTestFactory {

	public static DisciplinaId umDisciplinaId() {
		return DisciplinaId.generate();
	}

	public static Disciplina umaDisciplina() {
		return umDisciplinaBuilder().build();
	}

	public static Disciplina.DisciplinaBuilder umDisciplinaBuilder() {
		return Disciplina.builder().id(umDisciplinaId()).professores(new HashSet<>()).cargaHoraria(0).sigla("").descricao("");
	}
	
//	public static Disciplina persistir(DisciplinaRepository repository, Disciplina disciplina) {
//		repository.insert(disciplina);
//		return disciplina;
//	}
}

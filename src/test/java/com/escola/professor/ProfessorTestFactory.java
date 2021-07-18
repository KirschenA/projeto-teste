package com.escola.professor;

import com.escola.core.model.PessoaId;
import com.escola.core.professor.domain.model.Professor;

public class ProfessorTestFactory {

	public static PessoaId umPessoaId() {
		return PessoaId.generate();
	}

	public static Professor umAluno() {
		return umProfessorBuilder().build();
	}

	public static Professor.ProfessorBuilder umProfessorBuilder() {
		return Professor.builder().id(umPessoaId()).titulacao("").cpf("").email("").nome("");
	}
	
//	public static Professor persistir(ProfessorRepository repository, Professor professor) {
//		repository.insert(professor);
//		return professor;
//	}
}

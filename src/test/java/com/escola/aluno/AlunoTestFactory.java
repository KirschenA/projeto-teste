package com.escola.aluno;

import com.escola.core.aluno.domain.model.Aluno;
import com.escola.core.model.PessoaId;

public class AlunoTestFactory {

	public static PessoaId umPessoaId() {
		return PessoaId.generate();
	}

	public static Aluno umAluno() {
		return umAlunoBuilder().build();
	}

	public static Aluno.AlunoBuilder umAlunoBuilder() {
		return Aluno.builder().id(umPessoaId()).matricula(0).cpf("").email("").nome("").formaIngresso("");
	}

}

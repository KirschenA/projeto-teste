package com.escola.aluno.domain.asserts;

import com.escola.core.aluno.domain.events.AlunoCriadoEvent;
import com.escola.core.aluno.domain.model.Aluno;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class AlunoCriadoEventAssert extends AbstractAssert<AlunoCriadoEventAssert, AlunoCriadoEvent> {

	public AlunoCriadoEventAssert(AlunoCriadoEvent actual) {
		super(actual, AlunoCriadoEventAssert.class);
	}

	public AlunoCriadoEventAssert hasInformationAccordingTo(Aluno aluno) {
		isNotNull();

		Assertions.assertThat(actual.getId()).isEqualTo(aluno.getId().toString());
		Assertions.assertThat(actual.getNome()).isEqualTo(aluno.getNome());
		Assertions.assertThat(actual.getEmail()).isEqualTo(aluno.getEmail());
		Assertions.assertThat(actual.getCpf()).isEqualTo(aluno.getCpf());
		Assertions.assertThat(actual.getMatricula()).isEqualTo(aluno.getMatricula());

		return this;
	}

	public static AlunoCriadoEventAssert assertThatEvent(AlunoCriadoEvent actual) {
		return new AlunoCriadoEventAssert(actual);
	}
}
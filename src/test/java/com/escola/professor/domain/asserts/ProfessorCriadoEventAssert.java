package com.escola.professor.domain.asserts;

import com.escola.core.professor.domain.events.ProfessorCriadoEvent;
import com.escola.core.professor.domain.model.Professor;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ProfessorCriadoEventAssert extends AbstractAssert<ProfessorCriadoEventAssert, ProfessorCriadoEvent> {

	public ProfessorCriadoEventAssert(ProfessorCriadoEvent actual) {
		super(actual, ProfessorCriadoEventAssert.class);
	}

	public ProfessorCriadoEventAssert hasInformationAccordingTo(Professor professor) {
		isNotNull();

		Assertions.assertThat(actual.getId()).isEqualTo(professor.getId().toString());
		Assertions.assertThat(actual.getNome()).isEqualTo(professor.getNome().toString());
		Assertions.assertThat(actual.getEmail()).isEqualTo(professor.getEmail().toString());
		Assertions.assertThat(actual.getCpf()).isEqualTo(professor.getCpf().toString());
		Assertions.assertThat(actual.getTitulacao()).isEqualTo(professor.getTitulacao().toString());

		return this;
	}

	public static ProfessorCriadoEventAssert assertThatEvent(ProfessorCriadoEvent actual) {
		return new ProfessorCriadoEventAssert(actual);
	}
}
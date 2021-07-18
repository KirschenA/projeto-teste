package com.escola.turma.domain.asserts;

import com.escola.core.turma.domain.events.TurmaCriadaEvent;
import com.escola.core.turma.domain.model.Turma;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class TurmaCriadaEventAssert extends AbstractAssert<TurmaCriadaEventAssert, TurmaCriadaEvent> {

	public TurmaCriadaEventAssert(TurmaCriadaEvent actual) {
		super(actual, TurmaCriadaEventAssert.class);
	}

	public TurmaCriadaEventAssert hasInformationAccordingTo(Turma turma) {
		isNotNull();

		Assertions.assertThat(actual.getId()).isEqualTo(turma.getId().toString());
		Assertions.assertThat(actual.getDescricao()).isEqualTo(turma.getDescricao());
		Assertions.assertThat(actual.getAnoLetivo()).isEqualTo(turma.getAnoLetivo());
		Assertions.assertThat(actual.getNumeroVagas()).isEqualTo(turma.getNumeroVagas());
		Assertions.assertThat(actual.getAlunos()).isEqualTo(turma.getAlunos());
		Assertions.assertThat(actual.getDisciplinas()).isEqualTo(turma.getDisciplinas());

		return this;
	}

	public static TurmaCriadaEventAssert assertThatEvent(TurmaCriadaEvent actual) {
		return new TurmaCriadaEventAssert(actual);
	}
}
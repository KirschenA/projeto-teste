package com.escola.disciplina.domain.asserts;

import com.escola.core.disciplina.domain.events.DisciplinaCriadaEvent;
import com.escola.core.disciplina.domain.model.Disciplina;
import com.escola.core.model.PessoaId;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class DisciplinaCriadaEventAssert extends AbstractAssert<DisciplinaCriadaEventAssert, DisciplinaCriadaEvent> {

	public DisciplinaCriadaEventAssert(DisciplinaCriadaEvent actual) {
		super(actual, DisciplinaCriadaEventAssert.class);
	}

	public DisciplinaCriadaEventAssert hasInformationAccordingTo(Disciplina disciplina) {
		isNotNull();

		Assertions.assertThat(actual.getId()).isEqualTo(disciplina.getId().toString());
		Assertions.assertThat(actual.getSigla()).isEqualTo(disciplina.getSigla());
		Assertions.assertThat(actual.getDescricao()).isEqualTo(disciplina.getDescricao());
		Assertions.assertThat(actual.getCargaHoraria()).isEqualTo(disciplina.getCargaHoraria());
//		Assertions.assertThat(actual.getProfessores()).isEqualTo(disciplina.getProfessores());

		disciplina.getProfessores().forEach(professores -> {

			var professorEvent = actual.getProfessores()
					.stream()
					.filter(espec -> espec.getId().equals(professores.getId().toString()))
					.findFirst()
					.get();

			assertProfessorEquals(professores, professorEvent);

		});

		return this;
	}

	private void assertProfessorEquals(PessoaId professor,
									   DisciplinaCriadaEvent.DisciplinaProfessorEvent professorEvent) {
		assertThat(professorEvent.getId()).isEqualTo(professor.getId().toString());
	}

	public static DisciplinaCriadaEventAssert assertThatEvent(DisciplinaCriadaEvent actual) {
		return new DisciplinaCriadaEventAssert(actual);
	}
}
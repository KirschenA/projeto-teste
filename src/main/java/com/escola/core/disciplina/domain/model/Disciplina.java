package com.escola.core.disciplina.domain.model;

import com.escola.core.disciplina.domain.events.DisciplinaCriadaEvent;
import com.escola.core.model.PessoaId;
import com.escola.util.AbstractDomainEvent;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class Disciplina extends AbstractDomainEvent {

    private DisciplinaId id;
    private String descricao;
    private String sigla;
    private Integer cargaHoraria;
    private Collection<PessoaId> professores = new HashSet<>();

    @Builder
    public Disciplina(final @NonNull DisciplinaId id,
                      String descricao,
                      @NotNull String sigla,
                      Integer cargaHoraria,
                      Set<PessoaId> professores
    ) {

        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
        this.professores = professores;

        this.registerEvent(DisciplinaCriadaEvent.from(this));
    }

}

package com.escola.core.turma.api;

import com.escola.core.disciplina.domain.model.DisciplinaId;
import com.escola.core.model.PessoaId;
import com.escola.core.turma.api.dto.CriarTurmaDTO;
import com.escola.core.turma.application.TurmaApplicationService;
import com.escola.core.turma.application.commands.CriarTurmaCommand;
import com.escola.core.turma.domain.model.Turma;
import com.escola.core.turma.exception.TMSCriarTurmaConstraintException;
import com.escola.util.ValidateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping(path = TurmaController.PATH)
public class TurmaController {
    public static final String PATH = "/api/v1/turmas";

    private final TurmaApplicationService service;
    private final ValidateService validator;

    @ApiOperation(value = "Criar uma turma.", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Turma criada."),
            @ApiResponse(code = 400, message = "A Turma não pode ser criada porque está em um estado inválido.")})

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody CriarTurmaDTO dto) {

        validator.validate(dto).ifPresent(violations -> {
            throw new TMSCriarTurmaConstraintException(violations);
        });

        var cmd = CriarTurmaCommand.builder()
                .descricao(dto.getDescricao())
                .anoLetivo(dto.getAnoLetivo())
                .periodoLetivo(dto.getPeriodoLetivo())
                .numeroVagas(dto.getNumeroVagas())
                .alunos(getAlunos(dto.getAlunos()))
                .disciplinas(getDisciplinas(dto.getDisciplinas()))
                .build();

        Turma turma = service.handle(cmd);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(turma.getId().toString()).build().toUri();

        return ResponseEntity.created(uri).build();
    }

    public static Set<PessoaId> getAlunos(Set<CriarTurmaDTO.CriarTurmaAlunoDTO> alunos) {
        return alunos.stream()
                .map(alunoDTO -> PessoaId.from(alunoDTO.getId()))
                .collect(Collectors.toSet());
    }

    public static Set<DisciplinaId> getDisciplinas(Set<CriarTurmaDTO.CriarTurmaDisciplinaDTO> disciplinas) {
        return disciplinas.stream()
                .map(disciplinaDTO -> DisciplinaId.from(disciplinaDTO.getId()))
                .collect(Collectors.toSet());
    }

}

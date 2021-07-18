package com.escola.core.disciplina.api;

import com.escola.core.disciplina.api.dto.CriarDisciplinaDTO;
import com.escola.core.disciplina.application.DisciplinaApplicationService;
import com.escola.core.disciplina.application.commands.CriarDisciplinaCommand;
import com.escola.core.disciplina.domain.model.Disciplina;
import com.escola.core.disciplina.exception.CriarDisciplinaConstraintException;
import com.escola.core.model.PessoaId;
import com.escola.util.ValidateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping(path = DisciplinaController.PATH)
public class DisciplinaController {
    public static final String PATH = "/api/v1/disciplinas";

    private final DisciplinaApplicationService service;
    private final ValidateService validator;

    @ApiOperation(value = "Criar uma disciplina.", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "disciplina criado."),
            @ApiResponse(code = 400, message = "A disciplina não pode ser criada porque está em um estado inválido.")})

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody CriarDisciplinaDTO dto) {

        validator.validate(dto).ifPresent(violations -> {
            throw new CriarDisciplinaConstraintException(violations);
        });

        var cmd = CriarDisciplinaCommand.of(
                dto.getDescricao(),
                dto.getSigla(),
                dto.getCargaHoraria(),
                professores(dto.getProfessores()));

        Disciplina disciplina = service.handle(cmd);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(disciplina.getId().toString()).build().toUri();

        return ResponseEntity.created(uri).build();
    }

    public static Set<CriarDisciplinaCommand.CriarDisciplinaProfessorCommand> professores(Set<CriarDisciplinaDTO.CriarDisciplinaProfessorDTO> professores) {
        return professores == null ? new HashSet<>()
                : professores.stream()
                .map(professorDTO -> CriarDisciplinaCommand.CriarDisciplinaProfessorCommand.from(
                        PessoaId.from(professorDTO.getId())
                        ))
                .collect(Collectors.toSet());
    }


}

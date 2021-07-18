package com.escola.core.professor.api;

import com.escola.core.professor.application.ProfessorApplicationService;
import com.escola.core.professor.application.commands.CriarProfessorCommand;
import com.escola.core.professor.domain.model.Professor;
import com.escola.core.professor.exception.CriarProfessorConstraintException;
import com.escola.util.ValidateService;
import com.escola.core.professor.api.dto.CriarProfessorDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping(path = ProfessorController.PATH)
public class ProfessorController {
    public static final String PATH = "/api/v1/professores";

    private final ProfessorApplicationService service;
    private final ValidateService validator;

    @ApiOperation(value = "Criar um professor.", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Professor criado."),
            @ApiResponse(code = 400, message = "O Professor não pode ser criado porque está em um estado inválido.")})

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody CriarProfessorDTO dto) {

        validator.validate(dto).ifPresent(violations -> {
            throw new CriarProfessorConstraintException(violations);
        });

        var cmd = CriarProfessorCommand.of(
                dto.getNome(),
                dto.getEmail(),
                dto.getCpf(),
                dto.getTitulacao());


        Professor professor = service.handle(cmd);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(professor.getId().toString()).build().toUri();

        return ResponseEntity.created(uri).build();
    }
}
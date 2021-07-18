package com.escola.core.aluno.api;

import com.escola.core.aluno.application.AlunoApplicationService;
import com.escola.core.aluno.application.commands.CriarAlunoCommand;
import com.escola.core.aluno.domain.model.Aluno;
import com.escola.core.aluno.exception.CriarAlunoConstraintException;
import com.escola.core.model.PessoaId;
import com.escola.util.ValidateService;
import com.escola.core.aluno.api.dto.CriarAlunoDTO;
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
@RequestMapping(path = AlunoController.PATH)
public class AlunoController {

    public static final String PATH = "/api/v1/alunos";

    private final AlunoApplicationService service;
    private final ValidateService validator;

    @ApiOperation(value = "Criar um aluno.", httpMethod = "POST", consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Aluno criado."),
            @ApiResponse(code = 400, message = "O Aluno não pode ser criado porque está em um estado inválido.")})

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody CriarAlunoDTO dto) {

        validator.validate(dto).ifPresent(violations -> {
            throw new CriarAlunoConstraintException(violations);
        });

        var cmd = CriarAlunoCommand
                .builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .matricula(dto.getMatricula())
                .formaIngresso(dto.getFormaIngresso())
                .build();

        Aluno aluno = service.handle(cmd);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(aluno.getId().toString()).build().toUri();

        return ResponseEntity.created(uri).build();
    }
}
package com.escola.config;

import com.escola.core.aluno.application.AlunoApplicationService;
import com.escola.core.disciplina.application.DisciplinaApplicationService;
import com.escola.core.professor.application.ProfessorApplicationService;
import com.escola.core.turma.application.TurmaApplicationService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class Config {


    @MockBean
    protected AlunoApplicationService alunoApplicationService;

    @MockBean
    protected DisciplinaApplicationService disciplinaApplicationService;

    @MockBean
    protected TurmaApplicationService turmaApplicationService;

    @MockBean
    protected ProfessorApplicationService professorApplicationService;


}

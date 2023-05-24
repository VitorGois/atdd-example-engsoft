package com.engsoft.coursesapi.application.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.engsoft.coursesapi.domain.models.Plan;
import com.engsoft.coursesapi.infraestructure.repositories.PlanRepository;


@ExtendWith(MockitoExtension.class)
public class CreatePlanUseCaseTest {

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private CreatePlanUseCase createPlanUseCase;

    @Test
    public void testExecute() {
        // Dados de entrada para o teste
        String name = "Plano A";
        String description = "Descrição do Plano A";

        // Criação do objeto de plano esperado
        Plan expectedPlan = new Plan();
        expectedPlan.setName(name);
        expectedPlan.setDescription(description);

        // Configuração do comportamento do repositório mockado
        Mockito.when(planRepository.save(Mockito.any(Plan.class))).thenReturn(expectedPlan);

        // Execução do caso de uso
        Plan createdPlan = createPlanUseCase.execute(name, description);

        // Verificação dos resultados
        assertNotNull(createdPlan);
        assertEquals(name, createdPlan.getName());
        assertEquals(description, createdPlan.getDescription());

        // Verificação se o método save do repositório foi chamado com o plano esperado
        Mockito.verify(planRepository).save(Mockito.eq(expectedPlan));
    }
}


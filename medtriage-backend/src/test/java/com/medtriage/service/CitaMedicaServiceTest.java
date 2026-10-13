package com.medtriage.service;

import medtriage.dto.CrearCitaDTO;
import medtriage.model.CitaMedica;
import medtriage.model.Doctor;
import medtriage.model.Paciente;
import medtriage.repository.CitaMedicaRepository;
import medtriage.repository.DoctorRepository;
import medtriage.repository.PacienteRepository;
import medtriage.service.CitaMedicaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CitaMedicaServiceTest {

    @Mock
    private CitaMedicaRepository citaMedicaRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private CitaMedicaService citaMedicaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearCitaExitososa() {
        // Arrange
        CrearCitaDTO dto = new CrearCitaDTO();
        dto.setDoctorId(1L);
        dto.setPacienteId(1L);
        dto.setNivelPrioridad(1); 
        dto.setMotivoConsulta("Dolor de pecho");
        dto.setMontoConsulta(25000.00); 

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setDisponible(true);

        Paciente paciente = new Paciente();
        paciente.setId(1L);

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(citaMedicaRepository.save(any(CitaMedica.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var resultado = citaMedicaService.crearCita(dto);

        assertNotNull(resultado);
    }
}
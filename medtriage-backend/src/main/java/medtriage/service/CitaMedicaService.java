package medtriage.service;

import medtriage.dto.CitaMedicaDTO;
import medtriage.dto.CrearCitaDTO;
import medtriage.model.CitaMedica;
import medtriage.model.Doctor;
import medtriage.model.Paciente;
import medtriage.repository.CitaMedicaRepository;
import medtriage.repository.DoctorRepository;
import medtriage.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CitaMedicaService {

    @Autowired
    private CitaMedicaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public CitaMedicaDTO crearCita(CrearCitaDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));

        if (!doctor.isDisponible()) {
            throw new RuntimeException("El doctor seleccionado no se encuentra disponible.");
        }

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        CitaMedica cita = new CitaMedica();
        String randomSuffix = UUID.randomUUID().toString().substring(0, 4);
        cita.setCodigoCita("CIT-2026-" + randomSuffix);
        cita.setPaciente(paciente);
        cita.setDoctor(doctor);
        cita.setNivelPrioridad(dto.getNivelPrioridad());
        cita.setMotivoConsulta(dto.getMotivoConsulta());
        cita.setEstado("PENDIENTE");
        cita.setMontoConsulta(dto.getMontoConsulta());
        cita.setFechaCita(LocalDateTime.now());

        CitaMedica saved = citaRepository.save(cita);
        return convertToDTO(saved);
    }

    public CitaMedicaDTO actualizarEstado(Long id, String nuevoEstado) {
        CitaMedica cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita médica no encontrada"));
        cita.setEstado(nuevoEstado);
        CitaMedica updated = citaRepository.save(cita);
        return convertToDTO(updated);
    }

    public List<CitaMedicaDTO> listarCitas() {
        return citaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CitaMedicaDTO convertToDTO(CitaMedica c) {
        CitaMedicaDTO dto = new CitaMedicaDTO();
        dto.setId(c.getId());
        dto.setCodigoCita(c.getCodigoCita());
        dto.setNombrePaciente(c.getPaciente() != null ? c.getPaciente().getNombreCompleto() : "");
        dto.setNombreDoctor(c.getDoctor() != null ? c.getDoctor().getNombre() : "");
        dto.setNivelPrioridad(c.getNivelPrioridad());
        dto.setMotivoConsulta(c.getMotivoConsulta());
        dto.setEstado(c.getEstado());
        dto.setMontoConsulta(c.getMontoConsulta());
        dto.setFechaCita(c.getFechaCita());
        return dto;
    }
}
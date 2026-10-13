package medtriage;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import medtriage.model.Doctor;
import medtriage.model.Paciente;
import medtriage.repository.DoctorRepository;
import medtriage.repository.PacienteRepository;

@SpringBootApplication
public class MedTriageApplication {

    private final DoctorRepository doctorRepository;
    private final PacienteRepository pacienteRepository;

    public MedTriageApplication(DoctorRepository doctorRepository, PacienteRepository pacienteRepository) {
        this.doctorRepository = doctorRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MedTriageApplication.class, args);
    }

    @PostConstruct
    public void init() {
        if (doctorRepository.count() == 0) {
            Doctor d1 = new Doctor();
            d1.setId(1L);
            d1.setNombre("Dr. Carlos Alvarado");
            d1.setDisponible(true);
            doctorRepository.save(d1);

            Doctor d2 = new Doctor();
            d2.setId(2L);
            d2.setNombre("Dra. Sofia Segura");
            d2.setDisponible(true);
            doctorRepository.save(d2);
        }

        if (pacienteRepository.count() == 0) {
            Paciente p1 = new Paciente();
            p1.setId(1L);
            p1.setNombreCompleto("Elena Madrigal Castro");
            pacienteRepository.save(p1);
        }
        
        System.out.println(">>> BASE DE DATOS INICIALIZADA CORRECTAMENTE CON ID 1");
    }
}
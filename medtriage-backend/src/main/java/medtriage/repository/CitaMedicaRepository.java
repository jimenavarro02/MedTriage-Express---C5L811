package medtriage.repository;

import medtriage.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {
    
    // Cambiar de String a Integer para que coincida con el modelo CitaMedica
    List<CitaMedica> findByNivelPrioridadOrderByFechaCitaDesc(Integer nivelPrioridad);

}